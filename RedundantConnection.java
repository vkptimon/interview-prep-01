public class RedundantConnection {
    public static void main(String[] args) {
        
    }

    /**
     * Psuedocode for the problem
     * <pre>
        function find(parent, x):
        if parent[x] != x:
            parent[x] = find(parent, parent[x])  // Path compression
        return parent[x]

    function union(parent, x, y):
        rootX = find(parent, x)
        rootY = find(parent, y)
        
        if rootX == rootY:
            return false  // Already connected - adding this edge creates cycle!
        
        parent[rootX] = rootY  // Merge groups
        return true

    function findRedundantConnection(edges):
        n = edges.length
        parent = new int[n + 1]  // 1-indexed nodes
        
        // Initialize: each node is its own parent
        for i = 1 to n:
            parent[i] = i
        
        for each edge [u, v] in edges:
            if find(parent, u) == find(parent, v):
                return edge  // This edge creates a cycle
            else:
                union(parent, u, v)  // Connect these nodes
        
        return []  // Should never reach here given problem constraints
 
     * </pre>
     * @param edges
     * @return
     */

    public static int[] findRedundantConnection(int[][] edges){
        /** Rough Algorithm
         * We need to find where a cycle is being formed in the graph and return the edge that's causing int
         * We can look upto the problem "Graph Valid Tree"
         * Instead of checking based on nodes and returning boolean, we need perform based on edges and return the responsible edge
         * Even in that question in that question, we are building the edges between the nodes in adjacency graph
         * So, we could skip that step and directly use it
         * We need to perform a DFS, with the initial root node being 1 and it's parent being 0
         * So, DFS(node, parent, visited boolean array, adjacency graph 2D array)
         * If it returns false, we could immediately return the edge connected to that node
         * 
         * DFS has a couple of performance issues for this problem, like more time complexity, tracking nodes per call
         * So, it's advised to use "Union Find"(Disjoint Set Union) approach for this problem
         */

        int[] result = new int[2];

        int n = edges.length; 
        int[] parentArray = new int[n+1];

        //initializing each nodes as it's own parent
        for(int i = 1; i <= n; i++){
            parentArray[i] = i;
        }

        for(int[] edge:edges){
            int nodeU = edge[0];
            int nodeV = edge[1];
            
            //if nodeU and nodeV are already connected, this edge is redundant
            if( find(parentArray, nodeU) == find(parentArray, nodeV) )
                result = edge;
            else
                union(parentArray, nodeU, nodeV);
        }

        return result;
    }

    //find with path compression
    public static int find(int[] parentArray, int node){
        if( parentArray[node] != node )
            parentArray[node] = find(parentArray, parentArray[node]);  //path compression
        return parentArray[node];
    }

    //union operation
    public static void union(int[] parentArray, int nodeU, int nodeV){
        int parentU = find(parentArray, nodeU);
        int parentV = find(parentArray, nodeV);

        if( parentU != parentV )
            parentArray[parentU] = parentV; //attach one parent to another
    }
}
