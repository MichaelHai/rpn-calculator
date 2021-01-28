package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

import java.util.List;

public abstract class AbstractCalculationOperator implements Operator {
    private final CalculatorStack calculatorStack;

    protected AbstractCalculationOperator(CalculatorStack calculatorStack) {
        this.calculatorStack = calculatorStack;
    }

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
