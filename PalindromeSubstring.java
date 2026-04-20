public class PalindromeSubstring {

    public static void main(String[] args) {
        String str = "abbcdda";
        var result = countSubstrings(str);
        System.out.println("the total palindromic substrings are: "+result);
    }


    /*
    algorithm: 
    - traverse through the string
    - find a window, where it's a palindrome and keep incrementing the second pointer and calculate the sum of all possible combinations
    (equals to sum of natural numbers till the lenght of the substring)
    - when the window ends, set the first pointer to the end of window and increment the second pointer
    - add the count to totalCount at the end of each round
    */
    public static int countSubstrings(String str){
        int totalCount = 0;
        int first = 0, second = 0;
        while(first<second && second<str.length()){
            int count = 0;
            if(isPalindrome(str.substring(first, second))){
                second++;
                count = calSummationValue(second-first+1);
            }else{
                first = second;
                second++;
            }
            totalCount+=count;
        }

        return totalCount;
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

    public static int calSummationValue(int subStringSize){
        return (subStringSize*subStringSize+1)/2;
    }
}
