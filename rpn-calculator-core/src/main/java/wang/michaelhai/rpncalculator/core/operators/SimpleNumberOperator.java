package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.StackModifier;

import java.util.Collections;

public class SimpleNumberOperator implements Operator {
    private StackModifier stackModifier;
    private BigNumber number;

    public SimpleNumberOperator(StackModifier stackModifier, BigNumber number) {
        this.stackModifier = stackModifier;
        this.number = number;
    }

    @Override
    public void run() {
        stackModifier.modify(0, Collections.singletonList(number));
    }
}
