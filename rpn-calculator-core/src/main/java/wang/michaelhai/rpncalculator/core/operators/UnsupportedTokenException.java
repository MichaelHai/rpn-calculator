package wang.michaelhai.rpncalculator.core.operators;

import wang.michaelhai.rpncalculator.core.ErrorType;
import wang.michaelhai.rpncalculator.core.RPNCalculatorError;

public class UnsupportedTokenException extends RuntimeException implements RPNCalculatorError {
    private final String token;

    public UnsupportedTokenException(String token) {
        this.token = token;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.UNSUPPORTED_TOKEN;
    }

    @Override
    public String getMessage() {
        return "Unsupported token: " + token;
    }
}
