# LeetCode 981: Time Based Key-Value Store

**Date:** 2026-06-11

---

## 1. Initial Thought Summary
- Attempted to concatenate the string key and integer timestamp into a single key or keep a single flat list containing all keys and timestamps globally.
- Intended to run a linear search across the entire dataset to retrieve the target value.

---

## 2. Mistakes Made
- **Global Linear Scanning:** Created a flat list structure that forced $O(N)$ linear scans for every retrieval, leading to performance timeouts (TLE).
- **Map Blockage:** Added a conditional check in `set` that only created the entry list when the key was absent, preventing subsequent insertions of new timestamps for the same key.
- **String Accumulation:** Concatenated values instead of reassigning them during binary search updates (`candidate = candidate + value`), combining multiple values together.

---

## 3. Hints Received
- Group entries by key using a Map of Lists (`Map<String, List<TimeEntry>>`).
- Leverage the strictly increasing order of timestamps to run binary search.
- Overwrite the candidate string rather than using concatenation.

---

## 4. Final Approach
- Use a `HashMap<String, List<TimeEntry>>` for $O(1)$ key clustering.
- For `set(key, value, timestamp)`: Retrieve the list associated with the key and append a new `TimeEntry`. Since timestamps arrive sequentially, the list is naturally sorted.
- For `get(key, timestamp)`: Retrieve the list. If it exists, perform binary search:
  - If `curr.timestamp == target`: Return value immediately.
  - If `curr.timestamp < target`: Save `curr.value` as the new candidate and check the right half (`low = mid + 1`).
  - If `curr.timestamp > target`: Check the left half (`high = mid - 1`).

---

## 5. Pattern & Recognition Signals
### **Pattern:** Binary Search on Monitored Logs
- **Range Queries:** Need to search for the closest value $\le$ or $\ge$ a target index/timestamp.
- **Monotonic Input Sequence:** The metadata (IDs, timestamps, version keys) is generated or written in strictly increasing order.

---

## 6. Complexity Analysis
- **Time Complexity:**
  - `set`: $O(1)$ average.
  - `get`: $O(\log M)$ where $M$ is the number of versions/timestamps for the given key.
- **Space Complexity:** $O(N)$ total space to store all key-value-timestamp records.

---

## 7. Related Problems
- **[Snapshot Array (LeetCode 1146)](https://leetcode.com/problems/snapshot-array/)** (Identical design concept using versioned arrays)
- **[Find First and Last Position of Element in Sorted Array (LeetCode 34)](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)** (Classic binary search boundary conditions)

---

## 8. Revisit Schedule
- **First Review:** 3 Days (June 14, 2026)
- **Second Review:** 1 Week (June 18, 2026)
- **Third Review:** 1 Month (July 11, 2026)
