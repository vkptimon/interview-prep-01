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
         * loop through each element, till the last but 2(to get rid of bounds issue), and initialize two pointers first and last;
         * the former from the start and the latter from the end of the array
         * if sum(nums[first]+nums[last]) > -nums[i], then last--
         * else if sum(nums[first]+nums[last]) < -nums[i], then first++
         * else, form the List<List<Integer>> with i, first, last; we need to first++ and last++ to avoid duplicates
         * if also need to check if the consecutive elements at the first position aren't the same and increment till a diff one is found
         * if the nums[i] == nums[i-1], then we could skip it, to avoid duplicates
         */

        List<List<Integer>> result = new ArrayList<>();

        //sorting
        Arrays.sort(nums);

        //looping through the array, till the last but 2 elements so it doesn't cause bounds issue
        for(int i=0; i<nums.length-2; i++){
            //skip when the same number check
            if(i>0 &&nums[i-1] == nums[i])
                continue;

            //initializing the first and last pointers
            int first = i+1;
            int last = nums.length-1;

            //this becomes 2Sum problem from here
            while(first<last){
                if(nums[first]+nums[last] > -(nums[i]))
                    last--;
                else if(nums[first]+nums[last] < -(nums[i]))
                    first++;
                else{
                    List<Integer> positionList = new ArrayList<>();
                    positionList.add(nums[i]);
                    positionList.add(nums[first]);
                    positionList.add(nums[last]);

                    result.add(positionList);

                    //skip this combo, so it won't be duplicated
                    first++;
                    last--;
                    
                    //skipping the duplicate case of fixed element, only after a match
                    while(first<last && nums[first] == nums[first-1])
                        first++;
                }
            }
        }

        return result;
    }
}
