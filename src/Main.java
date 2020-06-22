import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int currentBase = 1;
        String num = "";
        int newBase = 1;
        try {
            currentBase = scanner.nextInt();
            num = scanner.next();
            newBase = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("1 error");
            return;
        }


        if (newBase < 1 || newBase > 36 || currentBase < 1 || currentBase > 36) {
            System.out.println(" 2 error");
            return;
        }

        for (char c : num.toCharArray()) {
            if (!isCorrectRadix(c, currentBase)){
                System.out.println("3 error");
                return;
            }
        }


        String beforePoint = num.split("\\.")[0];

        String afterPoint = "0.0";
        if (num.split("\\.").length > 1) {
            afterPoint = "0." + num.split("\\.")[1];
        }

        String beforeInDecimal = convertFromRadixToDecimal(beforePoint, currentBase);
        String beforeToRadix = convertFromDecimalToRadix(beforeInDecimal, newBase);

        String afterInDecimal = convertFloatToDecimal(afterPoint, currentBase);
        String afterToRadix = convertFloatFromDecimal(afterInDecimal, newBase);

        String result = beforeToRadix + "." + afterToRadix.split("\\.")[1];
        System.out.println(result);
    }

    private static boolean isCorrectRadix(char c, int radix) {
        if (c == '1' && radix == 1){
            return true;
        }
        return Character.getNumericValue(c) < radix;
    }

    public static String convertFromDecimalToRadix(String numS, int radix) {
        int num = Integer.parseInt(numS);
        if (radix == 1) {
            return ("1".repeat(num));
        }
        return Long.toString(num, radix);
    }

    public static String convertFromRadixToDecimal(String num, int radix) {

        if (radix == 1) {
            return Integer.toString(String.valueOf(num).length());
        }

        return String.valueOf(Integer.valueOf(num, radix));
    }

    public static String convertFloatFromDecimal(String num, int radix) {
        double number = Double.parseDouble(num);
        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; i < 5; i++) {
            double iter = number * radix;
            String iterStr = Double.toString(iter);
            int intPart = Integer.parseInt(iterStr.split("\\.")[0]);
            String floatPart = iterStr.split("\\.")[1];
            number = Double.parseDouble("0." + floatPart);
            if (intPart >= 10) {
                char c = 'a';
                c += (intPart - 10);
                sb.append(c);
            } else {
                sb.append(intPart);
            }
        }
        return sb.toString();
    }

    public static String convertFloatToDecimal(String num, int radix) {
        String number = num.split("\\.")[1];
        double value = 0.0;
        int exponent = 1;

        for (Character c : number.toCharArray()) {
            double divisionResult = 0.0;
            divisionResult = Character.getNumericValue(c) / Math.pow(radix, exponent);
            value += divisionResult;
            exponent++;
        }

        return Double.toString(value);
    }

}
