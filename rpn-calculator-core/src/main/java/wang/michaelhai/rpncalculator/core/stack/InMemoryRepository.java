package wang.michaelhai.rpncalculator.core.stack;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the mock of a database which provides in memory implementation for repositories
 */
@Component
public class InMemoryRepository implements StackModificationCursorRepository, StackModificationHistoryRepository {
    private final Map<Long, StackModificationHistoryEntity> historyEntityMap = new HashMap<>();
    private StackModificationCursorEntity cursorEntity;

    @Override
    public StackModificationCursorEntity find() {
        return cursorEntity;
    }

    @Override
    public void save(StackModificationCursorEntity cursorEntity) {
        this.cursorEntity = cursorEntity;
    }

    @Override
    public StackModificationHistoryEntity save(StackModificationHistoryEntity historyEntity) {
        return historyEntityMap.put(historyEntity.getId(), historyEntity);
    }

    @Override
    public StackModificationHistoryEntity find(Long id) {
        return historyEntityMap.get(id);
    }
}
