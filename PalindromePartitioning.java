import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning{
    public static void main(String[] args) {
        String s = "aab";
        var result = palindromePartitioning(s);
        System.out.println("The result is " + result);
    }
    
    public static List<List<String>> palindromePartitioning(String s) {
        /** Rough Approach for the leetcode problem 
         * Not able to think of any viable way to solve the question
         * The only approach i had in my mind was to build a sliding window, which breaks off when a character disturbs the palindrome nature of the substring
        */

        List<List<String>> allPartitions = new ArrayList<>();
        List<String> currPath = new ArrayList<>();

        backtrack(s, 0, currPath, allPartitions);

        return allPartitions;
    }

    public static void backtrack(String inputString, int start_index, List<String> currPath, List<List<String>> allPartitions){
        //base condition
        if(start_index == inputString.length()){
            List<String> copyList = new ArrayList<>(currPath);
            allPartitions.add(copyList);
            return;
        }

        //traversing till the end of inputString
        for(int i = start_index; i < inputString.length(); i++){
            //evaluating if the substring from start to i is a palindrome
            if(isPalindrome(inputString, start_index, i)){
                String subPalindrome = inputString.substring(start_index, i+1);
                currPath.add(subPalindrome);
                //exploring if the next index follows the palindrome check
                backtrack(inputString, i+1, currPath, allPartitions);
                //removing the last appended substring to try other partitions
                currPath.removeLast();
            }
        }
    }

    public static boolean isPalindrome(String str, int start, int end){
        while(start < end){
            if(str.charAt(start) != str.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }
}