package Week2;
// Q1: Find the Largest of Three Numbers
// Q2: Check Whether a String Is a Palindrome

public class P1 {

    // Q1: Find the Largest of Three Numbers
    public static void largestOfThree() {
        int a = 10;
        int b = 25;
        int c = 15;

        if (a >= b && a >= c) {
            System.out.println("Largest number: " + a);
        } else if (b >= a && b >= c) {
            System.out.println("Largest number: " + b);
        } else {
            System.out.println("Largest number: " + c);
        }
    }

    // Q2: Check Whether a String Is a Palindrome
    public static void checkPalindrome() {
        String str = "madam";
        boolean isPalindrome = true;

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not a palindrome");
        }
    }

    public static void main(String[] args) {
        System.out.println("Question 1: Largest of Three");
        largestOfThree();

        System.out.println();

        System.out.println("Question 2: Palindrome Check");
        checkPalindrome();
    }
}
