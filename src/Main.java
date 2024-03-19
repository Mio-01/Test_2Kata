import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();
        char action;
        String[] data;
        try {
            if (exp.contains(" + ")) {
                data = exp.split(" \\+ ");
                action = '+';
            } else if (exp.contains(" - ")) {
                data = exp.split(" - ");
                action = '-';
            } else if (exp.contains(" * ")) {
                data = exp.split(" \\* ");
                action = '*';
            } else if (exp.contains(" / ")) {
                data = exp.split(" / ");
                action = '/';
            } else {
                throw new Exception("Некорректный знак действия");
            }

            if (data.length != 2) {
                throw new Exception("Неверный формат ввода");
            }

            if (action == '*' || action == '/') {
                if (data[1].contains("\"")) throw new Exception("Строчку можно делить или умножать только на число");
            }
            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].replace("\"", "");
            }

            if (!isValidInput(data[0]) || !isValidInput(data[1])) {
                throw new Exception("Некорректные данные. Числа должны быть от 1 до 10 включительно, а строки не более 10 символов длиной");
            }

            if (action == '+') {
                printInQuotes(data[0] + data[1]);
            } else if (action == '*') {
                int multiplier = Integer.parseInt(data[1]);
                String result = "";
                for (int i = 0; i < multiplier; i++) {
                    result += data[0];
                    if (result.length() > 40) {
                        result = result.substring(0, 40) + "...";
                        break;
                    }
                }
                printInQuotes(result);
            } else if (action == '-') {
                int index = data[0].indexOf(data[1]);
                if (index == -1) {
                    printInQuotes(data[0]);
                } else {
                    String result = data[0].substring(0, index);
                    if (index + data[1].length() < data[0].length()) {
                        result += data[0].substring(index + data[1].length());
                    }
                    printInQuotes(result);
                }
            } else {
                int newLen = data[0].length() / Integer.parseInt(data[1]);
                String result = data[0].substring(0, newLen);
                if (result.length() > 40) {
                    result = result.substring(0, 40) + "...";
                }
                printInQuotes(result);
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    static void printInQuotes(String text) {
        System.out.println("\"" + text + "\"");
    }

    static boolean isValidInput(String input) {
        try {
            int num = Integer.parseInt(input);
            return num >= 1 && num <= 10;
        } catch (NumberFormatException e) {
            return input.length() <= 10;
        }
    }
}