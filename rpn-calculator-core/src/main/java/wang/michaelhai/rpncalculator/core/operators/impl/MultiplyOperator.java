package wang.michaelhai.rpncalculator.core.operators.impl;

import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.operators.AbstractCalculationOperator;

import java.util.Collections;
import java.util.List;

public class MultiplyOperator extends AbstractCalculationOperator {
    @Override
    protected int getOperandCount() {
        return 2;
    }

    @Override
    protected List<BigNumber> doOperate(List<BigNumber> operands) {
        BigNumber result = operands.get(0).multiply(operands.get(1));
        return Collections.singletonList(result);
    }
}
