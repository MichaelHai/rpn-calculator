package wang.michaelhai.rpncalculator.core.stack;

public interface StackModificationHistoryRepository {
    StackModificationHistoryEntity save(StackModificationHistoryEntity historyEntity);

    StackModificationHistoryEntity find(Long id);
}
