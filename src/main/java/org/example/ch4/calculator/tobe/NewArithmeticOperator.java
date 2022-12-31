package org.example.ch4.calculator.tobe;

import org.example.ch4.calculator.domain.PositiveNumber;

public interface NewArithmeticOperator {
    boolean supports(String operator);
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
