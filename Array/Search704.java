package Array;

public class Search704 {
    public int search(int[] nums, int target){
        int left = 0, right = nums.length - 1; 
        while (left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target){
                return mid; 
            } else if (nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1; 
            }
        }
        return -1; 
    }

    public static void main(String[] args){
        Search704 s = new Search704();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int target = 4; 
        int result = s.search(nums, target);
        System.out.println("target value 4 index in nums is: " + result);
    }
}
