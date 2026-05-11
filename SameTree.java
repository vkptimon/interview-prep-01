public class SameTree {
    public static void main(String[] args) {

    }

    /**
     * <pre>
     * function isSameTree(p, q):
     *      if both null → true
     *      if one null → false  
     *      if values differ → false
     *      return isSameTree(p.left, q.left) AND isSameTree(p.right, q.right)
     * </pre>
     * @param p
     * @param q
     * @return boolean
     */

    public static boolean isSameTree(TreeNode p, TreeNode q){
        /** Rough Algorithm
         * We can traverse through both the given trees at the same time, in pre-order/in-order way
         * If the both the nodes of the tree are null, then we will return true, since they both don't have the nodes at that position
         * If during the traversal, one of the trees have a null node, then we will return false, since they don't have the same structure
         * Similarly,if the values in the nodes we are checking is not same, then return false
         * We will do this until the shortest tree is completely traversed - WRONG
         * We will perform this check recursively, for both left and right subtrees
         */

        // this is our base case
        if(p == null && q == null)
            return true;

        //if only one of them is having a null node, false
        if(p == null || q == null)
            return false;

        if(p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
}
