
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
    public static void main(String[] args) {
        
    }

    public static void push(int val){

    }
    
    public static void pop(){

    }

    public static int top(){

    }

    public static int getMin(){

    }
}
