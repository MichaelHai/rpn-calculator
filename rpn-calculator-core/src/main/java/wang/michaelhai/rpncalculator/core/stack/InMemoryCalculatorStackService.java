package wang.michaelhai.rpncalculator.core.stack;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Service
public class InMemoryCalculatorStackService implements CalculatorStackService {
    private final Stack<BigDecimal> stack = new Stack<>();

    @Override
    public List<BigDecimal> modify(int numberToPop, List<BigDecimal> valuesToPush) {
        BigDecimal[] result = new BigDecimal[numberToPop];
        for (int i = 0; i < numberToPop; i++) {
            BigDecimal popped = stack.pop();
            result[numberToPop - i - 1] = popped;
        }
        valuesToPush.forEach(stack::push);
        return Arrays.asList(result);
    }

    @Override
    public List<BigDecimal> peek(int count) {
        int start = count > stack.size() ? 0 : (stack.size() - count);
        return new ArrayList<>(stack.subList(start, stack.size()));
    }

    @Override
    public List<BigDecimal> listAll() {
        return peek(stack.size());
    }
}
