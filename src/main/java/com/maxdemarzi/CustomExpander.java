package com.maxdemarzi;

import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpander;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.traversal.BranchState;

import java.util.HashSet;

public class CustomExpander implements PathExpander<Boolean> {
    HashSet<RelationshipType> relTypes;

    public CustomExpander(HashSet<RelationshipType>relTypes) {
        this.relTypes = relTypes;
    }

    @Override
    public Iterable<Relationship> expand(Path path, BranchState<Boolean> branchState) {
        if (path.length() > 0) {
            if (!branchState.getState()) {
                if (relTypes.contains(path.lastRelationship().getType())) {
                    branchState.setState(true);
                }
            }
        }
        return path.endNode().getRelationships();
    }

    @Override
    public PathExpander reverse() {
        return null;
    }
}
