package com.maxdemarzi;

import org.codehaus.jackson.map.ObjectMapper;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Uniqueness;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Path("/service")
public class CustomShort {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Path("/shortest")
    public Response customShort(String body, @Context GraphDatabaseService db) throws IOException {
        HashMap input = Validators.getValidInput(body);
        HashSet<String> results = new HashSet<>();

        try (Transaction tx = db.beginTx()) {
            Node starting = db.getNodeById(Long.parseLong((String) input.get("from")));
            Node ending = db.getNodeById(Long.parseLong((String) input.get("to")));
            ArrayList<String> rels = (ArrayList<String>) input.get("types");
            ArrayList<RelationshipType> relTypes = new ArrayList<>();
            for (String rel : rels) {
                relTypes.add(RelationshipType.withName(rel));
            }

            PathExpander pathExpander = PathExpanderBuilder.allTypesAndDirections().build();
            FoundNodeEvaluator evaluator = new FoundNodeEvaluator(ending);
            TraversalDescription td = db.traversalDescription()
                    .breadthFirst()
                    .expand(pathExpander)
                    .evaluator(evaluator)
                    .uniqueness(Uniqueness.RELATIONSHIP_PATH);

            outerloop:
            for ( org.neo4j.graphdb.Path path : td.traverse(starting)) {
                for (Relationship rel : path.relationships()) {
                    if (relTypes.contains(rel.getType())) {
                        for (Node node : path.nodes()) {
                            results.add((String) node.getProperty("name"));
                        }
                        break outerloop;
                    }
                }
            }
        }
        return Response.ok().entity(objectMapper.writeValueAsString(results)).build();
    }
}
