import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
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
}
