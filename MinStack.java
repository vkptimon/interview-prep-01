import java.util.Stack;

/** Rough Approach
 * The operations push, pop, top can be implemented as is using native stack with O(1) T.C
 * But achieving the same for getMin() is difficult without another stack, which is monotonic
 * It will also be pushed the new element, only if it is less than the current top/peek; Else, not inserted
 * During pop, if the element being removed is the same as peek of this monotonic stack, then we can pop from here too
 * We need to push the duplicate min value to maintain the same depth of the OG stack, so we can avoid conditonal check in pop()
 * 
 * Another way is to store a list of currVal, min(currVal, minVal) and return the second element of the pair when getMin() is invoked
 */


public class MinStack {

    Stack<Integer> mainStack = new Stack<>();
    Stack<Integer> monotonicStack = new Stack<>();

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-1);

        System.out.println("min: "+minStack.getMin());
        System.out.println("top: "+minStack.top());

        minStack.pop();
        System.out.println("min: "+minStack.getMin());
    }

    public void push(int val){
        mainStack.push(val);

        if(monotonicStack.empty())
            monotonicStack.push(val);
        else{
            //inserting the min val again so it would be easier to perform operations with the same depth as mainStack
            monotonicStack.push( Math.min(val, monotonicStack.peek()) );
        }
    }
    
    public void pop(){
        mainStack.pop();

        monotonicStack.pop();
    }

    public int top(){
        return mainStack.peek();
    }

    public int getMin(){
        return monotonicStack.peek();
    }
}
