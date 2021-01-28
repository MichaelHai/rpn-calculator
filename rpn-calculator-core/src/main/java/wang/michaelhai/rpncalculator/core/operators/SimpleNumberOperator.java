package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

import java.util.Collections;

public class SimpleNumberOperator implements Operator {
    private CalculatorStack calculatorStack;
    private BigNumber number;

    public SimpleNumberOperator(CalculatorStack calculatorStack, BigNumber number) {
        this.calculatorStack = calculatorStack;
        this.number = number;
    }

    @Override
    public void run() {
        calculatorStack.modify(0, Collections.singletonList(number));
    }
}
