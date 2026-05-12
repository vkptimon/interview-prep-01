public class ClimbingStairs {
    public static void main(String[] args) {
        int n=4;
        var result = climbStairs(n);
        System.out.println("number of possible ways are: "+result);
        var resultItr = climbStairsItr(n);
        System.out.println("number of ways to climb stairs, calculated in itr mode are: "+resultItr);
    }

    public static int climbStairs(int n){
        /**
         * based on the pattern, i've understood that i to implement recursion
         * every number can be divided into 1+n and 2+(n-1) and the number of choices of it can be combined to form the solution
         * or we could simply calculate the fibonacci value of the given number cause the values of both coincide
         * wil try to implement the former approach and then if it's taking a lot of time, will implement the latter
         */
        //base conditions
        if(n == 1)
            return 1;
        else if(n == 2)
            return 2;
        else{
            int stepsBy1 = climbStairs(n-1); // 1+cs(n-1)
            int stepsBy2 = climbStairs(n-2); // 2+cs(n-2)
            return stepsBy1+stepsBy2;
        }
    }

    public static int climbStairsItr(int n){
        //recursive approach failed when n=45 in leetcode, so implemented the iterative version of it
        //base conditions
        if(n == 1)
            return 1;
        if(n == 2)
            return 2;
        
        int result=0;
        int os=1, ts=2;
        for(int i=3; i<=n; i++){
            result = ts + os;
            os = ts;
            ts = result;
        }

        return result;
    }
}
