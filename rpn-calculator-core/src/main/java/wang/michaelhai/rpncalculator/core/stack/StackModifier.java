package wang.michaelhai.rpncalculator.core.stack;

import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.List;

/**
 * The functional interface used to modify the stack to calculate
 */
@FunctionalInterface
public interface StackModifier {

    /**
     * modify the stack with pop and push operations.
     *
     * The values will be pushed one by one from the beginning of the given list.
     * The order of the popped value follows the same order as <code>StackPeeker</code>
     * @see StackPeeker#peek(int)
     *
     * @param numberToPop  pop the top numbers from the stack
     * @param valuesToPush values to push after popping
     * @return the values popped from the stack
     */
    List<BigNumber> modify(int numberToPop, List<BigNumber> valuesToPush);
}
