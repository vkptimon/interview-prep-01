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
        for(int i=0;i<arrSize;i++){
            int top = !maxTempCheckingStack.isEmpty() ? maxTempCheckingStack.peek() : 0;
            while(!maxTempCheckingStack.isEmpty() && temperatures[i]>temperatures[maxTempCheckingStack.peek()]){
                top = maxTempCheckingStack.pop();
                result[top] = i-top;
            }
            maxTempCheckingStack.push(i);
        }

        return result;
    }
}
