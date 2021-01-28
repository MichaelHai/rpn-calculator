package wang.michaelhai.rpncalculator.core.stack;

import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.List;

/**
 * The functional interface used to peek the top numbers in the stack to calculate
 */
@FunctionalInterface
public interface StackPeeker {
    /**
     * Peak the top given <code>count</code> of numbers in the calculator stack.
     * If the stack size is less than <code>count</code>, all numbers in the stack will be returned.
     *
     * The order of the returned value is bottom first. For example:
     * suppose the stack before peek is bottom -> 1 -> 2 -> 3 -> top, peek(2) will returns [2, 3].
     * @param count the <code>count</code> of number to peek
     * @return the numbers peeked
     */
    List<BigNumber> peek(int count);

}
