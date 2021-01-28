package wang.michaelhai.rpncalculator.core;

import java.math.BigDecimal;
import java.util.List;

/**
 * The core of the PRN Calculator.
 */
public interface CalculationService {
    /**
     * Process a sequence of operators and returns the calculator status after calculation
     * @param input a whitespace separated lists of numbers and operators
     * @return the elements in the stack with bottom first order
     */
    List<BigDecimal> process(String input);
}
