import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {
    int capacity;
    Map<Integer, Node> lruMap = new HashMap<>();
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(lruMap.containsKey(key)){
            Node node=lruMap.get(key);
            node.updateReference(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
     Node node=new Node();
        if(lruMap.size()<=capacity){
        ;
        lruMap.put(key, node);
        node.updateReference(node);
    }
     node.removeReference(node);
     node.updateReference(node);   
    }
}

class Node{
    int key, value;
    Node prev, next;

    Node(){}

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
    
    Node Head=new Node(0, 0, null, null);
    Node Tail=new Node(0, 0, null, null);

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
}