package com.example.calculadorav01;
public class Calculator {
    private double value = 0;
    private double  value1 = 0;
    private String currentOperator = "";
    private boolean operatorClicked = false;
    private boolean decimalEntered = false;

    public String handleInput(String input) {
        if (input.matches("[0-9]")) {
            handleNumber(input);
        } else if (input.equals(".")) {
            handleDecimal();
        } else if (input.matches("[+\\-*/]")) {
            handleOperator(input);
        } else if (input.equals("=")) {
            return calculate();
        } else if (input.equals("C")) {
            clear();
        }
        return formatValue(value);
    }

    private void handleNumber(String number) {
        if (operatorClicked || value == 0) {
            value = Double.parseDouble(number);
            operatorClicked = false;
        } else {
            value = value * 10 + Double.parseDouble(number);
        }
    }

    private void handleDecimal() {
        if (!decimalEntered) {
            value = 0;
            decimalEntered = true;
        }
    }

    private void handleOperator(String operator) {
        if (!operatorClicked) {

            calculate();
        }
        currentOperator = operator;
        operatorClicked = true;
        decimalEntered = false;
    }

    private String calculate() {
        switch (currentOperator) {
            case "+":
                value += Double.parseDouble(formatValue(value));
                break;
            case "-":
                value -= Double.parseDouble(formatValue(value));
                break;
            case "*":
                value *= Double.parseDouble(formatValue(value));
                break;
            case "/":
                double divisor = Double.parseDouble(formatValue(value));
                if (divisor != 0) {
                    value /= divisor;
                } else {
                    clear();
                    return "Error: Division by zero";
                }
                break;
        }

        currentOperator = "";
        operatorClicked = false;
        decimalEntered = false;
        return formatValue(value);
    }

    private void clear() {
        value = 0;
        currentOperator = "";
        operatorClicked = false;
        decimalEntered = false;
    }

    private String formatValue(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        } else {
            return String.format("%s", value);
        }
    }


}
