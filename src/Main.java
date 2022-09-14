import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String operation = sc.nextLine();

        String res = calc(operation);
        System.out.println(res);
    }

    public static String calc(String fullOperation) {
        String[] partsOperation = fullOperation.split(" ");

        if (partsOperation.length != 3) {
            throw new RuntimeException("Неверное число аргументов");
        }

        String operation = partsOperation[1];
        if (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/")) {
            throw new RuntimeException("Неверная операция");
        }

        if (isArabNum(partsOperation[0]) && isArabNum(partsOperation[2])) {
            int result = workWithArabsNum(
                    Integer.parseInt(partsOperation[0]),
                    Integer.parseInt(partsOperation[2]),
                    partsOperation[1]
            );
            return result + "";
        } else if (isRomanNum(partsOperation[0]) && isRomanNum(partsOperation[2])) {
            return workWithRomansNum(
                    partsOperation[0],
                    partsOperation[2],
                    partsOperation[1]
            );
        } else {
            throw new RuntimeException("Плохие числа");
        }
    }

    public static boolean isArabNum(String strNum) {
        try {
            Integer.parseInt(strNum);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isRomanNum(String strNum) {
        Map<String, Integer> romanNums = new HashMap<>();
        romanNums.put("I", 1);
        romanNums.put("II", 2);
        romanNums.put("III", 3);
        romanNums.put("IV", 4);
        romanNums.put("V", 5);
        romanNums.put("VI", 6);
        romanNums.put("VII", 7);
        romanNums.put("VIII", 8);
        romanNums.put("IX", 9);
        romanNums.put("X", 10);

        return romanNums.containsKey(strNum);
    }

    public static int workWithArabsNum(int num1, int num2, String operation) {
        if (num1 < 1 || num1 > 10) {
            throw new RuntimeException("Неверный диапазон чисел");
        }
        if (num2 < 1 || num2 > 10) {
            throw new RuntimeException("Неверный диапазон чисел");
        }
        return doOperation(num1, num2, operation);
    }

    public static String workWithRomansNum(String num1, String num2, String operation) {
        Map<String, Integer> romanNums = new HashMap<>();
        romanNums.put("I", 1);
        romanNums.put("II", 2);
        romanNums.put("III", 3);
        romanNums.put("IV", 4);
        romanNums.put("V", 5);
        romanNums.put("VI", 6);
        romanNums.put("VII", 7);
        romanNums.put("VIII", 8);
        romanNums.put("IX", 9);
        romanNums.put("X", 10);

        int result = doOperation(romanNums.get(num1), romanNums.get(num2), operation);

        if (result < 1) {
            throw new RuntimeException("Результат операции - не валидное число");
        } else {
            return calcRoman(result);
        }
    }


    static String calcRoman(int num) {
        TreeMap<Integer, String> romanNumsCalc = new TreeMap<>();
        romanNumsCalc.put(1, "I");
        romanNumsCalc.put(5, "V");
        romanNumsCalc.put(10, "X");
        romanNumsCalc.put(50, "L");
        romanNumsCalc.put(100, "C");

        int floorElement = romanNumsCalc.floorKey(num);

        if (floorElement == num) {
            return romanNumsCalc.get(num);
        }

        return romanNumsCalc.get(floorElement) + calcRoman(num - floorElement);
    }


    public static int doOperation(int num1, int num2, String operation) {
        if (operation.equals("+")) {
            return num1 + num2;
        }
        if (operation.equals("-")) {
            return num1 - num2;
        }
        if (operation.equals("*")) {
            return num1 * num2;
        }
        if (operation.equals("/")) {
            return num1 / num2;
        }
        throw new RuntimeException("Неожиданная ошибка");
    }
}