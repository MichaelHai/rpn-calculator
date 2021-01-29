package wang.michaelhai.rpncalculator.core.operators.impl;

import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.operators.AbstractCalculationOperator;

import java.util.Collections;
import java.util.List;

public class SqrtOperator extends AbstractCalculationOperator {
    @Override
    protected int getOperandCount() {
        return 1;
    }

    @Override
    protected List<BigNumber> doOperate(List<BigNumber> operands) {
        BigNumber number = operands.get(0);
        if (new BigNumber("0").compareTo(number) > 0) {
            throw new SqrtOnNegativeException();
        }
        return Collections.singletonList(number.sqrt());
    }
}
