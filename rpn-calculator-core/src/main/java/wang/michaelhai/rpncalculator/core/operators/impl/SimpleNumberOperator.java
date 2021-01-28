package wang.michaelhai.rpncalculator.core.operators.impl;

import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.operators.BaseOperator;

import java.util.Collections;

public class SimpleNumberOperator extends BaseOperator {
    private BigNumber number;

    public SimpleNumberOperator(BigNumber number) {
        this.number = number;
    }

    @Override
    public void run() {
        calculatorStack.modify(0, Collections.singletonList(number));
    }
}
