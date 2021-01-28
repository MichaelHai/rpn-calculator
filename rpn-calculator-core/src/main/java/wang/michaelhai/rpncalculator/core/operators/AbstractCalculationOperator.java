package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.StackModifier;
import wang.michaelhai.rpncalculator.core.stack.StackPeeker;

import java.util.List;

public abstract class AbstractCalculationOperator implements Operator {
    private final StackPeeker stackPeeker;
    private final StackModifier stackModifier;

    protected AbstractCalculationOperator(StackPeeker stackPeeker, StackModifier stackModifier) {
        this.stackPeeker = stackPeeker;
        this.stackModifier = stackModifier;
    }

    @Override
    public void run() {
        int operandCount = getOperandCount();
        List<BigNumber> numbers = stackPeeker.peek(operandCount);

        if (numbers.size() < operandCount) {
            throw new InsufficientParametersException();
        }
        List<BigNumber> result = doOperate(numbers);
        stackModifier.modify(operandCount, result);
    }

    protected abstract int getOperandCount();

    protected abstract List<BigNumber> doOperate(List<BigNumber> operands);
}
