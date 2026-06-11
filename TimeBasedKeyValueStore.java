import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStore {
    
    class TimeMap{
        
        /** Rough Approach
         * Concatenating the key and timestamp is tedious because both are of different types, string and int
         * Retreival using that would be tedious
         * So, we can keep a list of two elements, the key and the timestamp inside
         * When we have a retreival call, we will thoroughly check the contents of the list and then return the associated value
         * 
         * From the hints given, we can make a map as the key, comprising of key and timestamp
         * But we are sure that the key and timestamp combo would receive a unique value, so this also fails
         * The second hints helps us that we need to implement "binary search" to get the correct timestamp associated with the key
         * But the thing is, i'm not sure about the data structure to be implemented for it
         */

        String key;
        class TimeEntry{
            String value;
            int timestamp;

            public TimeEntry(String value, int timestamp){
                this.value = value;
                this.timestamp = timestamp;
            }
        }

        Map<String, List<TimeEntry>> kvStore = new HashMap<>();
        
        public TimeMap() {}

        public String get(String key, int timestamp){
            if(!kvStore.containsKey(key))
                return "";

            List<TimeEntry> retreivedList = kvStore.get(key);

            //implementing the binary search
            int low = 0, high = retreivedList.size()-1;
            String candidateString = "";

            while(low <= high){
                int mid = (low + high)/2;
                TimeEntry currEntry = retreivedList.get(mid);

                if(currEntry.timestamp == timestamp)
                    return currEntry.value;
                if(currEntry.timestamp < timestamp){
                    candidateString = currEntry.value;
                    low = mid+1;
                } else{
                    high = mid-1;
                }
            }
            return candidateString;
        }

        public void set(String key, String value, int timestamp){
            if(!kvStore.containsKey(key)){
                TimeEntry te = new TimeEntry(value, timestamp);
                List<TimeEntry> newTE = new ArrayList<>();
                newTE.add(te);
                kvStore.putIfAbsent(key, newTE);
            }
            TimeEntry currte = new TimeEntry(value, timestamp);
            List<TimeEntry> currTE = kvStore.get(key);
            currTE.add(currte);
            kvStore.put(key, currTE);
        }
    }
}
