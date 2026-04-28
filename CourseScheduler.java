public class CourseScheduler {
    public static void main(String[] args){
        int numCourses = 6;
        int[][] prerequisites = {{0,1}, {1,2}, {2,3}, {3,4}, {4,5}, {4,2}};

        var result = canFinish(numCourses, prerequisites);
        System.out.println("can we schedule the courses? "+result);
    }

    // Make stateArr a class-level variable so dfs() can access it
    private static int[] stateArr;
    private static int[][] adjacencyGraph;

    public static boolean canFinish(int numCourses, int[][] prerequisites){
        // FIX: Initialize adjacencyGraph with numCourses rows to store each node's neighbors
        // Previously: new int[1][numCourses] only had 1 row, couldn't represent all nodes
        adjacencyGraph = new int[numCourses][];
        // Initialize each node's adjacency list
        for (int i = 0; i < numCourses; i++) {
            adjacencyGraph[i] = new int[0];
        }

        // FIX: Iterate through all prerequisite pairs, not just prerequisites[0].length
        // prerequisites is a 2D array where each row is a pair [course, prerequisite]
        for(int i = 0; i < prerequisites.length; i++){
            // considering each pair [a,b]; where b->a (b is prerequisite for a)
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            
            // FIX: Build adjacency list properly using dynamic arrays
            // Previously: adjacencyGraph[0][a]=b overwrote entries and had fixed size
            int[] temp = new int[adjacencyGraph[b].length+1];
            System.arraycopy(adjacencyGraph[b], 0, temp, 0, adjacencyGraph[b].length);
            temp[adjacencyGraph[b].length] = a;
            adjacencyGraph[b] = temp;
        }

        //state, where 0-unvisited, 1-visited and still processing, 2-processed
        stateArr = new int[numCourses];

        // FIX: Iterate through all numCourses, not just prerequisites.length
        // Some courses might have no prerequisites but still need to be checked
        for(int i = 0; i < numCourses; i++){
            if(stateArr[i] == 0){
                if(!dfs(i))
                    return false;
            }
        }

        return true;

    }

    public static boolean dfs(int node){
        if(stateArr[node] == 1)
            return false;  // Cycle detected
        if(stateArr[node] == 2)
            return true;   // Already processed, no cycle from here

        stateArr[node] = 1;  // Mark as visiting
        
        // FIX: Iterate through current node's neighbors, not adjacencyGraph[0]
        // Previously: adjacencyGraph[0].length only checked first row
        for(int i = 0; i < adjacencyGraph[node].length; i++){
            if(!dfs(adjacencyGraph[node][i]))
                return false;
        }
        
        // FIX: Set state to processed AFTER visiting all neighbors
        // Previously: stateArr[node] == 2 was a comparison (==), not assignment (=)
        stateArr[node] = 2;
        return true;
    }
    
}
