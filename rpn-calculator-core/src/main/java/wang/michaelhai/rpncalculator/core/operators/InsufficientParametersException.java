package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.ErrorType;
import wang.michaelhai.rpncalculator.core.RPNCalculatorError;

public class InsufficientParametersException extends RuntimeException implements RPNCalculatorError {
    @Override
    public ErrorType getErrorType() {
        return ErrorType.INSUFFICIENT_PARAMETER;
    }
}
