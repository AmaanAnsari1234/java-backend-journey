package Week2;
// Count of occurence of a number
public class P5 {

    public static void main(String[] args) {
        countDigitOccurrences();
    }

    public static void countDigitOccurrences() {
        int num = 555;
        int target = 5;
        int count = 0;

        while (num > 0) {
            int digit = num % 10;

            if (digit == target) {
                count++;
            }

            num = num / 10;
        }
        System.out.println("Digit " + target + " occurs " + count + " times");
    }
}
