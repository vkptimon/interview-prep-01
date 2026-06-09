import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    

    public static List<List<String>> groupAnagrams(String[] strs) {
        /**
         * Approach:
         * 1. Create a HashMap where the key is the sorted version of each string,
         *    and the value is a list of all strings that are anagrams of each other
         *    (i.e., they share the same sorted version).
         * 2. For each string, sort its characters to form the key, then add the original
         *    string to the list corresponding to that key in the map.
         *    - If the key doesn't exist, computeIfAbsent creates a new ArrayList automatically.
         *    - If the key exists, it retrieves the existing list and adds the string to it.
         * 3. Finally, return all the values (lists of anagrams) from the map as a List<List<String>>.
         * 
         * Why this works:
         * - Anagrams have identical sorted character sequences (e.g., "eat", "tea", "ate" all sort to "aet")
         * - Using the sorted string as a key groups all anagrams together in the map
         * - computeIfAbsent ensures we don't create duplicate lists and handles the null check elegantly
         */

        Map<String, List<String>> anagramMap = new HashMap<>();

        for (String str : strs) {
            String sortedKey = sortString(str);
            // Automatically creates a new ArrayList if key doesn't exist, then adds the string
            anagramMap.computeIfAbsent(sortedKey, k -> new ArrayList<>()).add(str);
        }

        // Collect all the anagram groups (values of the map) into a list
        return new ArrayList<>(anagramMap.values());
    }

    public static String sortString(String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
