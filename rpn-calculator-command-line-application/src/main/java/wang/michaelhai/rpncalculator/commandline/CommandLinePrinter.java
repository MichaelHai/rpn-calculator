package wang.michaelhai.rpncalculator.commandline;

import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.InvalidOperationException;

import java.io.PrintStream;
import java.util.List;

public interface CommandLinePrinter {
    void printStack(List<BigNumber> stack);

    void printError(InvalidOperationException e);
}
