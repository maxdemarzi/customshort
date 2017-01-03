package com.maxdemarzi;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.Evaluation;
import org.neo4j.graphdb.traversal.Evaluator;

public class FoundNodeEvaluator implements Evaluator {

    private Node node;
    public FoundNodeEvaluator(Node node) {
        this.node = node;
    }

    @Override
    public Evaluation evaluate(Path path) {
        if (path.endNode().equals(node)) {
            return Evaluation.INCLUDE_AND_PRUNE;
        }
        return Evaluation.EXCLUDE_AND_CONTINUE;
    }
}
