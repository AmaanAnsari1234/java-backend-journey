package Week2;

public class P4 {

    public static void main(String[] args) {
        EvenOdd();
    }

    public static void EvenOdd(){
        int num = 58322;
        int evenCount = 0;
        int oddCount = 0;

        while(num>0){
            int digit  =num % 10;
            if (digit % 2 == 0) {
                 evenCount++;
            } else {
                oddCount++;
            }
            num = num / 10;
        }
        System.out.println(evenCount);
        System.out.println(oddCount);

    }
}
