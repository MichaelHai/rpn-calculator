# Introduction

This project is a spring based RPN (Reverse Polish Notation) calculator.

# How to run

```shell
# Build projects if not build
./mvnw clean package
# run the application
./mvnw -pl rpn-calculator-command-line-application spring-boot:run
# An alternative way to run
java -jar rpn-calculator-command-line-application/target/rpn-calculator-command-line-application-0.0.1-SNAPSHOT.jar
```

# Design & Implementation Considerations

## Operator abstraction

All input is parsed as tokens and all the tokens will be transferred as an `Operator`.

An `Operator` object represents a modification on the `CalculatorStack`. In this case, there are 2 operations designed for this interface: `setCalculatorStack` to set the `CalculatorStack` this operation will perform on and `run` to perform the operation.

The `Operator` abstraction is designed not only for calculation operations like +, -, *, / but all kinds of modifications that will be performed on the `CalculationStack`. For example, a single number input is also implemented as `SimpleNumberOperator` which will simply push the number into the `CalculatorStack`.

Future implementation for more operators require 2 steps:
1. Implement the `Operator` interface: there is an abstract `BaseOperator` which already implemented the `setCalculatorStack` operation. There is another abstract `AbstractCalculationOperator` on top of `BaseOperator` which implements a template method for calculation operations which is pop some numbers from the stack, execute calculation and push result back.
2. Add branches in `OperatorFactoryImpl#create` to make the new operator available for the calculator.

## CalculatorStack abstraction

The interface `CalculatorStack` represent a stack that can be operated on.

In the current implementation, there is a singleton stack implemented by `InMemoryCalculatorStack` as a spring bean. This implementation is enough for the current command line based application.
However, when it is available through Web UI, there might be requirements to support multiple sessions of calculators. The `CalculatorStack` abstraction makes it easy to change code without affecting the `Operator` implementations.
Future developers can modify the caller of `Operator`s to supply different `CalculatorStack` instances according to the input.

## Undo support

To implement undo, it is required record history operations or snapshots.
With the history operations recorded, undo can be implemented by reversing the operations. This is the current implementation.
With the snapshots recorded, undo can be implemented by Momento pattern.

I choose to record operation instead of using Momento pattern because
1. All operations only contains popping elements and pushing some other elements after popping. It is easy to reverse the operation. I reuse the same logic as performing the operation that needs to be undone but just reverse the push and pop arguments. 
2. Using Momento pattern requires re-building the entire stack when undoing. It may not be efficient if the stack is large. In comparison, reversed operations will normally operate in small set of elements.

It is expected to have redo feature in the future. So I use a cursor to move between the recorded history entities. When undoing, the cursor is moved backward through the history and when redoing, we can move the cursor forward.

In order to implement redo in the future, the operation records whose id is larger than the latest normal operation should be removed or marked in order to avoid operation sequence like `1 2 3 + * undo undo + redo`. The last redo should be invalid because redos should be only allowed immediately after undos.
Currently, since there's only requirement for undo, the removing/marking is not performed in source code.

## CalculationService

The `CalculationService` accept a `String` as input as required. The output is a list of all elements in the stack from bottom to top.

I designed the returned type as a list so that the presentation layer can decide whatever way it wants to use to present the stack, "customize the colour of each number in the stack depends on its position" for example.

## Error handling

The error handling is designed with 2 level:
* InvalidOperationException: it will be thrown by `CalculationService#process` for presentation layer to handle. There are rich information in this exception including error operator, error position, the current stack status and the error type. Presentation layer can use these properties with the best suitable way for the UI type.
* RPNCalculatorError: this is an interface defined to delegate `ErrorTypes` to internal `Exceptions` like `DivideByZeroException`, `SqrtOnNegativeException` etc. All `Exceptions` will be caught and transformed to `InvalidOperationException`. However, non-`RPNCalculatorError` exceptions will be identified as `UNKNOWN` for callers to avoid breaking the entire system. Error log will be printed for such `UNKNOWN` exceptions. They should be treated as bug in implementation which requires developers fixing.
