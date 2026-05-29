import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public static void main(String[] args) {
        
        int n = 3;
        var result = generateParenthesis(n);
        System.out.println("The result is " + result);
    }
    
    public static List<String> generateParenthesis(int n) {
        /** Rough Approach for the leetcode problem 
         * For all 'n', we will have 'n' ( , ) braces
         * We will push all the ( into a stack and pop out gradually
         * First we will pop-out one ( and we can map it outmost 1 ) brace, so we will get [()]
         * Then we will pop-out the second ( and we can match it to outmost 2 braces
         * So, we will get [(), ()], [(())]
         * Similarly for the third one we will get - [(), (), ()], [(()), (); (), (()); ((), ())], [((()))]
         * 
         * Instead of the stack approach, we have to track only 2 variables "open" and "close"
         * In no point, should close > open and both close, open <= n
         * Until then, we will check and append the parenthesis in a recursive fashion
        */

        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    public static void backtrack(List<String> result, String currentString, int openCount, int closeCount, int n){
        if( openCount == n && closeCount == n ){
            result.add(currentString);
            return;
        }

        // adding '(' if we still have open braces left
        if( openCount < n )
            backtrack(result, currentString + "(", openCount + 1, closeCount, n);

        //adding ')' if it won't make sequence invalid
        if( closeCount < openCount )
            backtrack(result, currentString + ")", openCount, closeCount + 1, n);
    }
}