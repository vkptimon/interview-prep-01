public class PalindromeSubstring {

    public static void main(String[] args) {
        String str = "abbcdda";
        var result = countSubstrings(str);
        System.out.println("the total palindromic substrings are: "+result);
    }


    /*
    algorithm: 
    - iterate through each character and perform expanding assuming it's an odd-length and even-length string
    - in the expand-around-center method, we will check if the left and right pointers are present in the boundaries and
    then check if the characters at those positions are same
    - if so, then we will move towards the left and right, growing outward and then incrementing the count
    - and then we will finally return the count, obtain from both odd-length and even-length ones
    */
    public static int countSubstrings(String str){
        int totalCount = 0;
        for(int i=0; i<str.length(); i++){
            // for odd-length palindromes
            totalCount += expandAroundCenter(str, i, i);
            // for even-length palindromes
            totalCount += expandAroundCenter(str, i, i+1);
        }

        return totalCount;
    }

    public static int expandAroundCenter(String str, int left, int right){
        int count=0;
        while(left>=0 && right<str.length() && str.charAt(left)==str.charAt(right)){
            left--; //moving towards left, expanding
            right++; //moving towards right, expanding
            count++;
        }
        return count;
    }

    public static boolean isPalindrome(String subString){
        int subStrSize = subString.length();
        int endBoundary = subStrSize%2 != 0 ? subStrSize/2 : (subStrSize-1)/2;
        for(int i=0; i<=endBoundary; i++){
            if(subString.charAt(i) != subString.charAt(subStrSize-i-1)){
                return false;
            }
        }
        return true;
    }

}
