package wang.michaelhai.rpncalculator.core.operators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OperatorFactoryImpl implements OperatorFactory {
    private final CalculatorStack calculatorStack;

    @Override
    public Operator create(String token) {
        switch (token) {
            case "+":
                return new PlusOperator(calculatorStack);
            case "sqrt":
                return new SqrtOperator(calculatorStack);
            case "clear":
                return new ClearOperator(calculatorStack);
            default:
                try {
                    return new SimpleNumberOperator(calculatorStack, new BigNumber(token));
                } catch (Exception ex) {
                    throw new UnsupportedOperationException("Un-supported token: " + token, ex);
                }
        }
    }
}
