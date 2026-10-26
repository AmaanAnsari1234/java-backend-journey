package Week4;

import java.util.HashMap;

// Find the first repeating digit -------- num = 123243 --- op:2
public class P1 {
    public static void main(String[] args) {
        FirstRepeatingDigit();

    }

    public static void FirstRepeatingDigit(){
        HashMap<Integer, Integer> freq = new HashMap<>();
        int num = 123243;
        int rev = 0;

        // reverse the number and count digits
        while (num > 0) {
            int digit = num % 10;
            rev = rev * 10 + digit;

            freq.put(digit, freq.getOrDefault(digit, 0) + 1);

            num = num / 10;
        }

        // find the first non-repeating digit
        while (rev > 0) {
            int digit = rev % 10;

            if (freq.get(digit) > 1) {
                System.out.println(digit);
                return;
            }

            rev = rev / 10;
        }

        System.out.println("No repeating digit found.");
    }


}
