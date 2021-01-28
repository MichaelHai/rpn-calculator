package wang.michaelhai.rpncalculator.core.operators.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("UndoOperator")
class UndoOperatorTest {
    @Test
    @DisplayName("should be able to undo operation")
    public void testUndo(@Mock CalculatorStack stack) {
        UndoOperator undoOperator = new UndoOperator();
        undoOperator.setCalculatorStack(stack).run();

        verify(stack).undo();
    }
}
