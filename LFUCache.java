import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class LFUCache {
    int capacity;
    int frequency, minFrequency;

    private Node Head = new Node(null, null);
    private Node Tail = new Node(null, null);

    LFUCache(int capacity){
        this.capacity = capacity;
        this.minFrequency = 1;  // FIX: Initialize minFrequency to 1 for new cache
        Head.next = Tail;
        Tail.prev = Head;
    }

    //Map for storing the node order or the cache
    Map<Integer, Node> lfuMap = new HashMap<>();  // FIX: Changed key type from String to Integer
    //Map for storing the corresponding list to a frequency
    Map<Integer, List<Node>> frequencyMap = new HashMap<>();

    // internal operations for maintaining the order inside the cache
    public void addReference(Node n){
        //linking to head
        n.next = Head.next;
        n.prev = Head;
        Head.next.prev = n;
        Head.next = n;
    }

    public void updateReference(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;

        addReference(n);
    }

    public void removeReference(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    //method to handle minFrequency value across operations
    // FIX: Removed unused method - minFrequency is now handled inline in get() and put()
    // Previously: This method had incorrect logic and wasn't being used properly
    public int handleMinFrequency(int frequency, Map<Integer, List<Node>> frequencyMap){
        if(frequencyMap.get(frequency).isEmpty() && minFrequency == frequency)
            minFrequency++;
        minFrequency = Math.min(minFrequency, frequency);
        return minFrequency;
    }

    // map operations of get() and put()
    public int get(int key){
        if(lfuMap.containsKey(key)){
            Node existing = lfuMap.get(key);
            int currentFreq = existing.frequency;
            
            // FIX: Remove node from its current frequency list before updating
            // Previously: Node remained in old frequency list, causing inconsistency
            frequencyMap.get(currentFreq).remove(existing);
            
            // FIX: Update node's frequency and add to new frequency list
            // Previously: frequency was global counter, not per-node
            existing.frequency = currentFreq + 1;
            
            // FIX: Add to new frequency list, create if doesn't exist
            // Previously: Always created new list, losing existing nodes
            frequencyMap.computeIfAbsent(currentFreq + 1, k -> new ArrayList<>()).add(existing);
            
            // FIX: Update minFrequency if current frequency list is now empty
            // Previously: minFrequency logic was incorrect
            if(frequencyMap.get(currentFreq).isEmpty() && minFrequency == currentFreq) {
                minFrequency = currentFreq + 1;
            }
            
            updateReference(existing);
            return existing.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value){
        if(capacity <= 0) return; // Edge case: zero capacity
        
        if(lfuMap.containsKey(key)){
            // Update existing node
            Node existing = lfuMap.get(key);
            existing.value = value;
            
            // FIX: Same frequency update logic as get()
            // Previously: frequency was global counter, not per-node
            int currentFreq = existing.frequency;
            frequencyMap.get(currentFreq).remove(existing);
            existing.frequency = currentFreq + 1;
            frequencyMap.computeIfAbsent(currentFreq + 1, k -> new ArrayList<>()).add(existing);
            
            if(frequencyMap.get(currentFreq).isEmpty() && minFrequency == currentFreq) {
                minFrequency = currentFreq + 1;
            }
            
            updateReference(existing);
        } else {
            // Add new node
            if(lfuMap.size() >= capacity){
                // FIX: Remove LFU node from both map and frequency list
                // Previously: Only removed from linked list, not from frequencyMap
                Node removalNode = frequencyMap.get(minFrequency).get(0);
                lfuMap.remove(removalNode.key);
                frequencyMap.get(minFrequency).remove(0);
                removeReference(removalNode);
            }
            
            Node newNode = new Node(key, value);
            newNode.frequency = 1; // New nodes start with frequency 1
            lfuMap.put(key, newNode);
            addReference(newNode);
            frequencyMap.computeIfAbsent(1, k -> new ArrayList<>()).add(newNode);
            minFrequency = 1; // New node has frequency 1, which is the new minimum
        }
    }

}

class Node{
    int key, value, frequency;  // FIX: Added frequency field to track each node's access count
    Node prev, next;              // Previously: frequency was global, not per-node

    Node(int key, int value){
        this.key = key;
        this.value = value;
        this.frequency = 1;       // FIX: Initialize frequency to 1 for new nodes
    }

    Node(Node prev, Node next){
        this.prev = prev;
        this.next = next;
    }
}
