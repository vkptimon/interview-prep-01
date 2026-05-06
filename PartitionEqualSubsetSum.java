public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums = {1,2,5};
        var result = canPartition(nums);
        System.out.println("can the given array be partitioned into 2 subsets of equal sum: "+result);
    }

    public static boolean canPartition(int[] nums){
        /** Rough Algorithm
         * We need to form a subarray, whose sum will be equal to total/2; since we are dividing the array into 2 subsets of equal sum
         * if the total sum is odd, return false, no need to check
         * we need to loop once and calculate the total sum of the array
         * and then loop again, add the numbers to subset array until their sum <= total/2
         * if it exceeds total/2, then we need to remove the recent number from the subset array
         * if it exceeds total/2 with a particular number combination, then we need to remove that too from the subset array
         * if all the possible combinations are explored, we will check, out of the loop, if the subset sum == total/2
         * if it is, return true; else false
         */

        int totalSum = 0;
        for(int i=0; i<nums.length; i++){
            totalSum = totalSum+nums[i];
        }

        if(totalSum%2 != 0)
            return false;

        return backtrack(0, 0, totalSum/2, nums);
    }

    public static boolean backtrack(int start, int currentSum, int target, int[] nums){
        if(currentSum == target)
            return true;
        else if(currentSum > target)
            return false;
        else if(start >= nums.length)
            return false;

        for(int i=start; i<nums.length; i++){
            if(backtrack(i+1, currentSum+nums[i], target, nums))
                return true;
            // skipping duplicates
            while (i+1<nums.length && nums[i+1]==nums[i]) { i++; }
        }

        return false;
    }
}
