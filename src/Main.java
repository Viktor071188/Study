import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int num1, num2;
    static char operation;
    static int result;
    static int counter = 0;

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Roman roman = new Roman();
        String userInput = scanner.nextLine();
        userInput = userInput.replaceAll("\\s", "");
        char[] charArray = new char[userInput.length()];
        for (int i = 0; i < userInput.length(); i++) {
            charArray[i] = userInput.charAt(i);
            if (charArray[i] == '+') {
                operation = '+';
                counter++;
            }
            if (charArray[i] == '-') {
                operation = '-';
                counter++;
            }
            if (charArray[i] == '*') {
                operation = '*';
                counter++;
            }
            if (charArray[i] == '/') {
                operation = '/';
                counter++;
            }
            if (counter > 1) {
                System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор");
                return;
            }
        }
        String charArrayToString = String.valueOf(charArray);
        String[] sym = charArrayToString.split("[+-/*]");
        String sym1 = sym[0];
        String sym2 = sym[1];
        try {
            num1 = Integer.parseInt(sym1);
            num2 = Integer.parseInt(sym2);
            if ((num1 > 0 && num1 < 10) || (num2 > 0 && num2 < 10)) {
                result = calc.calculated(num1, num2, operation);
                System.out.println(num1 + " " + operation + " " + num2 + " = " + result);
            } else {
                System.out.println("Введено недопустимое число");
            }
        } catch (NumberFormatException e) {
            num1 = roman.romanToNumber(sym1);
            num2 = roman.romanToNumber(sym2);
            if (num1 < 0 || num2 < 0) {
                System.out.println("Введены недопустимые символы");
            } else if (operation == '-' && num1 < (num2 + 1)) {
                System.out.println("недопустимое выражение");
                return;
            } else {
                result = calc.calculated(num1, num2, operation);
                String resultRoman = roman.convertNumToRoman(result);
                System.out.println(sym1 + " " + operation + " " + sym2 + " = " + resultRoman);
            }
        }
    }
}
