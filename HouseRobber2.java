public class HouseRobber2 {
    public static void main(String[] args) {
        
        int[] nums = {};
        var result = houseRobber2(nums);
        System.out.println("The result is " + result);
    }
    
    /**
     * Your approach is correct! Since the houses are circular, the first and last
     * are neighbors. The solution is to take the maximum of two linear subproblems.
     * 
     * Pseudocode:
     * 1. If nums length is 1, return nums[0].
     * 2. Define a helper function linearRob(array_slice):
     *    - Use two variables (rob1, rob2) to track the max loot of the previous two houses.
     *    - Iterate through the slice: newMax = max(current + rob1, rob2).
     *    - Update rob1 = rob2, rob2 = newMax.
     *    - Return rob2.
     * 3. Return max(linearRob(nums[0...n-2]), linearRob(nums[1...n-1])).
     */
    public static int houseRobber2(int[] nums) {
        /** Rough Approach for the leetcode problem
         * We have to divide the problem into 2 types
         * Since the first and last houses are adjacent to each other, we can only choose one at once
         * If the first house is selected, we have to calculate the max till the nums.size-2
         * Else, we have to start the max calculation from the 2nd house
         * The max of these both should be returned, the rest of the problem is linear house robber
         */

        int size = nums.length;
        
        if(size == 1)
            return nums[0];

        return Math.max(linearRob(nums, 0, size-2), linearRob(nums, 1, size-1));
    }

    public static int linearRob(int[] nums, int start, int end){
        int rob1 = 0, rob2 = 0;
        int newMax = 0;
        for(int i = start; i <= end; i++){
            newMax = Math.max(nums[i]+rob1, rob2);
            rob1 = rob2;
            rob2 = newMax;
        }

        return rob2;
    }
}
