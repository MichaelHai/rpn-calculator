package wang.michaelhai.rpncalculator.core.stack;

public interface StackModificationCursorRepository {
    StackModificationCursorEntity find();

    void save(StackModificationCursorEntity cursorEntity);
}
