package Week3;
// Armstrong Number
public class P1 {

    public static void main(String[] args) {
        Armstrong();
        Armstrong1();
    }

    public static void Armstrong(){
        int num = 153;
        int  c = num   ;
        int temp = 0;

        while(num > 0){
           int  digit = num  % 10;
           temp = temp + digit * digit * digit ;
           num = num / 10;
        }
        if (c == temp)
        {
            System.out.printf("Armstrong");
        }
        else System.out.printf("Not Armstrong");

    }


    public static void Armstrong1() {
        int num = 153;
        int original = num;
        int countNum = num;
        int digits = 0;
        int sum = 0;

        // Step 1: Count the digits
        while (countNum > 0) {
            digits++;
            countNum = countNum / 10;
        }

        // Step 2: Calculate the sum of powered digits
        countNum = num;

        while (countNum > 0) {
            int digit = countNum % 10;
            sum = sum + (int) Math.pow(digit, digits);
            countNum = countNum / 10;
        }

        // Step 3: Compare with the original number
        if (original == sum) {
            System.out.println("Armstrong");
        } else {
            System.out.println("Not Armstrong");
        }
    }


}
