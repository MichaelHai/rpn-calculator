package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.StackModifier;
import wang.michaelhai.rpncalculator.core.stack.StackPeeker;

import java.util.Collections;
import java.util.List;

public class SqrtOperator extends AbstractCalculationOperator {

    public SqrtOperator(StackPeeker stackPeeker, StackModifier stackModifier) {
        super(stackPeeker, stackModifier);
    }

    @Override
    protected int getOperandCount() {
        return 1;
    }

    @Override
    protected List<BigNumber> doOperate(List<BigNumber> operands) {
        BigNumber number = operands.get(0);
        return Collections.singletonList(number.sqrt());
    }
}
