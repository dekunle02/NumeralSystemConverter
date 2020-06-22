import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.next();
        int radix = s.nextInt();
        System.out.println(isCorrectRadix(input.charAt(0), radix));
        System.out.println(Character.getNumericValue(input.charAt(0)));

    }

    private static boolean isCorrectRadix(char c, int radix) {
        if (c == '1' && radix == 1){
            return true;
        }
        return Character.getNumericValue(c) < radix;
    }
}

