import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        var result = combinationSum(candidates, target);
        System.out.println("the result is: "+result);
    }

    /**
     * <pre>
     * Plain text algorithm
     * 
    result = []
    current = []

    function backtrack(start, remainingTarget):
        if remainingTarget == 0:
            result.add(new ArrayList(current))
            return
        
        if remainingTarget < 0 or start >= candidates.length:
            return
        
        for i from start to candidates.length - 1:
            if candidates[i] > remainingTarget:
                break  // prune (requires sorted array)
            
            current.add(candidates[i])
            backtrack(i, remainingTarget - candidates[i])  // note: i, not i+1
            current.remove(current.size() - 1)  // backtrack
     * </pre>
     * 
     * @param candidates
     * @param target
     * @return
     */

    public static List<List<Integer>> combinationSum(int[] candidates, int target){
        /** Rough Algorithm
         * We can solve this similar to Coin change problem using a dp Array
         * for all the numbers <= target in candidates, we can see if we can sum them for target
         * instead of saving the minimum possible combination, we could add all combinations into the list
         * we need to call the combination method in a recursive fashion
         * a number can be added any number of times, so everytime it's added for the combo, it should be reducted from target
         * if target == 0, return true
         * else if target < 0 || i >= array's length, return false
         */
    
    List<List<Integer>> result = new ArrayList<>();
    
    //sorting the array so early pruning can happen
    Arrays.sort(candidates);

    // FIX 1: currentCombination should track ONE path, not all combinations
    // You need a single list to build the current combination during DFS
    List<Integer> currentCombination = new ArrayList<>();
    
    backtrack(candidates, 0, target, currentCombination, result);
    
    return result;
}

public static void backtrack(int[] candidates, int startIndex, int remainingTarget, 
                             List<Integer> currentCombination, List<List<Integer>> result) {
    // FIX 2: When target reaches 0, you MUST add a COPY of current combination
    if (remainingTarget == 0) {
        // Create a new ArrayList with the current combination's values
        result.add(new ArrayList<>(currentCombination));
        return;
    }
    
    if (remainingTarget < 0) {
        return;
    }
    
    // FIX 3: Start from 'startIndex' not 0, and use 'startIndex' to prevent duplicates
    // Starting from 0 each time would create permutations like [2,3] and [3,2]
    for (int i = startIndex; i < candidates.length; i++) {
        if (candidates[i] > remainingTarget)
            break;
        
        // FIX 4: Add to the path, NOT to a new ArrayList
        currentCombination.add(candidates[i]);
        
        // Pass i (not i+1) because we CAN reuse the same element
        backtrack(candidates, i, remainingTarget - candidates[i], 
                  currentCombination, result);
        
        // Backtrack - remove the last added element
        currentCombination.remove(currentCombination.size() - 1);
    }
}

}
