import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CheapFlightsWithKStops {
    public static void main(String[] args) {
        int n=4;
        int[][] flights = {{0,1,100}, {1,2,100}, {2,0,100}, {1,3,600}, {2,3,200}};
        int src=0;
        int dst=3;
        int k=1;
        int result = findCheapestPrice(n, flights, src, dst, k);
        System.out.println("result is "+result);
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k){
        // 1. Build the adjacency list (Graph)
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] f : flights) {
            adj.computeIfAbsent(f[0], x -> new ArrayList<>()).add(new int[]{f[1], f[2]});
        }

        // 2. Track the minimum cost to reach each node
        int[] minCosts = new int[n];
        Arrays.fill(minCosts, Integer.MAX_VALUE);
        minCosts[src] = 0;

        // 3. Queue stores {currentNode, currentCost}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});

        int stops = 0;

        // BFS level by level, up to k+1 flights
        while (!queue.isEmpty() && stops <= k) {
            int size = queue.size();
            
            // Process all nodes at the current 'stop' level
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int u = curr[0];
                int cost = curr[1];

                if (!adj.containsKey(u)) continue;

                for (int[] neighbor : adj.get(u)) {
                    int v = neighbor[0];
                    int price = neighbor[1];

                    // Optimization: Only explore this path if it's cheaper than 
                    // any previous path found to reach 'v'
                    if (cost + price < minCosts[v]) {
                        minCosts[v] = cost + price;
                        queue.offer(new int[]{v, minCosts[v]});
                    }
                }
            }
            stops++;
        }

        return minCosts[dst] == Integer.MAX_VALUE ? -1 : minCosts[dst];
    }
}
