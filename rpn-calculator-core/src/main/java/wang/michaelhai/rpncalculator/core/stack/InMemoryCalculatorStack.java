package wang.michaelhai.rpncalculator.core.stack;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InMemoryCalculatorStack implements CalculatorStack {
    private final Stack<BigNumber> stack = new Stack<>();
    private final StackModificationHistoryRepository stackModificationHistoryRepository;
    private final StackModificationCursorRepository stackModificationCursorRepository;

    @Override
    public List<BigNumber> modify(int numberToPop, List<BigNumber> valuesToPush) {
        List<BigNumber> resultList = doModification(numberToPop, valuesToPush);

        updateHistory(valuesToPush, resultList);

        return resultList;
    }

    private void updateHistory(List<BigNumber> valuesPushed, List<BigNumber> valuesPopped) {
        StackModificationCursorEntity cursorEntity = stackModificationCursorRepository.find();
        if (cursorEntity == null) {
            cursorEntity = new StackModificationCursorEntity();
            cursorEntity.setCurrentCursor(0L);
        }
        Long currentCursor = cursorEntity.getCurrentCursor();
        currentCursor++;

        StackModificationHistoryEntity historyEntity = new StackModificationHistoryEntity();
        historyEntity.setId(currentCursor);
        historyEntity.setPopped(valuesPopped);
        historyEntity.setPushed(valuesPushed);
        stackModificationHistoryRepository.save(historyEntity);

        cursorEntity.setCurrentCursor(currentCursor);
        stackModificationCursorRepository.save(cursorEntity);
    }

    private List<BigNumber> doModification(int numberToPop, List<BigNumber> valuesToPush) {
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

    @Override
    public void undo() {
        StackModificationCursorEntity cursorEntity = stackModificationCursorRepository.find();
        Long cursorHistoryEntityId = cursorEntity.getCurrentCursor();

        StackModificationHistoryEntity historyEntity = stackModificationHistoryRepository.find(cursorHistoryEntityId);

        doModification(historyEntity.getPushed().size(), historyEntity.getPopped());

        cursorHistoryEntityId--;
        cursorEntity.setCurrentCursor(cursorHistoryEntityId);
        stackModificationCursorRepository.save(cursorEntity);
    }
}
