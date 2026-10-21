// Count the frequency of each digit

import java.util.HashMap;

public class P3 {
    public static void main(String[] args) {
        CountDigitFrequency();
    }

    public static void CountDigitFrequency() {
        int num = 11233;
        HashMap<Integer, Integer> freq = new HashMap<>();

        while (num > 0) {
            int digit = num % 10;

            freq.put(digit, freq.getOrDefault(digit, 0) + 1);

            num = num / 10;
        }

        System.out.println(freq);
    }
}
