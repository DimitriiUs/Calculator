package main.java.by.epam.calc.impl;

import main.java.by.epam.calc.AbstractCalculator;
import main.java.by.epam.calc.MemoryCell;
import main.java.by.epam.calc.Operation;

/**
 * @author Dzmitry_Lapitski on 2/8/2017.
 */
public class CalculatorWithMemoryCell extends AbstractCalculator {

    private MemoryCell memoryCell;
    private SimpleCalculator calculator;

    public CalculatorWithMemoryCell(SimpleCalculator calculator) {
        this.memoryCell = new MemoryCell();
        this.calculator = calculator;
    }

    public double perform(Operation operation, double operand1, double operand2) {
        double result = operand1;
        switch (operation) {
            case MEMORY_SAVE:
                memoryCell.setCell(operand1);
                System.out.println("MEMORY_SAVE: " + memoryCell.getCell());
                break;
            case MEMORY_INCREASE:
                memoryCell.increaseCell(operand1);
                System.out.println("MEMORY_INCREASE: " + memoryCell.getCell());
                break;
            case MEMORY_DECREASE:
                memoryCell.decreaseCell(operand1);
                System.out.println("MEMORY_DECREASE: " + memoryCell.getCell());
                break;
            case MEMORY_RETURN:
                System.out.println("MEMORY_RETURN: " + memoryCell.getCell());
                result = memoryCell.getCell();
                break;
            case MEMORY_CLEAN:
                memoryCell.cleanCell();
                System.out.println("MEMORY_CLEAN: " + memoryCell.getCell());
                break;
            default:
                result = calculator.perform(operation, operand1, operand2);
        }
        return result;
    }

}
