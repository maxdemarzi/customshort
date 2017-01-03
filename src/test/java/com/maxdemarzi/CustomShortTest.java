package com.maxdemarzi;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.harness.junit.Neo4jRule;
import org.neo4j.test.server.HTTP;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomShortTest {
    @Rule
    public Neo4jRule neo4j = new Neo4jRule()
            .withFixture(MODEL_STATEMENT)
            .withExtension("/v1", CustomShort.class);

    @Test
    public void shouldFindCustomShortestPath() {
        HTTP.Response response = HTTP.POST(neo4j.httpURI().resolve("/v1/service/shortest").toString(), input);
        ArrayList<String> actual  = response.content();
        Assert.assertEquals(expected, actual);
    }

    private static final String MODEL_STATEMENT =
            
            /*    n3-[TYPE2]-n5--[TYPE2]--
                 /                        \
            n1--n2                        n8
                 \                        /
                  n4-[TYPE3]-n6-[TYPE3]-n7
             */

            "CREATE (n1:Label1 {name:'n1'})" +
            "CREATE (n2:Label2 {name:'n2'})" +
            "CREATE (n3:Label3 {name:'n3'})" +
            "CREATE (n4:Label3 {name:'n4'})" +
            "CREATE (n5:Label4 {name:'n5'})" +
            "CREATE (n6:Label4 {name:'n6'})" +
            "CREATE (n7:Label5 {name:'n7'})" +
            "CREATE (n8:Label5 {name:'n8'})" +
            "CREATE (n1)-[:TYPE1]->(n2)" +
                    "CREATE (n2)-[:TYPE1]->(n3)" +
                    "CREATE (n2)-[:TYPE1]->(n4)" +
                    "CREATE (n3)-[:TYPE2]->(n5)" +
                    "CREATE (n4)-[:TYPE3]->(n6)" +
                    "CREATE (n5)-[:TYPE4]->(n8)" +
                    "CREATE (n6)-[:TYPE3]->(n7)" +
                    "CREATE (n7)-[:TYPE4]->(n8)"
            ;

    private static final HashMap input = new HashMap<String, Object>() {{
        put("from","0");
        put("to","7");
        put("types", new ArrayList<String>(){{add("TYPE3");}});
    }};


    private static final ArrayList<String> expected = new ArrayList<String>() {{
        add("n1");
        add("n2");
        add("n4");
        add("n6");
        add("n7");
        add("n8");
    }};
}
