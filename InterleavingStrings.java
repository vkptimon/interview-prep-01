public class InterleavingStrings {
    public static void main(String[] args) {

        String s1 = "aabcc", s2 = "ddbca", s3 = "aadbbcbcac";
        var result = interleavingStrings(s1, s2, s3);
        System.out.println("The result is " + result);
    }

    /**
     * Pseudocode (2D Dynamic Programming):
     * 1. If length(s1) + length(s2) != length(s3), return false.
     * 2. Create a 2D boolean table dp[s1.length + 1][s2.length + 1].
     * 3. dp[0][0] = true (base case: empty strings match empty s3).
     * 4. Initialize first row (using s2): dp[0][j] = dp[0][j-1] && s2[j-1] == s3[j-1].
     * 5. Initialize first column (using s1): dp[i][0] = dp[i-1][0] && s1[i-1] == s3[i-1].
     * 6. Fill the table (i from 1 to len1, j from 1 to len2):
     *    dp[i][j] = (dp[i-1][j] && s1[i-1] == s3[i+j-1]) || 
     *               (dp[i][j-1] && s2[j-1] == s3[i+j-1]).
     * 7. Return dp[s1.length][s2.length].
     */
     public static boolean interleavingStrings(String s1, String s2, String s3) {
         /** Rough Approach for the leetcode problem 
          * We can a build a sliding window of characters in string s3 and check if they're present in either of s1 or s2
          * If the immediate string is not present, we will break it down into substrings and compare
          * After every successfull positioning of a substring, we can mark it true at that positon
          * We can follow this till the end of the string s3
          * We will return the boolean at the end positon of the string
         */

         int s1Len = s1.length(), s2Len = s2.length();

         // Length check: s3 must be exactly the sum of s1 and s2 lengths
         if (s1Len + s2Len != s3.length()) return false;

         //boolean 2D array for storing the values
         boolean[][] dp = new boolean[s1Len+1][s2Len+1];

         //base case
         dp[0][0] = true;

         //filling first row, using s2
         // CHANGED: i <= s2Len to ensure the full row/column is filled
         for(int j = 1; j <= s2Len; j++){
            dp[0][j] = dp[0][j-1] && (s2.charAt(j-1) == s3.charAt(j-1));
         }

         //filling first column, using s1
         for(int i = 1; i <= s1Len; i++){
            dp[i][0] = dp[i-1][0] && (s1.charAt(i-1) == s3.charAt(i-1));
         }

         //populating the rest of the table
         for(int i = 1; i <= s1Len; i++){
            for(int j = 1; j <= s2Len; j++){
                char c = s3.charAt(i+j-1);
                dp[i][j] = (dp[i-1][j] && (s1.charAt(i-1) == c)) || (dp[i][j-1] && (s2.charAt(j-1) == c));

            }
        }

        return dp[s1Len][s2Len];
    }

    // Approach: 1D Dynamic Programming (Space Optimized)
    public static boolean isInterleave1DDP(String s1, String s2, String s3) {
        int s1Len = s1.length(), s2Len = s2.length(), s3Len = s3.length();
        if (s1Len + s2Len != s3Len) return false;

        boolean[] dp = new boolean[s2Len + 1];
        dp[0] = true;

        // Initialize for i=0 (only s2)
        for (int j = 1; j <= s2Len; j++) {
            dp[j] = dp[j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }

        for (int i = 1; i <= s1Len; i++) {
            // Update dp[0] for current i (only s1)
            dp[0] = dp[0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
            for (int j = 1; j <= s2Len; j++) {
                char c = s3.charAt(i + j - 1);
                dp[j] = (dp[j] && (s1.charAt(i - 1) == c)) ||
                        (dp[j - 1] && (s2.charAt(j - 1) == c));
            }
        }
        return dp[s2Len];
    }
}
