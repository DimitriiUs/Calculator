package main.java.by.epam.calc.impl;

import main.java.by.epam.calc.AbstractCalculator;
import main.java.by.epam.calc.Operation;

/**
 * @author Dzmitry_Lapitski on 2/8/2017.
 */
public class SimpleCalculator extends AbstractCalculator {

    @Override
    public double perform(Operation operation, double operand1, double operand2) {
        switch (operation) {
            case SUMMATION:
                return operand1 + operand2;
            case SUBTRACTION:
                return operand1 - operand2;
            case MULTIPLICATION:
                return operand1 * operand2;
            case DIVISION:
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException();
        }
    }

}
