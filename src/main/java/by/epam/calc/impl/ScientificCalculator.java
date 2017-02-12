package main.java.by.epam.calc.impl;

import main.java.by.epam.calc.Operation;

/**
 * @author Dzmitry_Lapitski on 2/8/2017.
 */
public class ScientificCalculator extends SimpleCalculator {

    @Override
    public double perform(Operation operation, double operand1, double operand2) {
        switch (operation) {
            case COS:
                return Math.cos(operand1);
            case EXP:
                return Math.exp(operand1);
            case SQRT:
                return Math.sqrt(operand1);
            default:
                return super.perform(operation, operand1, operand2);
        }
    }

}
