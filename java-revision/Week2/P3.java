package Week2;

public class P3 {
    public static void main(String[] args) {
        sumOfDigits();
    }

    public static void sumOfDigits() {
        int num = 14;
        int sum = 0;

        while (num > 0) {
            // 1. Extract the last digit
            int last  = num % 10;
            // 2. Add it to sum
            sum = sum + last;
            // 3. Remove the last digit
            num = num /10;
        }

        System.out.println(sum);
    }


}
