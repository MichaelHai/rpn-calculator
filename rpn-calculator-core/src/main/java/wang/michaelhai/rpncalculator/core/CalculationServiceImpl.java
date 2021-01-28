package wang.michaelhai.rpncalculator.core;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.michaelhai.rpncalculator.core.operators.InsufficientParametersException;
import wang.michaelhai.rpncalculator.core.operators.OperatorFactory;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CalculationServiceImpl implements CalculationService {
    private final OperatorFactory operatorFactory;
    private final CalculatorStack stackService;

    @Override
    public List<BigNumber> process(String input) {
        log.debug("Start processing input: {}", input);
        List<Token> tokens = parseInput(input);
        for (Token token : tokens) {
            try {
                this.executeOperation(token.operator);
            } catch (InsufficientParametersException e) {
                throw new InvalidOperationException(token.operator, token.pos, stackService.listAll());
            }
        }
        return stackService.listAll();
    }

    private List<Token> parseInput(String input) {
        String delimiter = " ";
        StringTokenizer tokenizer = new StringTokenizer(input, delimiter, true);
        List<Token> result = new ArrayList<>();
        int pos = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (!token.contains(delimiter)) {
                result.add(Token.of(token, pos));
            }
            pos += token.length();
        }
        return result;
    }

    private void executeOperation(String operator) {
        operatorFactory
            .create(operator)
            .setCalculatorStack(stackService)
            .run();
    }

    @AllArgsConstructor
    private static class Token {
        String operator;
        int pos;

        public static Token of(String operator, int pos) {
            return new Token(operator, pos);
        }
    }
}
