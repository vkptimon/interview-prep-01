import java.util.PriorityQueue;

public class KthLargestElementInStream {
    
}

class KthLargest{
    /** Rough Approach
     * the first time we initialize the array, we can sort it in descending order, which would make finding kth element easier
     * when a new element is being added, we can check if it's greater than the element at (k-1)th position
     * if it is, then we have to check from the start till kth element to insert it, would take O(N) at worst case
     * but inserting in between in array involves moving the elements consequtively and also tidious to create an empty cell in between
     * else, insert it in the other half
     * return at the element at (k-1)th index in the end
     * 
     * BETTER APPROACH: implement a min-heap of size k
     * When the new value <= current kth largest, skip, don't add/remove anything
     * If new value > current kth largest, pop the peek(last element) and add the new value
     */

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private int k;

    public KthLargest(int k, int[] nums){
        this.k = k;
        this.minHeap = new PriorityQueue<>();

        for(int num : nums){
            add(num);
        }
    }

    public int add(int val){
        //add to heap if we have space
        if(minHeap.size() < k)
            minHeap.add(val);

        //if the heap is full and val is greater than peek
        else if (val > minHeap.peek()){
            minHeap.poll();
            minHeap.add(val);
        }

        //returning current kth largest, which is smallest in the heap
        return minHeap.peek();
    }
}