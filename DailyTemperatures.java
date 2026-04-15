import java.util.Stack;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        var result = dailyTemperatures(temperatures);
        System.out.println("the next highest temperature will be at: ");
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }

    public static int[] dailyTemperatures(int[] temperatures){
        int arrSize = temperatures.length;
        int[] result = new int[arrSize];
        Stack<Integer> maxTempCheckingStack = new Stack<>();
        for(int i=arrSize-1;i>=0;i--){
            int count = 0;
            int top = maxTempCheckingStack.isEmpty() ? 0 : maxTempCheckingStack.peek();
            while(!maxTempCheckingStack.isEmpty() && temperatures[i]>temperatures[top]){
                maxTempCheckingStack.pop();
            }
            result[i] = maxTempCheckingStack.isEmpty() ? top : top - i;
            maxTempCheckingStack.push(i);
        }

        return result;
    }
}
