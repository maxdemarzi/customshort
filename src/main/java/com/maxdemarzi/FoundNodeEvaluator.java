package com.maxdemarzi;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.BranchState;
import org.neo4j.graphdb.traversal.Evaluation;
import org.neo4j.graphdb.traversal.PathEvaluator;

public class FoundNodeEvaluator implements PathEvaluator<Boolean> {

    private Node node;
    public FoundNodeEvaluator(Node node) {
        this.node = node;
    }

    @Override
    public Evaluation evaluate(Path path, BranchState<Boolean> branchState) {
        System.out.println(path);
        if (branchState.getState() && path.endNode().equals(node)) {
            return Evaluation.INCLUDE_AND_PRUNE;
        }
        return Evaluation.EXCLUDE_AND_CONTINUE;
    }

    @Override
    public Evaluation evaluate(Path path) {
        return null;
    }
}
