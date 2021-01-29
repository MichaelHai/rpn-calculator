package wang.michaelhai.rpncalculator.commandline;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.InvalidOperationException;
import wang.michaelhai.rpncalculator.core.operators.InsufficientParametersException;

import java.io.PrintStream;
import java.util.Arrays;

import static org.mockito.Mockito.verify;

@DisplayName("CommandLineFormatter")
@ExtendWith(MockitoExtension.class)
class CommandLinePrinterImplTest {
    @InjectMocks
    private CommandLinePrinterImpl formatter;
    @Mock
    private PrintStream printStream;

    @Test
    @DisplayName("should format stack pretty")
    public void testPrintStack() {
        formatter.printStack(Arrays.asList(new BigNumber("1"), new BigNumber("2.2000"),
            new BigNumber("1.0123456789987")));
        verify(printStream).println("stack: 1 2.2 1.0123456789");
    }

    @Test
    @DisplayName("should format error pretty")
    public void testPrintError() {
        formatter.printError(new InvalidOperationException("*", 10, Arrays.asList(new BigNumber("2"), new BigNumber(
            "-1")), new InsufficientParametersException()));

        verify(printStream).println("operator * (position: 11): insufficient parameters");
        verify(printStream).println("stack: 2 -1");
    }
}
