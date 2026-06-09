import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicates {
    public static void main(String[] args) {
        
        int[] nums = {1,2,3,4};
        var result = containsDuplicates(nums);
        System.out.println("The result is " + result);
    }
    
    public static boolean containsDuplicates(int[] nums) {
        /** Rough Approach for the leetcode problem
         * We have to increment the frequency of an element in a map
         * When the frequency of any element is more than one, return false; else true
         */

        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int num : nums){
            int freq = 0;
            if(freqMap.containsKey(num))
                return true;
            freqMap.putIfAbsent(num, freq++);
        }

        return false;
    }
}
