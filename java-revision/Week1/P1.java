import java.util.ArrayList;
import java.util.Arrays;

public class P1 {

    public static void main(String[] args) {

        int[] nums = {2, 5, 1, 3, 4, 7};
        int n = 3;

        int[] result = shuffle(nums, n);

        System.out.println("Input:  " + Arrays.toString(nums));
        System.out.println("Output: " + Arrays.toString(result));
    }

    public static int[] shuffle(int[] nums, int n) {

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            result.add(nums[i]);
            result.add(nums[i + n]);
        }

        // Convert ArrayList<Integer> → int[]
        int[] output = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }

        return output;
    }
}