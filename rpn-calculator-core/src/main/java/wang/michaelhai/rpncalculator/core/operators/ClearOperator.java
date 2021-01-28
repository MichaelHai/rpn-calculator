package wang.michaelhai.rpncalculator.core.operators;

public class ClearOperator extends BaseOperator {
    @Override
    public void run() {
        calculatorStack.clear();
    }
}
