package Hard;

import java.util.Scanner;

public class UniversalConvertor {
    public static void main(String[] args) {
        System.out.println("Введите десятичное, двоичное или шестнадцатеричное число для перекодировки:");
        while (true) {
            System.out.println("""
                    Десятичное число должно быть больше 0
                    Двоичное число должно начинаться на 0b (пример: 0b100110)
                    Шестнадцатеричное число должно начинаться на 0x (пример: 0xDF0)""");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                int decimalNumber = scan.nextInt();
                if (decimalNumber <= 0) {           // проверяем ввод десятичного числа
                    System.out.println("Число не соответствует условию ввода. Введите еще раз:");
                    continue;
                }
                System.out.println("Десятичное число " + decimalNumber + " равно шестнадцатеричному числу " + toDecimalHex(decimalNumber));
                System.out.println("Десятичное число " + decimalNumber + " равно двоичному числу " + toDecimalBinary(decimalNumber));
            }
            String stringNumber = scan.nextLine();
            if (hexDemTrue(stringNumber)) {   // проверяем ввод шестнадцатеричного числа
                System.out.println("Шестнадцатеричное число " + stringNumber + " равно десятичному числу " + toHexDecimal(stringNumber));
                System.out.println("Шестнадцатеричное число " + stringNumber + " равно двоичному числу " + toDecimalBinary(toHexDecimal(stringNumber)));
                break;
            } else if (BinaryTrue(stringNumber)) {  // проверяем ввод двоичного числа
                System.out.println("Двоичное число " + stringNumber + " равно десятичному числу " + toBinaryDecimal(stringNumber));
                System.out.println("Двоичное число " + stringNumber + " равно шестнадцатеричному числу " + toDecimalHex(toBinaryDecimal(stringNumber)));
                break;
            } else {
                System.out.println("Число не соответствует условию ввода. Введите еще раз:");
            }
        }
    }

    public static String toDecimalHex(int decimalNumber) {  // метод конвертирует десятичное в шестнадцатеричное число ( 0xDF0 3568)
        String hexadecimalNumberic = "";
        for (; decimalNumber > 0; decimalNumber = decimalNumber / 16) {
            switch (decimalNumber % 16) {
                case 10 -> hexadecimalNumberic = "A" + hexadecimalNumberic;
                case 11 -> hexadecimalNumberic = "B" + hexadecimalNumberic;
                case 12 -> hexadecimalNumberic = "C" + hexadecimalNumberic;
                case 13 -> hexadecimalNumberic = "D" + hexadecimalNumberic;
                case 14 -> hexadecimalNumberic = "E" + hexadecimalNumberic;
                case 15 -> hexadecimalNumberic = "F" + hexadecimalNumberic;
                default -> hexadecimalNumberic = decimalNumber % 16 + hexadecimalNumberic;
            }
        }
        return "0x" + hexadecimalNumberic;
    }

    public static int toHexDecimal(String stringNumber) {  // метод конвертирует шестнадцатеричное в десятичное число
        int decimalNumber = 0;
        stringNumber=stringNumber.substring(2, stringNumber.length());  // отбрасываем "0x" от шестнадцатеричного числа
        int i = stringNumber.length();
        char[] array = stringNumber.toCharArray();  // разбиваем строку на символьный массив
        for (int x : array) {                                                          // цикл for each
            switch (x) {
                case 'A' -> decimalNumber += 10 * Math.pow(16, --i);
                case 'B' -> decimalNumber += 11 * Math.pow(16, --i);
                case 'C' -> decimalNumber += 12 * Math.pow(16, --i);
                case 'D' -> decimalNumber += 13 * Math.pow(16, --i);
                case 'E' -> decimalNumber += 14 * Math.pow(16, --i);
                case 'F' -> decimalNumber += 15 * Math.pow(16, --i);
                default ->
                        decimalNumber += (int) (Character.getNumericValue(x) * Math.pow(10, --i)); // =(char --> int (1 * 2^i) i=i-1)
            }
        }
        return decimalNumber;
    }

    public static String toDecimalBinary(int decimalNumber) {
        String binaryNumber = "";
        for (; decimalNumber > 0; decimalNumber = decimalNumber / 2) { //
            binaryNumber = (decimalNumber % 2) + binaryNumber;          // от перемены мест влияет на порядок заполнение 0001 или 1000
        }
        return binaryNumber;
    }

    public static int toBinaryDecimal(String stringNumber) {
        int decimalNumber = 0;
        stringNumber = stringNumber.substring(2, stringNumber.length());
        int i = stringNumber.length();
        char[] array = stringNumber.toCharArray();  // разбиваем строку на символьный массив
        for (int x : array) {                                                          // цикл for each
            decimalNumber += (int) (Character.getNumericValue(x) * Math.pow(2, --i)); // =(char --> int (1 * 2^i) i=i-1)
        }
        return decimalNumber;
    }

    public static boolean hexDemTrue(String stringNumber) {
        if (!(stringNumber.startsWith("0x"))) {
            return false;
        }
        stringNumber = stringNumber.substring(2, stringNumber.length());
        char[] array = stringNumber.toCharArray();
        for (char x : array) {
            switch (x) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                        'A', 'B', 'C', 'D', 'E', 'F' -> x = 'r';
                default -> {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean BinaryTrue(String stringNumber) {
        if (!(stringNumber.startsWith("0b"))) {
            return false;
        }
        stringNumber = stringNumber.substring(2, stringNumber.length());
        char[] array = stringNumber.toCharArray();
        for (char x : array) {
            switch (x) {
                case '0', '1' -> x = 'r';
                default -> {
                    return false;
                }
            }
        }
        return true;
    }
}
