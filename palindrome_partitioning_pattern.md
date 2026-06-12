# LeetCode 131: Palindrome Partitioning

**Date:** 2026-06-12

---

## 1. Initial Thought Summary
- Attempted to apply a sliding window mechanism. 
- Realized a linear sliding window cannot handle multiple branching possibilities since one partition decision alters all subsequent valid splits.

---

## 2. Mistakes Made
- **Shallow Copy Bug:** Added the mutable path variable directly to results (`allPartitions.add(currPath)`). Since the path is mutated in-place and eventually emptied, the results list ended up full of empty lists.
- **Reference Copy Misconception:** Attempted to clone using `copyList = currPath`, which only copies the reference pointer, not the underlying array list.
- **Premature Base Condition:** Halted traversal when `start_index == length - 1`. This stopped recursion before the last character could be evaluated and appended.

---

## 3. Hints Received
- Use a decision tree model where you partition a valid prefix and recursively process the remaining suffix.
- Instantiate a new list (`new ArrayList<>(currPath)`) to store independent snapshots of the partition state.
- Change the recursion termination check to `start_index == inputString.length()`.

---

## 4. Final Approach
- Implement Depth-First Search (DFS) with backtracking.
- Loop through potential end positions for the current partition.
- If the current substring is a palindrome:
  1. **Choose**: Add the substring to the active path.
  2. **Explore**: Call recursion on the next index.
  3. **Revert**: Remove the substring from the path to restore the state for the next loop iteration.
- If the start index reaches the end of the string, create a new `ArrayList` copy of `currPath` and add it to the final output list.

---

## 5. ASCII Visualization
```text
State space exploration tree for "aab":

Level 0: backtrack(s, start=0, path=[])
 │
 ├── i=0: substring "a" (Palindrome) -> path=["a"]
 │    └── Level 1: backtrack(s, start=1, path=["a"])
 │         │
 │         ├── i=1: substring "a" (Palindrome) -> path=["a", "a"]
 │         │    └── Level 2: backtrack(s, start=2, path=["a", "a"])
 │         │         │
 │         │         └── i=2: substring "b" (Palindrome) -> path=["a", "a", "b"]
 │         │              └── Level 3: backtrack(s, start=3) -> REACHED END!
 │         │                   └── Clone & Save: ["a", "a", "b"]
 │         │
 │         └── i=2: substring "ab" (Not Palindrome) -> Skip
 │
 └── i=1: substring "aa" (Palindrome) -> path=["aa"]
      └── Level 1: backtrack(s, start=2, path=["aa"])
           │
           └── i=2: substring "b" (Palindrome) -> path=["aa", "b"]
                └── Level 2: backtrack(s, start=3) -> REACHED END!
                     └── Clone & Save: ["aa", "b"]
```

---

## 6. Pattern & Recognition Signals
### **Pattern:** Backtracking / Combinatorial DFS
- **All Permutations/Combinations:** The problem explicitly demands returning *every single possible* combination/partition rather than a count or a single optimized output.
- **State Restoration:** Taking a choice at step $N$ modifies the state for step $N+1$, and we must clean up that choice before exploring sibling branches.

---

## 7. Complexity Analysis
- **Time Complexity:** $O(N \cdot 2^N)$ where $N$ is the length of the string. There are $2^{N-1}$ possible partitioning paths, and for each path, checking if it is a palindrome and copying the list takes $O(N)$ time.
- **Space Complexity:** $O(N)$ stack depth for recursion.

---

## 8. Related Problems
- **[Restore IP Addresses (LeetCode 93)](https://leetcode.com/problems/restore-ip-addresses/)** (Backtracking split choices on a string with constraints)
- **[Subsets (LeetCode 78)](https://leetcode.com/problems/subsets/)** (Classic backtracking build of element combinations)
- **[Word Search (LeetCode 79)](https://leetcode.com/problems/word-search/)** (Grid-based pathfinding with backtracking state restoration)

---

## 9. Revisit Schedule
- **First Review:** 3 Days (June 15, 2026)
- **Second Review:** 1 Week (June 19, 2026)
- **Third Review:** 1 Month (July 12, 2026)
