package wang.michaelhai.rpncalculator.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {
    public static void assertBigNumberList(List<BigNumber> result, String... expected) {
        List<BigNumber> expectedList = Arrays.asList(expected)
                                             .stream()
                                             .map(BigNumber::new)
                                             .collect(Collectors.toList());
        assertEquals(expectedList, result);
    }
}
