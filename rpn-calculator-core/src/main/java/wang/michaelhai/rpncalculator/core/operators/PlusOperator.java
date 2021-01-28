package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.stack.StackModifier;
import wang.michaelhai.rpncalculator.core.stack.StackPeeker;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class PlusOperator implements Operator {
    private final StackPeeker stackPeeker;
    private final StackModifier stackModifier;

    public PlusOperator(StackPeeker stackPeeker, StackModifier stackModifier) {
        this.stackPeeker = stackPeeker;
        this.stackModifier = stackModifier;
    }

    @Override
    public void run() {
        List<BigDecimal> numbers = stackPeeker.peek(2);

        if (numbers.size() < 2) {
            throw new InsufficientParametersException();
        }
        BigDecimal result = numbers.get(0).add(numbers.get(1));
        stackModifier.modify(2, Collections.singletonList(result));
    }
}
