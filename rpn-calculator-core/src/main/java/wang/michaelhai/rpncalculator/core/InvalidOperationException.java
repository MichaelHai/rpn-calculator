package wang.michaelhai.rpncalculator.core;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static wang.michaelhai.rpncalculator.core.ErrorType.UNKNOWN;

@Getter
@Slf4j
public class InvalidOperationException extends RuntimeException {
    private final String errorOperator;
    private final int errorPosition;
    private final List<BigNumber> stackStatus;
    private final ErrorType errorType;

    public InvalidOperationException(String errorOperator, int errorPosition, List<BigNumber> stackStatus,
        Exception cause) {
        this.errorOperator = errorOperator;
        this.errorPosition = errorPosition;
        this.stackStatus = stackStatus;
        if (cause instanceof RPNCalculatorError) {
            this.errorType = ((RPNCalculatorError) cause).getErrorType();
        } else {
            this.errorType = UNKNOWN;
            log.error("Unknown error happened", cause);
        }
    }
}
