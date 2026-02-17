import java.util.*;

public class PalindromeProblem {

    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       System.out.println("enter the string: ");
       String inputStr = sc.nextLine();
       System.out.println("enter the size of the string: "); 
       int n = sc.nextInt();
       System.out.println("enter the max no of changes allowed: ");
       int k = sc.nextInt();

       String result = highestValuePalindrome(inputStr, n, k);
       System.out.println("the result is: " + result);
    }

    public static String highestValuePalindrome(String s, int n, int k) {
    // Write your code here
    String result="";
    int sValueInInt = Integer.parseInt(s);
    int numValue = 0;
    int first=0, last=n-1;
    StringBuilder sBuilder = new StringBuilder(s);
    while(first<last){
        if(!Objects.equals(s.charAt(first), s.charAt(last))){
            if(k>1){
                numValue = conversionLogic(s, sBuilder, first, last);
                k=k-2;
            }else if(k==1){
                numValue = conversionLogic(s, sBuilder, first, last);
                k=k-1;
            }else
                break;
        }
        
        first++;
        last--;
    }

    if((numValue > sValueInInt) && (checkPalindrome(s, n))){
        return s;
    }else{
        return "-1";
    }
    }
    
    public static int conversionLogic(String s, StringBuilder sBuilder, int first, int last){
        int numValue = 0;
        if(sBuilder.charAt(first)!='9' && sBuilder.charAt(last)!='9'){
            sBuilder.setCharAt(first, '9');
            sBuilder.setCharAt(last, '9');
            numValue = Integer.parseInt(s);
        }else if(s.charAt(first)=='9' || s.charAt(last)=='9'){
            sBuilder.setCharAt(first, '9');
            sBuilder.setCharAt(last, '9');
            numValue = Integer.parseInt(s);
        }
        return numValue;
    }

    public static boolean checkPalindrome(String checkString, int n){
        boolean result=true;
        
        int first=0, last=n-1;
        while(first<last){
            if(!Objects.equals(checkString.charAt(first), checkString.charAt(last)))
                return false;
            first++;
            last--;
        }
        return result;
    }
}