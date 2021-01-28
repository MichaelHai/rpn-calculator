package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.List;

public abstract class AbstractCalculationOperator extends BaseOperator {
    @Override
    public void run() {
        int operandCount = getOperandCount();
        List<BigNumber> numbers = calculatorStack.peek(operandCount);

        if (numbers.size() < operandCount) {
            throw new InsufficientParametersException();
        }
        List<BigNumber> result = doOperate(numbers);
        calculatorStack.modify(operandCount, result);
    }

    protected abstract int getOperandCount();

    protected abstract List<BigNumber> doOperate(List<BigNumber> operands);
}
