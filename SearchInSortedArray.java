public class SearchInSortedArray {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;

        var result = search(nums, target);
        System.out.println("the element is present in the array:"+result);
    }

    public static int search(int[] nums, int target){

        int low = 0, high = nums.length-1;
        int mid = (low+high)/2;

        if(nums[low] <= nums[mid]){ //left half is sorted
            if(nums[low] <= target && target < nums[mid])
                high = mid-1; //target in left half
            else
                low = mid+1; //target in right half
        } else { //right half is sorted
            if(nums[mid] < target && target <= nums[high])
                low = mid-1; //target in right half
            else
                high = mid+1; //target in left half
        }

        return mid = mid >= nums.length ? -1 : mid;
    }
}
