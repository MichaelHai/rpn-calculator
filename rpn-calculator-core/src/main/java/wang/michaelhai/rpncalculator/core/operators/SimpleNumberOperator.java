package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.stack.StackModifier;

import java.math.BigDecimal;
import java.util.Collections;

public class SimpleNumberOperator implements Operator {
    private StackModifier stackModifier;
    private BigDecimal number;

    public SimpleNumberOperator(StackModifier stackModifier, BigDecimal number) {
        this.stackModifier = stackModifier;
        this.number = number;
    }

    @Override
    public void run() {
        stackModifier.modify(0, Collections.singletonList(number));
    }
}
