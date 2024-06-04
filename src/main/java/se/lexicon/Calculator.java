package se.lexicon;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public double add(double... numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum;
    }

    public double subtract(double... numbers) {
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result -= numbers[i];
        }
        return result;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }
}

class CalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {
            System.out.print("Enter operation (e.g., 2 + 3, 5 - 2 - 1) or 'q' to quit: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            try {
                String[] parts = input.split(" ");

                List<Double> numbers = new ArrayList<>();
                String operator = null;
                for (String part : parts) {
                    if (part.matches("[+\\-*/]")) {
                        operator = part;
                    } else {
                        try {
                            numbers.add(Double.parseDouble(part));
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Invalid number format: " + part);
                        }
                    }
                }

                if (operator == null) {
                    throw new IllegalArgumentException("Invalid input: missing operator");
                }

                if (numbers.size() < 2 && !operator.equals("+") && !operator.equals("-")) {
                    throw new IllegalArgumentException("Not enough numbers for operation: " + operator);
                }

                double[] numbersArray = numbers.stream().mapToDouble(Double::doubleValue).toArray();

                double result;
                switch (operator) {
                    case "+":
                        result = calculator.add(numbersArray);
                        break;
                    case "-":
                        result = calculator.subtract(numbersArray);
                        break;
                    case "*":
                        result = calculator.multiply(numbersArray[0], numbersArray[1]);
                        break;
                    case "/":
                        result = calculator.divide(numbersArray[0], numbersArray[1]);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator");
                }

                System.out.println("Result: " + result);
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter valid numbers.");
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }
}