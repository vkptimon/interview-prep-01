import java.util.HashMap;
import java.util.Map;

public class LRUCache{
    int capacity;
    Map<Integer, Node> lruMap = new HashMap<>();

    private static Node Head=new Node(0, 0, null, null);
    private static Node Tail=new Node(0, 0, null, null);
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        Head.next = Tail;
        Tail.prev = Head;
    }

    public void addReference(Node n){
        //pointing head to current node
        n.next = Head.next;
        n.prev = Head;
        Head.next.prev = n;
        Head.next = n;
    }
    
    public void updateReference(Node n){
        //breaking off
        n.prev.next = n.next; //pointing the back to the front
        n.next.prev = n.prev; //poniting the front to the back
    
        //inserting n beside HEAD
        addReference(n);
    }
    
    public void removeReference(Node n){
        //re-routing the node to be deleted out of the list
        n.prev.next = Tail;
        Tail.prev = n.prev;
    }
    
    public int get(int key) {
        if(lruMap.containsKey(key)){
            Node node=lruMap.get(key);
            this.updateReference(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
     Node node=new Node(key, value);
        if(lruMap.containsKey(key)){
            lruMap.put(key, node);
            updateReference(node);
        }else if(!lruMap.containsKey(key) && lruMap.size() >= capacity){
            // removing the last node from list and the map
            Node lastNode = Tail.prev;
            this.removeReference(lastNode);
            lruMap.remove(key);
            // adding the new node in it's place
            this.addReference(node);
            lruMap.put(key, node);
        }
        // this.updateReference(node);
    }

    public static void main(String[] args) {
        LRUCache cacheInstance = new LRUCache(4);
        cacheInstance.put(1,1);
        cacheInstance.put(2,2);
        cacheInstance.put(3, 4);
        System.out.println(cacheInstance.get(2));
        cacheInstance.put(3, 6);
        cacheInstance.put(4,1);
        System.out.println(cacheInstance.get(2));
    }
}

class Node{
    int key, value;
    Node prev, next;

    Node(int key, int value){
        this.key = key;
        this.value = value;
    }
    
    Node(int key, int value, Node prev, Node next){
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

}