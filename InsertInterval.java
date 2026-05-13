public class InsertInterval {
    public static void main(String[] args) {
        
    }

    public static int[][] insert(int[][] intervals, int[] newInterval){
        /**
         * Rough Algorithm
         * we will traverse through all the list in intervals grid and compare the first and second elements of newInterval to them
         * if newInterval[0] is > intervals[i-1][1] and newInterval[1] < intervals[i][0], then we could insert it in between i-1 and i
         * if the difference between them is 1 and difference between intervals[0][0] and intervals[last-1][1] == length(intervals)*2,
         * then we could merge all of them into intervals[0](intervals[0][0], intervals[last-1][1])
         */
    }
}
