package wang.michaelhai.rpncalculator.core.operators.impl;

import wang.michaelhai.rpncalculator.core.operators.BaseOperator;

public class UndoOperator extends BaseOperator {

    @Override
    public void run() {
        calculatorStack.undo();
    }
}
