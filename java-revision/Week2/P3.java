package Week2;
// Q1 - Sum of  Digits
// Q2 - Palindrome
// Q3 - Largest digit in a number
// Q4 - Smallest digit in a number
public class P3 {
    public static void main(String[] args) {
        sumOfDigits();
        numberPalindrome();
        largestDigit();
        smallestDigit();
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

    public static void largestDigit()
    {
        int num = 5992;
        int largest = 0;

        while (num > 0){
            int last = num % 10;
            if(last > largest)
            {
                largest = last;
            }
            num = num /10;
        }
        System.out.println(largest);
    }

    public static void smallestDigit()
    {
        int num = 5992;
        int smallest = 9;

        while (num > 0){
            int last = num % 10;
            if(last < smallest)
            {
                smallest = last;
            }
            num = num /10;
        }
        System.out.println(smallest);
    }
}
