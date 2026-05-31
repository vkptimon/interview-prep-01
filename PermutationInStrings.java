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
        */
    }
}
