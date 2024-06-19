package com.example.javapolitech_j120_lab3.Caclulator_1;

public class Calculator {
    private double num1 = 0;
    private String operator = "";
    private boolean start = true;

    public String process(String input, String currentDisplay) {
        if (input.matches("[0-9]") || input.equals(".")) {
            if (start) {
                currentDisplay = "";
                start = false;
            }
            return currentDisplay + input;
        } else {
            if (!start) {
                num1 = Double.parseDouble(currentDisplay);
                operator = input;
                start = true;
            }
            return "";
        }
    }

    public String calculate(String currentDisplay) {
        if (!operator.isEmpty()) {
            double num2 = Double.parseDouble(currentDisplay);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }

            operator = "";
            start = true;
            return String.valueOf(result);
        }
        return currentDisplay;
    }

    public void reset() {
        num1 = 0;
        operator = "";
        start = true;
    }
}
