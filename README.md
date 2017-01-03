# Custom Short
Example Traversal API finding the first shortest path to a node by way of at least one of given relationship types

# Instructions

1. Build it:

        # neo_path_to_label
        Example Traversal API finding the first shortest path to a node with a specific label
        
        # Instructions
        
        1. Build it:
        
                mvn clean package
        
        2. Copy target/custom-short-1.0-SNAPSHOT.jar to the plugins/ directory of your Neo4j server.
        
        
        3. Configure Neo4j by adding a line to conf/neo4j.conf:
        
                #dbms.unmanaged_extension_classes=com.maxdemarzi=/v1
        
                
        4. Start Neo4j server.
                       
        5. Try the extension:
                
                :POST /v1/service/shortest {"from":"1","to":"2","types":["TYPE1","TYPE2"]}
                