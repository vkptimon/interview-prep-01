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
         * 
         * we basically have to divide this question into 3 cases
         * case 1: we need to find all the intervals that end BEFORE the start of newInterval, and add as is
         * case 2: we need to check all the intervals that will overlap with newInterval
         * based on that we need to update the bounds of newInterval
         * case 3: we need to find all the intervals that start AFTER the end of newInterval, and add as is
         * between intervals (a,b), (c,d) they overlop if max(a,c) <= min(b,d)
         * if they do overlap, we need to merge the intervals into [min(a,c), max(b,d)];
         */

        // int[][] result = new int[intervals.length][2];
        List<int[]> result = new ArrayList<>();

        int i = 0;
        
        //case 1
        while(i < intervals.length && (intervals[i][1] < newInterval[0]) ) {
            result.add(intervals[i]);
            i++;
        }

        //case 2
        while(i < intervals.length && (Math.max(intervals[i][0], newInterval[0]) <= Math.min(intervals[i][1], newInterval[1])) ){
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        result.add(newInterval);

        //case 3
        while(i < intervals.length && (intervals[i][0] > newInterval[1]) ){
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}
