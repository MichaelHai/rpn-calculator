package wang.michaelhai.rpncalculator.core.operators.impl;

import wang.michaelhai.rpncalculator.core.ErrorType;
import wang.michaelhai.rpncalculator.core.RPNCalculatorError;

public class SqrtOnNegativeException extends RuntimeException implements RPNCalculatorError {
    @Override
    public ErrorType getErrorType() {
        return ErrorType.SQRT_ON_NEGATIVE;
    }
}
