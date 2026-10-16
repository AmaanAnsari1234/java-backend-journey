package Week2;

public class P3 {
    public static void main(String[] args) {
        sumOfDigits();
        numberPalindrome();
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

    public static void numberPalindrome(){
        int num = 121;
        int original = num;
        int rev = 0;

        while (num > 0) {
            // Hint 1: Extract the last digit
            int last  = num % 10;
            // Hint 2: Add it to rev

            rev = rev * 10 + last;

            // Hint 3: Remove the last digit
            num = num /10;
        }

        // Hint 4: Compare original with rev
        if( original == rev)
        {
            System.out.println("Palindrome");
        }
        else System.out.println("Not a Palindrome");
    }

    
}
