import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public static void main(String[] args) {
        
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        var result = topKFrequentElements(nums, k);
        System.out.println("The result is " + Arrays.toString(result));
    }
    
    /**
     * Final Approach
    1. Initialize an empty frequency collection to hold each distinct element and its occurrence count.  
    2. Iterate through the input list once: for each element, increase its count in the frequency collection.  
    3. Create a min‑size container (heap) that will store at most k entries, where each entry consists of an element and its frequency.  
    4. Iterate over the entries in the frequency collection:  
    a. If the heap has fewer than k entries, add the current element‑frequency pair to the heap.  
    b. Otherwise, compare the current element’s frequency with the frequency at the root of the heap (the smallest frequency in the heap).  
    c. If the current frequency is greater than the root’s frequency, remove the root and insert the current element‑frequency pair into the heap.  
    5. After processing all distinct elements, the heap contains the k elements with the highest frequencies.  
    6. Extract all elements from the heap (order does not matter) and return them as the result.
    This procedure uses a single pass to build frequencies, a second pass over distinct elements with a heap of size k, giving O(n + m log k) time (where m is the number of distinct elements) and O(m + k) space. 
     * 
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequentElements(int[] nums, int k) {

       /** Rough Approach for the leetcode problem
     * The bruteforce or naive approach: to increment the frequency of an element in nums at every occurence during scan
     * Maintaining the nums[i] as key and it's frequency as value
     * Then we could stream the map and get the sorted list of frequencies in descending order and return the first k freqs
     * OR Another approach is we could push all these values into a min-heap
     * For every occurence, it'll be pushed to the top
     * In the end, we will return the top k elements from the peek
     */
    
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Min-heap ordered by frequency.
        // Queue holds int[] where [element, frequency].
        // Comparator compares by frequency (index 1) in natural order (min-heap).
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Step 3: Iterate over each distinct element-frequency pair
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int element = entry.getKey();
            int frequency = entry.getValue();

            if (minHeap.size() < k) {
                // Step 3a: Heap not yet full, add directly
                minHeap.offer(new int[]{element, frequency});
            } else if (frequency > minHeap.peek()[1]) {
                // Step 3b: Current frequency larger than smallest in heap, replace
                minHeap.poll();
                minHeap.offer(new int[]{element, frequency});
            }
        }

        // Step 4: Extract top k elements from heap (order not important)
        int[] result = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll()[0];
        }

        return result;
    }
}
