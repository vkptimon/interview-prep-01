public class RotateImage {
    public static void main(String[] args) {

    }

    /**
     * <pre>
       function rotate(matrix):
        n = matrix.length
        
        # Process each layer (there are n/2 layers)
        for layer from 0 to (n/2) - 1:
            first = layer
            last = n - 1 - layer
            
            # Process each group of 4 in this layer
            for offset from 0 to (last - first - 1):
                # Save top-left
                topLeft = matrix[first][first + offset]
                
                # Move bottom-left to top-left
                matrix[first][first + offset] = matrix[last - offset][first]
                
                # Move bottom-right to bottom-left
                matrix[last - offset][first] = matrix[last][last - offset]
                
                # Move top-right to bottom-right
                matrix[last][last - offset] = matrix[first + offset][last]
                
                # Move saved top-left to top-right
                matrix[first + offset][last] = topLeft
        
        return matrix
     * </pre>
     * 
     * @param matrix
     */
    public static void rotate(int[][] matrix){
        /** Rough Algorithm
         * We can loop through each entry and add the 1st, 2nd.... jth element into the corresponding list at jth position
         * To be more precise we have to place matrix[size - i - 1][j] at result[j][i]
         * 
         * this is a good approach but we have to do inplace, which defeats the purpose of the question
         * the better way to solve it is to use swapping between elements
         * first we have to swap the elements in the outlayer, which consists of firstrow x firstcolumn x lastcolumn x lastrow
         * in which the edge elements can be swapped, 
         * after which we will swap the next elements in all the 4 sides of the matrix and so on
         * performing this for half the layers would cover almost all the elements in a rotated manner
         * the rotation order would be of following order:
         * (row, col) -> (col, n - row - 1) -> (n - row - 1, n - col - 1) -> (n - col - 1, row) -> (row, col)
         */
        int size = matrix.length;

        for(int i = 0; i < (size + 1)/2; i++){
            for(int j = 0; j < size/2; j++){
                //temp = bottom left
                int temp = matrix[size - j - 1][i];
                //bottom left has bottom right
                matrix[size - j - 1][i] = matrix[size - i - 1][size - j - 1];
                //bottom right has top right
                matrix[size - i - 1][size - j - 1] = matrix[j][size - i - 1];
                //top right has top left
                matrix[j][size - i - 1] = matrix[i][j];
                //top left has temp(bottom left)
                matrix[i][j] = temp;

            }
        }
    }

}
