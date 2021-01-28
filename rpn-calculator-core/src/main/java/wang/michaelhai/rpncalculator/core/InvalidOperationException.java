package wang.michaelhai.rpncalculator.core;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidOperationException extends RuntimeException {
    private final String errorOperator;
    private final int errorPosition;
    private final List<BigNumber> stackStatus;

    public InvalidOperationException(String errorOperator, int errorPosition, List<BigNumber> stackStatus) {
        this.errorOperator = errorOperator;
        this.errorPosition = errorPosition;
        this.stackStatus = stackStatus;
    }
}
