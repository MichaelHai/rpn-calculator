package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

public interface Operator {
    void run();
    Operator setCalculatorStack(CalculatorStack stack);
}
