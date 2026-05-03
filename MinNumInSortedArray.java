import java.util.*;

public class MinNumInSortedArray {
    public static void main(String[] args){
        int[] nums = {3,4,5,6,1,2};
        int[] nums2 = {7,8,9,10,11,12};
        int result = findMin(nums2);
        System.out.println("the min number is: "+result);
    }

    // solution with O(n) runtime
    public static int findMin(int[] nums){
        int left=0, right=0;
        while(right<nums.length){
            if(nums[left] <= nums[right]){
                right++;
            }
            else{
                return nums[right];
            }
        }

        return nums[0];
    }
}
