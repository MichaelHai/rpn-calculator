package wang.michaelhai.rpncalculator.core.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static wang.michaelhai.rpncalculator.core.TestUtils.assertBigNumberList;
import static wang.michaelhai.rpncalculator.core.TestUtils.bigNumberList;

@DisplayName("InMemoryCalculatorStackService")
@ExtendWith(MockitoExtension.class)
class InMemoryCalculatorStackTest {
    @InjectMocks
    private InMemoryCalculatorStack stackService;

    @Mock
    private StackModificationHistoryRepository historyRepository;
    @Mock
    private StackModificationCursorRepository cursorRepository;

    @Captor
    private ArgumentCaptor<StackModificationHistoryEntity> historyEntityCaptor;

    @BeforeEach
    public void setup() {
        stackService.modify(0, Arrays.asList(new BigNumber("1"), new BigNumber("10"), new BigNumber("0")));
        clearInvocations(cursorRepository);
        clearInvocations(historyRepository);
    }

    @Test
    @DisplayName("should be able to push and peek values")
    public void testPushAndPeek() {
        List<BigNumber> peeked = stackService.peek(2);
        assertEquals(Arrays.asList(new BigNumber("10"), new BigNumber("0")), peeked);
    }

    @Test
    @DisplayName("should be able to peek all values")
    public void testPeekAll() {
        List<BigNumber> peeked = stackService.peek(3);

        assertEquals(Arrays.asList(new BigNumber("1"), new BigNumber("10"), new BigNumber("0")), peeked);
    }

    @Test
    @DisplayName("should be able to peek more then exsiting values")
    public void testPeekOverflow() {
        List<BigNumber> peeked = stackService.peek(4);

        assertEquals(Arrays.asList(new BigNumber("1"), new BigNumber("10"), new BigNumber("0")), peeked);
    }

    @Test
    @DisplayName("should be able to pop elements")
    public void testPop() {
        List<BigNumber> popped = stackService.modify(2, Collections.emptyList());

        List<BigNumber> remaining = stackService.peek(3);

        assertEquals(Arrays.asList(new BigNumber("10"), new BigNumber("0")), popped);
        assertEquals(Collections.singletonList(new BigNumber("1")), remaining);
    }

    @Test
    @DisplayName("should be able to list all elements")
    public void testListAll() {
        List<BigNumber> all = stackService.listAll();

        assertEquals(Arrays.asList(new BigNumber("1"), new BigNumber("10"), new BigNumber("0")), all);
    }

    @Test
    @DisplayName("should be able to undo the previous modify")
    public void testUndo() {
        StackModificationCursorEntity cursorEntity = new StackModificationCursorEntity();
        cursorEntity.setCurrentCursor(2L);
        when(cursorRepository.find()).thenReturn(cursorEntity);

        stackService.modify(2, Arrays.asList(new BigNumber("11"), new BigNumber("20")));

        List<BigNumber> all = stackService.listAll();

        assertBigNumberList(all, "1", "11", "20");
        verify(cursorRepository).save(argThat(entity -> entity.getCurrentCursor() == 3L));
        verify(historyRepository).save(historyEntityCaptor.capture());

        StackModificationHistoryEntity entity = historyEntityCaptor.getValue();
        assertEquals(3L, entity.getId());
        assertBigNumberList(entity.getPushed(), "11", "20");
        assertBigNumberList(entity.getPopped(), "10", "0");

        when(historyRepository.find(3L)).thenReturn(entity);
        stackService.undo();

        all = stackService.listAll();

        assertBigNumberList(all, "1", "10", "0");
    }
}
