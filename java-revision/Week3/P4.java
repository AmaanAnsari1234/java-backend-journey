// the first non-repeating digit

import java.util.HashMap;

public class P4 {
    public static void main(String[] args) {
        FirstNonRepeatingDigit();
    }

    public static void FirstNonRepeatingDigit() {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int num = 11233;
        int rev = 0;

        // First loop: reverse the number and count digits
        while (num > 0) {
            int digit = num % 10;
            rev = rev * 10 + digit;

            freq.put(digit, freq.getOrDefault(digit, 0) + 1);

            num = num / 10;
        }

        // Second loop: find the first non-repeating digit
        while (rev > 0) {
            int digit = rev % 10;

            if (freq.get(digit) == 1) {
                System.out.println(digit);
                return;
            }

            rev = rev / 10;
        }

        System.out.println("No non-repeating digit found.");
    }
}
