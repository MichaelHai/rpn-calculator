package wang.michaelhai.rpncalculator.core.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

        stackService.modify(0, Arrays.asList(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ZERO));
    }

    @Test
    @DisplayName("should be able to push and peek values")
    public void testPushAndPeek() {
        List<BigDecimal> peeked = stackService.peek(2);
        assertEquals(Arrays.asList(BigDecimal.TEN, BigDecimal.ZERO), peeked);
    }

    @Test
    @DisplayName("should be able to peek all values")
    public void testPeekAll() {
        List<BigDecimal> peeked = stackService.peek(3);

        assertEquals(Arrays.asList(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ZERO), peeked);
    }

    @Test
    @DisplayName("should be able to peek more then exsiting values")
    public void testPeekOverflow() {
        List<BigDecimal> peeked = stackService.peek(4);

        assertEquals(Arrays.asList(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ZERO), peeked);
    }

    @Test
    @DisplayName("should be able to pop elements")
    public void settPop() {
        List<BigDecimal> popped = stackService.modify(2, Collections.emptyList());

        List<BigDecimal> remaining = stackService.peek(3);

        assertEquals(Arrays.asList(BigDecimal.TEN, BigDecimal.ZERO), popped);
        assertEquals(Collections.singletonList(BigDecimal.ONE), remaining);
    }
}
