package wang.michaelhai.rpncalculator.core.operators;

public interface OperatorFactory {
    Operator create(String token);
}
