// Q1 - Vowels,
// Q2 - factorial

package Week2;

public class P2 {

    public static void vowels() {
        String str = "Hello Java";
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = Character.toLowerCase(str.charAt(i));

            if (ch == 'a' || ch == 'e' || ch == 'i'
                    || ch == 'o' || ch == 'u') {
                vowels++;
            } else if (Character.isLetter(ch)) {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }

    public static void  fact(){
        int num = 5;
        int fact =1;
        for (int i = 1; i <= num; i++) {
            fact = fact *i;

        }
        System.out.println(fact);
    }

    public static void main(String[] args) {
        vowels();
        fact();
    }
}
