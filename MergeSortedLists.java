public class MergeSortedLists {
    public static void main(String[] args) {
        // ListNode list1 = [1,2,4];
        // ListNode list2 = [1,3,5];
        // var result = mergeTwoLists(list1, list2);
        // System.out.println("the output merged list is: "+result);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2){
        /*
        The rought algorithm
        - we will maintain two pointers for both the lists and compare the elements based on that
        - first we will create a dummy node and a current node and assign the current node to the dummy
        - then we will loop through the lists, until one of them reachs the end(i.e., null)
        - if the value in the list1 node is <= list2, then add it to the merged list and propogate it to the next node
        - vice-verse for the other case too
        - if any of the lists are non-empty, then we need to attach them as is
        */

        ListNode mergedList = new ListNode(0, null);
        ListNode currNode = new ListNode();
        mergedList = currNode;

        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                currNode.next = list1;
                list1 = list1.next;
            } else {
                currNode.next = list2;
                list2 = list2.next;
            }

            // after every loop, we need to increment the current node
            currNode = currNode.next;
        }

        // if any of the lists are null, then we will attach them to the merged list
        if(list1 != null){
            currNode.next = list1;
        } else {
            currNode.next = list2;
        }

        return mergedList.next;
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val=val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next;}
}
