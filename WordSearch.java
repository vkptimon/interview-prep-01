public class WordSearch {
    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";

        var result = exist(board, word);
        System.out.println("is the word present in the board: "+ result);
    }

    public static boolean exist(char[][] board, String word){
        /**
         * first, we have to loop through each entry of the char grid board, through each row and column
         * when the first character is found and if the dfs from the rest of the word is true, return true
         * in dfs, contains the entire char grid board, int rowIndex, int colIndex, int count(of the number of letters of the word found till now), String word as the parameters
         * the base condition is when the count == word.length-1, then we will return true
         * if the indices i, j are not in bounds or the char value at the indices is not equal to the value in word at the current count index, then we return false
         * else, we will store the value of the current character in a temp variable, so as to avoid performing duplicate checks to the same character
         * and then perform a dfs call in all the four directions of the character and reassign the value in temp variable back to the grid 
         * return the response from the 4 directional search, this is our backtracking step
         */

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0))
                    return true;
            }
        }

        return false;
    }

    public static boolean dfs(char[][] board, int i, int j, String word, int count) {
    // Base case: found all characters
    if (count == word.length() - 1) return true;

    // Check bounds and character match
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(count))
        return false;

    // Mark as visited
    char temp = board[i][j];
    board[i][j] = '#';

    // Try ALL four directions - if ANY works, return true
    if (dfs(board, i+1, j, word, count+1) ||
        dfs(board, i-1, j, word, count+1) ||
        dfs(board, i, j+1, word, count+1) ||
        dfs(board, i, j-1, word, count+1)) {
        return true;
    }

    // Backtrack - restore the character
    board[i][j] = temp;
    return false;
}
}
