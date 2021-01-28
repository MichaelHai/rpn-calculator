package wang.michaelhai.rpncalculator.core.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("InMemoryCalculatorStackService")
class InMemoryCalculatorStackServiceTest {
    private InMemoryCalculatorStackService stackService;

    @BeforeEach
    public void setup() {
        stackService = new InMemoryCalculatorStackService();

        stackService.modify(0, Arrays.asList(new BigNumber("1"), new BigNumber("10"), new BigNumber("0")));
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
}
