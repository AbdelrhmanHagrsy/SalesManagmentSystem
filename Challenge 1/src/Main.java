import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};
        int target = 9;
        HashMap<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int expectedKey = target - nums[i];
            if (map.containsKey(expectedKey)) {
                System.out.println("First index :"+ map.get(expectedKey));
                System.out.println("Seconed index :"+ i);
            }
            map.put(nums[i], i);
        }
    }

}