package Week1;
//1: Reverse a Number
//2: Count Digits
//3: Check Prime Number

public class P2 {

    // Question 1: Reverse a Number
    public static void reverseNumber() {
        int num = 1234;
        int rev = 0;

        while (num > 0) {
            int digit = num % 10;
            rev = rev * 10 + digit;
            num = num / 10;
        }

        System.out.println("Reversed Number: " + rev);
    }

    // Question 2: Count Digits
    public static void countDigits() {
        int num = 12345;
        int count = 0;

        if (num == 0) {
            count = 1;
        }

        while (num > 0) {
            num = num / 10;
            count++;
        }

        System.out.println("Digit Count: " + count);
    }

    // Question 3: Check Prime Number
    public static void checkPrime() {
        int num = 9;
        boolean isPrime = true;

        if (num <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        if (isPrime) {
            System.out.println(num + " is Prime");
        } else {
            System.out.println(num + " is Not Prime");
        }
    }

    public static void main(String[] args) {
        reverseNumber();
        countDigits();
        checkPrime();
    }
}
