package wang.michaelhai.rpncalculator.core.stack;

import java.math.BigDecimal;
import java.util.List;

public interface CalculatorStackService extends StackPeeker, StackModifier {
    /**
     * List all elements in the same order as <code>StackPeeker</code>
     * @see StackPeeker#peek(int)
     * @return all elements in the stack
     */
    List<BigDecimal> listAll();
}
