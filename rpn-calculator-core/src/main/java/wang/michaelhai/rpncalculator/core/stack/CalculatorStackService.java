package wang.michaelhai.rpncalculator.core.stack;

import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.List;

public interface CalculatorStackService extends StackPeeker, StackModifier {
    /**
     * List all elements in the same order as <code>StackPeeker</code>
     * @see StackPeeker#peek(int)
     * @return all elements in the stack
     */
    List<BigNumber> listAll();
}
