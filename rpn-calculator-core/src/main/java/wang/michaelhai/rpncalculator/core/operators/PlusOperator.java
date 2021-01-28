package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.Collections;
import java.util.List;

public class PlusOperator extends AbstractCalculationOperator {
    @Override
    protected int getOperandCount() {
        return 2;
    }

    @Override
    protected List<BigNumber> doOperate(List<BigNumber> operands) {
        BigNumber result = operands.get(0).add(operands.get(1));
        return Collections.singletonList(result);
    }
}
