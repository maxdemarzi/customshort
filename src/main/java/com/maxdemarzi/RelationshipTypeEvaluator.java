package com.maxdemarzi;

import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.traversal.Evaluation;
import org.neo4j.graphdb.traversal.Evaluator;

import java.util.ArrayList;

public class RelationshipTypeEvaluator implements Evaluator {

    ArrayList<RelationshipType> rels;

    public RelationshipTypeEvaluator(ArrayList<RelationshipType> rels) {
        this.rels = rels;
    }

    @Override
    public Evaluation evaluate(Path path) {
        return Evaluation.EXCLUDE_AND_CONTINUE;
    }

}
