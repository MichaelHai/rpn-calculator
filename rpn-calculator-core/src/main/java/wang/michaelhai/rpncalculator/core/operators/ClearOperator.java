package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

public class ClearOperator implements Operator {
    private CalculatorStack calculatorStack;

    public ClearOperator(CalculatorStack calculatorStack) {
        this.calculatorStack = calculatorStack;
    }

    @Override
    public void run() {
        calculatorStack.clear();
    }
}
