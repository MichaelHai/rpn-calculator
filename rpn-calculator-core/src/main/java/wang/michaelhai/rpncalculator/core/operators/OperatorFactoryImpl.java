package wang.michaelhai.rpncalculator.core.operators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStackService;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OperatorFactoryImpl implements OperatorFactory {
    private final CalculatorStackService calculatorStackService;

    @Override
    public Operator create(String token) {
        switch (token) {
            case "+":
                return new PlusOperator(calculatorStackService, calculatorStackService);
            case "sqrt":
                return new SqrtOperator(calculatorStackService, calculatorStackService);
            default:
                try {
                    return new SimpleNumberOperator(calculatorStackService, new BigNumber(token));
                } catch (Exception ex) {
                    throw new UnsupportedOperationException("Un-supported token: " + token, ex);
                }
        }
    }
}
