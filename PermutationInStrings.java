import java.util.HashMap;
import java.util.Map;

public class PermutationInStrings {
    public static void main(String[] args) {
        
        String s1 = "ab";
        String s2 = "eidbaooo";
        var result = permutationInStrings(s1, s2);
        System.out.println("The result is " + result);
    }
    
    public static boolean permutationInStrings(String s1, String s2) {
        /** Rough Approach for the leetcode problem 
         * permutation of a string is all the possible combinations of the characters in the string
         * my initial thought was to search if all the possible combinations of the search string in the main string
         * but that is obviously brute-force and not the intended way to solve it
         * 
         * hints from deepseek
         * to know if a substring is a permutation, both of them should have similar frequency count.
         * we have to maintain a sliding window, of at most size of the size of the search string
         * if the characters in the window match the one's in the substring, along with the frequencies, we return true
         * else, we reset the window and move forward and if finally we reach the end and the window is empty, then false
         *
         * small correction on the sliding window logic
         * we will not reset the window, but rather slide the window one character at a time
         * whatever character is added newly to the window, the freq++; else freq--
         * when a frequency map similar to the target string is obtained return true; else false
        */

        // FIX: If s1 is longer than s2, no permutation can exist as a substring
        if (s1.length() > s2.length()) return false;

        Map<Character, Integer> searchStringFreqMap = new HashMap<>();
        // SIMPLIFIED: Replaced merge() with getOrDefault()
        for(int i = 0; i < s1.length(); i++){
            char c = s1.charAt(i);
            searchStringFreqMap.put(c, searchStringFreqMap.getOrDefault(c, 0) + 1);
        }

        int first = 0;
        int second = s1.length() - 1;
        Map<Character, Integer> targetStringFreqMap = new HashMap<>();

        // SIMPLIFIED: Replaced merge() with getOrDefault()
        for(int i = first; i <= second; i++){
            char c = s2.charAt(i);
            targetStringFreqMap.put(c, targetStringFreqMap.getOrDefault(c, 0) + 1);
        }

        // FIX: Check the first window before entering the slide loop
        if (searchStringFreqMap.equals(targetStringFreqMap)) return true;

        // SIMPLIFIED: Replaced merge() with standard get/put
        while (second < s2.length() - 1) {
            // Remove the character leaving the window at index 'first'
            char leftChar = s2.charAt(first);
            targetStringFreqMap.put(leftChar, targetStringFreqMap.get(leftChar) - 1);
            if (targetStringFreqMap.get(leftChar) == 0) {
                targetStringFreqMap.remove(leftChar);
            }

            // Advance the window and add the new character at index 'second+1'
            first++;
            second++;

            char rightChar = s2.charAt(second);
            targetStringFreqMap.put(rightChar, targetStringFreqMap.getOrDefault(rightChar, 0) + 1);

            if(searchStringFreqMap.equals(targetStringFreqMap))
                return true;
        }

        return false;
    }
}