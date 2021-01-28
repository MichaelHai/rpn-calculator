package wang.michaelhai.rpncalculator.core.operators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.operators.impl.*;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OperatorFactoryImpl implements OperatorFactory {

    @Override
    public Operator create(String token) {
        switch (token) {
            case "+":
                return new PlusOperator();
            case "-":
                return new MinusOperator();
            case "*":
                return new MultiplyOperator();
            case "/":
                return new DivideOperator();
            case "sqrt":
                return new SqrtOperator();
            case "undo":
                return new UndoOperator();
            case "clear":
                return new ClearOperator();
            default:
                try {
                    return new SimpleNumberOperator(new BigNumber(token));
                } catch (Exception ex) {
                    throw new UnsupportedOperationException("Un-supported token: " + token, ex);
                }
        }
    }
}
