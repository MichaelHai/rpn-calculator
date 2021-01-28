package wang.michaelhai.rpncalculator.core.operators;

import java.math.BigDecimal;
import java.util.List;

/**
 * The functional interface used to modify the stack to calculate
 */
@FunctionalInterface
public interface StackModifier {

    /**
     * modify the stack with pop and push operations
     *
     * @param numberToPop  pop the top numbers from the stack
     * @param valuesToPush values to push after popping
     * @return the values popped from the stack
     */
    List<BigDecimal> modify(int numberToPop, List<BigDecimal> valuesToPush);
}
