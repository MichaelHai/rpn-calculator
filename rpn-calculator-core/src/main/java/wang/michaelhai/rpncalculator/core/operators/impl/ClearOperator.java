package wang.michaelhai.rpncalculator.core.operators.impl;

import wang.michaelhai.rpncalculator.core.operators.BaseOperator;

public class ClearOperator extends BaseOperator {
    @Override
    public void run() {
        calculatorStack.clear();
    }
}
