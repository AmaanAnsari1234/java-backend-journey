package Week3;
// find 2nd Largest Number
// second smallest distinct digit in a number

public class P2 {
    public static void main(String[] args) {
        SecLargest();
        SecSmallest();
    }

    public static void SecLargest() {
        int num = 98;
        int firstLar = 0;
        int secLar = 0;

        while (num > 0) {
            int digit = num % 10;

            if (digit > firstLar) {
                secLar = firstLar;
                firstLar = digit;
            } else if (digit < firstLar && digit > secLar) {
                secLar = digit;
            }

            num = num / 10;
        }

        System.out.println(secLar);
    }

    public static void SecSmallest(){
        int num = 9328;
        int firstSmall = 9;
        int secSmall = 9;

        while (num > 0) {
            int digit = num % 10;

            if (digit < firstSmall ) {
                secSmall = firstSmall;
                firstSmall = digit;
            } else if (digit > firstSmall && digit < secSmall) {
                secSmall = digit;
            }

            num = num / 10;
        }

        System.out.println(secSmall);

    }

}
