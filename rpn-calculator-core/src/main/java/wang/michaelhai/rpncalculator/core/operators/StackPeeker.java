package wang.michaelhai.rpncalculator.core.operators;

import java.math.BigDecimal;
import java.util.List;

/**
 * The functional interface used to peek the top numbers in the stack to calculate
 */
@FunctionalInterface
public interface StackPeeker {
    /**
     * Peak the top given <code>count</code> of numbers in the calculator stack
     * @param count the <code>count</code> of number to peek
     * @return the numbers peeked
     */
    List<BigDecimal> peek(int count);

}
