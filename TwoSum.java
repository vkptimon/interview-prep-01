import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] nums2 = {3,2,4};
        int target2 = 6;
        var result = twoSum(nums2, target2);
        System.out.println("the unique pair is: {"+ result[0]+", "+result[1]+"}");
    }

    public static int[] twoSum(int[] nums, int target){
        /**
         * We need to traverse the array from the start and end
         * We have to sort it
         * If the diff btw target and nums[last] < nums[first], then we need to decrement last
         * Else if the diff btw target and nums[last] > nums[first], then we need to increment first
         * Else, we have found the pair and then add them to the result list
         */
        int[] result = new int[2];
        int first = 0, last = nums.length-1;

        //sorting the array
        // Arrays.sort(nums);

        while(first < last){
            if(nums[first] > (target - nums[last]))
                last--;
            else if(nums[first] < (target - nums[last]))
                first++;
            else{
                result[0] = first;
                result[1] = last;
                first++;
                last--;
            }
        }

        return result;
    }
}
