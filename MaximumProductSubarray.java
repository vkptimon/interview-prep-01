public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = {-2, 2, 0, 1, -1, 3};
        var result = maxProduct(nums);
        System.out.println("the maximum product of an subarray is: "+result);
    }

    public static int maxProduct(int[] nums){
        /**
         * The Rough algorithm would be like this
         * we initialize two pointer first and last, where last = first+1 initially
         * we keep calculating the product till we find a negative number or zero
         * we increment last till a negative number is identified, and then we will search for another negative number
         * the idea is to find out if the array has even number of negative numbers without a zero in-between
         * if so, then we will calculate the product of all the number involved and then update, return the product value
         * after being at an odd negative number, if there's no more negative number, then we will calculate the product value till that number
         * if in-between a zero is present, then the window will be stopped and started again after the zero
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
