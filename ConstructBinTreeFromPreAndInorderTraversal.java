import java.util.HashMap;
import java.util.Map;

public class ConstructBinTreeFromPreAndInorderTraversal {
    public static void main(String[] args) {
        
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder){
        /** Rough Algorithm
         * The first element of the preorder is always the root node
         * All the elements before it in inorder can be categorized under the left subtree and the later in right subtree
         * Similarly, the next element in the preorder can be the parent/root node of the left subtree if it's the condition
         * If it has any elements to the left/right/both in inorder, else it's a leaf node
         * We can find the root and call the same method recursively for the left and right subtrees
         * 
         * We have to deal with the indices rather than the actual values
         * We can use markers like preStart, preEnd, inStart, inEnd, inRoot
         * The size of the left subtree would be `inRoot - inStart`
         * now left subtree can be built using indices (preStart + 1, preStart + 1 + leftSize)
         * similarly right subtree can be built using indices (preStart + 1 + leftSize, preEnd + 1)
         */


        //creating a hashmap for indexing
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }

        //building the subtree
        return buildSubTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, inorderMap);
    }

    public static TreeNode buildSubTree(int[] preorder, int preStart, int preEnd, 
        int[] inorder, int inStart, int inEnd,
        Map<Integer, Integer> inorderMap){

            //base case
            if(preStart > preEnd || inStart > inEnd)
                return null;

            //root node
            int rootVal = preorder[preStart];
            TreeNode rootNode = new TreeNode(rootVal);

            //finding rootNode's position in inorder list
            int rootInorderIndex = inorderMap.get(rootVal);

            //calculating the left subtree size
            int leftSubTreeSize = rootInorderIndex - inStart;

            //building left subtree
            rootNode.left = buildSubTree(preorder, preStart + 1, preStart + leftSubTreeSize, 
                inorder, inStart, rootInorderIndex - 1, inorderMap);

            //building right subtree
            rootNode.right = buildSubTree(preorder, preStart + leftSubTreeSize + 1, preEnd, 
                inorder, rootInorderIndex + 1, inEnd, inorderMap);

            return rootNode;

        }

        
}
