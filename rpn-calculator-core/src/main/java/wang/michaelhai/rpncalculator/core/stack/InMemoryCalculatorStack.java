package wang.michaelhai.rpncalculator.core.stack;

import org.springframework.stereotype.Service;
import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Service
public class InMemoryCalculatorStack implements CalculatorStack {
    private final Stack<BigNumber> stack = new Stack<>();

    @Override
    public List<BigNumber> modify(int numberToPop, List<BigNumber> valuesToPush) {
        BigNumber[] result = new BigNumber[numberToPop];
        for (int i = 0; i < numberToPop; i++) {
            BigNumber popped = stack.pop();
            result[numberToPop - i - 1] = popped;
        }
        valuesToPush.forEach(stack::push);
        return Arrays.asList(result);
    }

    @Override
    public List<BigNumber> peek(int count) {
        int start = count > stack.size() ? 0 : (stack.size() - count);
        return new ArrayList<>(stack.subList(start, stack.size()));
    }

    @Override
    public List<BigNumber> listAll() {
        return peek(stack.size());
    }

    @Override
    public void clear() {
        stack.clear();
    }
}
