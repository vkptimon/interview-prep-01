import java.util.ArrayList;
import java.util.List;

public class ThreeSum {
 public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, 4};
        var result = threeSum(nums);
        System.out.println("the resut is: "+result);
    }

    public static List<List<Integer>> threeSum(int[] nums){

        /**
         * Rough Algorithm
         * sort the array
         * loop through each element and initialize two pointers first and last;
         *  the former from the start and the latter from the end of the array
         * if sum(nums[first]+nums[last]) > -nums[i], then last--
         * else if sum(nums[first]+nums[last]) < -nums[i], then first++
         * else, form the List<List<Integer>> with i, first, last
         * if the nums[i] == nums[i-1], then we could skip it, to avoid duplicates
         */

        if(nums.length == 3){
            if(nums[0]+nums[1]+nums[2] == 0){

            }
        }

        //sorting
    }
}
