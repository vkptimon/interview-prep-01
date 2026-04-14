public class NumOneBits {
    
    public static void main(String[] args) {
        int n = 11;
        int n2 = 2147483645;
        var result = hammingWeight(n2);
        System.out.println("no of 1's are: "+result);
    }

    public static int hammingWeight(int n){
        int result = 0;

        while(n>0){
            int reminder = n%2;
            if(reminder == 1){
                result++;
            }
            n = n/2;
        }

        return result;
    }

}
