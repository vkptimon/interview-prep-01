import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 5, 10};
        int[] coins2 = {1, 3, 4};
        int amount = 12;
        int amount2 = 6;
        var result = coinChange(coins2, amount2);
        System.out.println("total coins used for the amount is: "+result);
    }

    /** Rough Algorithm
     * for the given amount, we will divide it by the largest number in the coins array
     * assuming the coins array is sorted, we will check from the end position
     * if the amount > coins[last], we will loop through and decrement it till amount < coins[last]
     * also simulatenously increment the count of coinCount in the loop
     * then we will check with last-1 and so until either the array is empty or amount becomes zero
     * if the amount is non-zero, +ve or -ve value, then we will return '-1', else return the coinCount
     
     * the above is a greedy algorithm and it doesn't work all the cases; we need to use bottom up DP
     * we have to build any array from 1 to amount, with the least amount of coins the value can be made up of
     * for all the coins which are less than the value, calculate dp[value] = min(dp[value], dp[value-coin]+1)
     * we will assign the rest of the elements of the array with 'amount+1', so that only the min values prevail
     * if there's no coin can be used, then it would still have the value greater than amount
     * finally if dp[amount] > amount, return -1
     */
    public static int coinChange(int[] coins, int amount){

        int[] dp = new int[amount+1];
        //all the values in the array should be greater than amount
        Arrays.fill(dp, amount+1);
        //set the base condition as 0
        dp[0] = 0;

        for(int i=1; i<=amount; i++){
            for(int coin:coins){
                if(coin <= i)
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
        }

        return dp[amount]>amount ? -1 : dp[amount];
    }
}
