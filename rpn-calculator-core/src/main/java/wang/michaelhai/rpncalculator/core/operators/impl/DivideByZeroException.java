package wang.michaelhai.rpncalculator.core.operators.impl;

import wang.michaelhai.rpncalculator.core.ErrorType;
import wang.michaelhai.rpncalculator.core.RPNCalculatorError;

public class DivideByZeroException extends RuntimeException implements RPNCalculatorError {
    @Override
    public ErrorType getErrorType() {
        return ErrorType.DIVIDE_BY_ZERO;
    }
}
