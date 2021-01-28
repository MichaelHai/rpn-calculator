package wang.michaelhai.rpncalculator.core.stack;

import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.List;

public interface CalculatorStack {
    /**
     * Peak the top given <code>count</code> of numbers in the calculator stack.
     * If the stack size is less than <code>count</code>, all numbers in the stack will be returned.
     * <p>
     * The order of the returned value is bottom first. For example:
     * suppose the stack before peek is bottom -> 1 -> 2 -> 3 -> top, peek(2) will returns [2, 3].
     *
     * @param count the <code>count</code> of number to peek
     * @return the numbers peeked
     */
    List<BigNumber> peek(int count);

    /**
     * modify the stack with pop and push operations.
     * <p>
     * The values will be pushed one by one from the beginning of the given list.
     * The order of the popped value follows the same order as <code>peek</code>
     *
     * @param numberToPop  pop the top numbers from the stack
     * @param valuesToPush values to push after popping
     * @return the values popped from the stack
     * @see CalculatorStack#peek(int)
     */
    List<BigNumber> modify(int numberToPop, List<BigNumber> valuesToPush);

    /**
     * List all elements in the same order as <code>peek</code>
     *
     * @return all elements in the stack
     * @see CalculatorStack#peek(int)
     */
    List<BigNumber> listAll();

    /**
     * Clear all the elements in the stack
     */
    void clear();
}
