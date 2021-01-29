package wang.michaelhai.rpncalculator.commandline;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.ErrorType;
import wang.michaelhai.rpncalculator.core.InvalidOperationException;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommandLinePrinterImpl implements CommandLinePrinter {
    private final PrintStream out;

    @Override
    public void printStack(List<BigNumber> stack) {

        StringBuilder builder = new StringBuilder("stack: ");
        String content = stack.stream()
                              .map(number -> number.toStringWithMaxScale(10))
                              .collect(Collectors.joining(" "));
        builder.append(content);
        out.println(builder.toString());
    }

    @Override
    public void printError(InvalidOperationException e) {
        String toPrint = String.format(
            "operator %s (position: %d): %s",
            e.getErrorOperator(),
            e.getErrorPosition() + 1,
            errorMessage(e.getErrorType())
        );
        out.println(toPrint);
        printStack(e.getStackStatus());
    }

    private String errorMessage(ErrorType type) {
        switch (type) {
            case INSUFFICIENT_PARAMETER:
                return "insufficient parameters";
            case DIVIDE_BY_ZERO:
                return "divide by zero";
            case SQRT_ON_NEGATIVE:
                return "sqrt on negative";
            case UNSUPPORTED_TOKEN:
                return "unsupported token";
            case UNKNOWN:
            default:
                return "unknown error";
        }
    }
}
