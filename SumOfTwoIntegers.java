public class SumOfTwoIntegers {
 
 public static void main(String[] args) {
    int a = 4;
    int b = 7;
    int result = getSum(a, b);
    System.out.println("addition result is: "+result);
 }
    public static int getSum(int a, int b){
        /** Rough Approach
         * We have to perform bit manipulation to achieve the result
         * Passively, we have to implement an adder logic to perform this summation
         * 
         * We have to calculate the sum bit by performing XOR operation between given integers
         * Then we have to calculate the carry by performing the and operation between those integers
         * We have to perform left shift by 1 position - need to know why
         * We have to repeat this operation until the carry is zero, performing the operation on the sum and the calculated carry
         */

        while( b != 0) {
            //calculating carry
            int carry = a & b;

            //perform xor between the numbers for sum
            a = a ^ b;

            //left shift the carry by a position
            b = carry << 1;
        }

        return a;
    }
}
