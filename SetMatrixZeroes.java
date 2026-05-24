import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,3},{3,4,0,2},{1,3,1,5}};
        setZeroesLinear(matrix);

        for(int i = 0; i < matrix.length; i++){
            System.out.println("row no: "+i);
            for(int j = 0; j < matrix[i].length; j++){
                System.out.println(matrix[i][j]+ " ");
            }
        }
    }

    public static void setZeroes(int[][] matrix){
        /** Rough Algorithm
         * Perform a bfs to get the indices of the rows and columns having zeroes in them
         * We will store the indices of rows and columns in seperate arrays
         * Loop through and convert all the elements in the mentioned indices to zeroes
         * 
         * Now, i need to use a solution that will use linear space
         */

        Set<Integer> rowIndicies = new HashSet<>();
        Set<Integer> colIndices = new HashSet<>();

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == 0 && (!rowIndicies.contains(i) || !colIndices.contains(j)) ){
                    rowIndicies.add(i);
                    colIndices.add(j);
                }
            }
        }

        //setting based on row indices
        for(int index: rowIndicies){
            for(int j = 0; j < matrix[index].length; j++){
                matrix[index][j] = 0;
            }
        }

        //setting based on column indices
        for(int index : colIndices){
            for(int i = 0; i < matrix.length; i++){
                matrix[i][index] = 0;
            }
        }
    }

    /**
     * Sets matrix zeroes in O(1) space (in-place).
     * 
     * Pseudocode:
     * 1. Check if first row has any zero, store in firstRowHasZero
     * 2. Check if first column has any zero, store in firstColHasZero
     * 3. For i from 1 to rows-1, j from 1 to cols-1:
     *    - If matrix[i][j] == 0:
     *      - Set matrix[i][0] = 0 (mark row i)
     *      - Set matrix[0][j] = 0 (mark column j)
     * 4. For i from 1 to rows-1, j from 1 to cols-1:
     *    - If matrix[i][0] == 0 or matrix[0][j] == 0:
     *      - Set matrix[i][j] = 0
     * 5. If firstRowHasZero: zero out first row (matrix[0][j] for all j)
     * 6. If firstColHasZero: zero out first column (matrix[i][0] for all i)
     */
    public static void setZeroesLinear(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;

        // Check if first row has any zero
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        // Check if first column has any zero
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }

        // Use first row and column as markers for the rest of the matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Zero out cells based on markers (excluding first row and column)
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero out first row if needed
        if (firstRowHasZero) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }

        // Zero out first column if needed
        if (firstColHasZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
