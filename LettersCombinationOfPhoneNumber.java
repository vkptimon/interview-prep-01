import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode Problem: Letter Combinations of a Phone Number
 * 
 * Given a string containing digits from 2-9, return all possible letter combinations
 * that the number could represent based on traditional phone keypad mapping.
 * 
 * Example:
 * Input: "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */
public class LettersCombinationOfPhoneNumber {

    public static List<String> letterCombinations(String digits) {
        // Handle edge case: empty input
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        // Initialize the result list to store all combinations
        List<String> result = new ArrayList<>();
        
        // Create the digit to letters mapping (like on a phone keypad)
        Map<Character, String> digitToLetters = new HashMap<>();
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");

        // Start the backtracking/DFS process
        backtrack(digits, 0, new StringBuilder(), result, digitToLetters);
        
        return result;
    }

    /**
     * Backtracking helper method to generate all combinations
     * 
     * @param digits The input string of digits
     * @param index Current position in the digits string we're processing
     * @param current Current combination being built
     * @param result List to store all valid combinations
     * @param digitToLetters Mapping of digits to their corresponding letters
     */
    private static void backtrack(String digits, int index, StringBuilder current, 
                                   List<String> result, Map<Character, String> digitToLetters) {
        
        // Base case: we've processed all digits
        // The current combination is complete, add it to result
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get the current digit
        char digit = digits.charAt(index);
        
        // Get all possible letters for this digit
        String letters = digitToLetters.get(digit);
        
        // Try each letter for the current digit
        for (char letter : letters.toCharArray()) {
            // Choose: add the current letter to the combination
            current.append(letter);
            
            // Explore: recurse to process the next digit
            backtrack(digits, index + 1, current, result, digitToLetters);
            
            // Unchoose: backtrack by removing the last added letter
            // This allows us to try the next letter in the next iteration
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(letterCombinations("23")); 
        // Expected: [ad, ae, af, bd, be, bf, cd, ce, cf]
        
        System.out.println(letterCombinations("")); 
        // Expected: []
        
        System.out.println(letterCombinations("2")); 
        // Expected: [a, b, c]
    }
}
