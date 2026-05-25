import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    

    public static int lastStoneWeight(int[] stones){
        /** Rough Algorithm
         * We sort the array everytime after the smash process and check if any element is present in the end
         * But the above solution is not at all optimal and is brute-force
         * we have to perform a recursion but i couldn't think of any way for the base case or actual condition
         * 
         * We can push the stones into a max heap, where the top elements would be present on the top
         * If the difference between top 2 elements is > 0, then we will push the diff into the heap
         */

        //initializing the max heap
        PriorityQueue<Integer> stoneHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int stone : stones){
            stoneHeap.add(stone);
        }

        
        // FIX 1: Loop while at least 2 stones exist, not for fixed array length
        // Reason: Heap size changes dynamically as stones are destroyed
        while(stoneHeap.size() >= 2) {
            // FIX 2: Poll both stones BEFORE any conditional checks
            // Reason: We always need two stones when loop condition is true
            int firstLargestStone = stoneHeap.poll();   // largest stone
            int secondLargestStone = stoneHeap.poll();  // second largest stone

            int smashDiff = firstLargestStone - secondLargestStone;
            if(smashDiff > 0){
                stoneHeap.add(smashDiff);
            }
            // If smashDiff == 0, both stones are destroyed (nothing to add back)
        }

        // FIX 3: Simplified return logic
        // Reason: If heap is empty, return 0; otherwise return the remaining stone
        return stoneHeap.isEmpty() ? 0 : stoneHeap.peek();
    }
}
