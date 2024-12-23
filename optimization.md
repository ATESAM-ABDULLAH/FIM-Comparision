
### **1. Memory Optimization for Counting Items**
**Old Code:**  
- Used a `HashMap<Integer, Integer>` to count item frequencies.  
  - **Time Complexity (per transaction):** \( O(T \cdot \log U) \), where \( T \) is the transaction size and \( U \) is the universe of items (due to `HashMap` lookups).  
  - **Space Complexity:** \( O(U) \) for the map.

**Optimized Code:**  
- Replaced `HashMap` with an integer array `itemCounts[]`, assuming item IDs are sequential integers.
  - **Time Complexity (per transaction):** \( O(T) \), as array indexing is \( O(1) \).  
  - **Space Complexity:** \( O(U) \), same as before but with reduced overhead.

**Improvement:** Array-based counting eliminates hash computation and reallocation overhead.

---

### **2. Sorting Frequent Items**
**Old Code:**  
- Used `Collections.sort()` with a comparator for sorting frequent items.  
  - **Time Complexity:** \( O(F \cdot \log F) \), where \( F \) is the number of frequent items.

**Optimized Code:**  
- Used `Arrays.sort()` on a primitive array of frequent items.  
  - **Time Complexity:** \( O(F \cdot \log F) \), but faster in practice due to lower overhead for primitive types.

**Improvement:** More efficient in terms of runtime constants.

---

### **3. Streaming Transaction Processing**
**Old Code:**  
- Read the entire database into memory, parsed it, and then processed transactions.  
  - **Time Complexity:** \( O(N \cdot T) \), where \( N \) is the number of transactions and \( T \) is the average transaction size.  
  - **Space Complexity:** \( O(N \cdot T) \), as the entire database is stored in memory.

**Optimized Code:**  
- Used `BufferedReader.lines()` to process transactions line-by-line without loading the entire database into memory.  
  - **Time Complexity:** \( O(N \cdot T) \), same as before.  
  - **Space Complexity:** \( O(T) \), as only one transaction is held in memory at a time.

**Improvement:** Reduces peak memory usage, enabling the algorithm to handle larger datasets.

---

### **4. Subset Checking in Candidate Generation**
**Old Code:**  
- Used binary search in a sorted list of frequent itemsets to verify subset frequencies.  
  - **Time Complexity (per candidate):** \( O(K \cdot \log C) \), where \( K \) is the subset size and \( C \) is the number of candidate itemsets.

**Optimized Code:**  
- Introduced a `HashSet` to cache frequent itemsets for constant-time subset checks.  
  - **Time Complexity (per candidate):** \( O(K) \).  
  - **Space Complexity:** Increased to \( O(C) \) due to hash set storage.

**Improvement:** Subset checking is significantly faster, especially for larger candidate sets.

---

### **5. Candidate Generation**
**Old Code:**  
- Created candidates by copying arrays manually and comparing all pairs of itemsets.  
  - **Time Complexity:** \( O(C^2 \cdot K) \), where \( C \) is the number of candidates and \( K \) is the itemset size.

**Optimized Code:**  
- Reused pre-allocated buffers for array copying and limited comparisons to only necessary pairs.  
  - **Time Complexity:** \( O(C^2) \) in general but reduced constants due to fewer allocations.

**Improvement:** Reduced overhead of candidate generation through array reuse.

---

### **6. Memory Logging**
**Old Code:**  
- Logged memory usage after every loop iteration.  
  - **Overhead:** Frequent and redundant logging.

**Optimized Code:**  
- Introduced periodic memory logging based on a configurable interval.  
  - **Overhead:** Negligible compared to the old implementation.

**Improvement:** Reduces logging overhead in scenarios with many iterations.

---

### **Time Complexity Comparison Table**

| **Operation**              | **Old Time Complexity**    | **Optimized Time Complexity**    | **Impact**                        |
|-----------------------------|----------------------------|-----------------------------------|------------------------------------|
| Item counting per transaction | \( O(T \cdot \log U) \)   | \( O(T) \)                        | Faster with primitive array.      |
| Sorting frequent items       | \( O(F \cdot \log F) \)   | \( O(F \cdot \log F) \)           | Same, faster with primitives.     |
| Subset checking              | \( O(K \cdot \log C) \)   | \( O(K) \)                        | Much faster for large \( C \).    |
| Candidate generation         | \( O(C^2 \cdot K) \)      | \( O(C^2) \)                      | Improved due to reuse of buffers. |
| Memory usage logging         | \( O(I) \) (frequent logs)| \( O(I/K) \) (interval logs)      | Reduced log frequency.            |

---

### **Conclusion**
The optimizations significantly improve the algorithm's practical runtime and memory usage while maintaining the same theoretical asymptotic complexities for most operations. These changes make the Apriori implementation scalable to larger datasets. Let me know if you need further clarifications or additional performance metrics!