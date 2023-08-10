package com.example.calculadorav01;

public class Calculator {
    private double currentValue = 0;
    private String currentOperator = "";
    private boolean operatorClicked = false;
    private boolean decimalEntered = false;
    public String handleInput(String input) {
        if (input.matches("[0-9.]")) {
            handleNumber(input);
        } else if (input.matches("[+\\-×÷]")) {
            handleOperator(input);
        } else if (input.equals("=")) {
            return calculate();
        } else if (input.equals("C")) {
            clear();
        }

        return String.valueOf(currentValue);
    }
    private void handleDecimal() {
        if (!decimalEntered) {
            currentValue = 0;
            decimalEntered = true;
        }
    }
    private void handleNumber(String number) {
        if (operatorClicked || currentValue == 0) {
            currentValue = Double.parseDouble(number);
            operatorClicked = false;
        } else {
            currentValue = currentValue * 10 + Double.parseDouble(number);
        }
    }

     private void handleOperator(String operator) {
        if (!operatorClicked) {
            calculate(); // Realiza el cálculo con el operador anterior
        }
        currentOperator = operator;
        operatorClicked = true;
        decimalEntered = false;
    }

    private String calculate() {
        double result = currentValue;

        switch (currentOperator) {
            case "+":
                result += currentValue;
                break;
            case "-":
                result -= currentValue;
                break;
            case "×":
                result *= currentValue;
                break;
            case "÷":
                if (currentValue != 0) {
                    result /= currentValue;
                } else {
                    return "Error"; // Manejar la división por cero
                }
                break;
            // Agregar más casos para otros operadores si es necesario
        }

        currentValue = result;
        currentOperator = "=";
        operatorClicked = false;
        decimalEntered = false;

        return String.valueOf(currentValue);
    }


    private void clear() {
        currentValue = 0;
        currentOperator = "";
        operatorClicked = false;
    }
}
