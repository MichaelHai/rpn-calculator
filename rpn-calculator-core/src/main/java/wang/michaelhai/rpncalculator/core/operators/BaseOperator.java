package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

public abstract class BaseOperator implements Operator {
    protected CalculatorStack calculatorStack;

    @Override
    public Operator setCalculatorStack(CalculatorStack calculatorStack) {
        this.calculatorStack = calculatorStack;
        return this;
    }
}
