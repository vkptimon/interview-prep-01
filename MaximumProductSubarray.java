public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = {-2, 2, 0, 1, -1, 3};
        var result = maxProduct(nums);
        System.out.println("the maximum product of an subarray is: "+result);
    }

    public static int maxProduct(int[] nums){
        /**
         * The Rough algorithm would be like this
         * we need to initialize two variables maxEndingHere and minEndingHere, both initialized to the first element of the array
         * while looping the remaining elements, we need to update the max and min endings based on if the number is positive or negative
         * for a positive number, we will calculate the max and min in the normal way but it get's inverted if it's a negative number
         */

        int maxProduct = nums[0];
        int maxEndingHere = nums[0], minEndingHere = nums[0];

        if (nums.length < 2){
            return nums[0];
        }

        for(int i=1; i<nums.length; i++){
            int currentNum = nums[i];

            int previousMax = maxEndingHere;

            if(currentNum > 0){
                maxEndingHere = Math.max(currentNum, previousMax*currentNum);
                minEndingHere = Math.min(currentNum, minEndingHere*currentNum);
            } else { //for negative numbers
                maxEndingHere = Math.max(currentNum, minEndingHere*currentNum);
                minEndingHere = Math.min(currentNum, previousMax*currentNum);
            }

            maxProduct = Math.max(maxProduct, maxEndingHere);
        }

        return maxProduct;
    }
}
