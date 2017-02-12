package main.java.by.epam.calc.impl.factory;

import main.java.by.epam.calc.AbstractCalculator;
import main.java.by.epam.calc.impl.CalculatorWithMemoryCell;
import main.java.by.epam.calc.impl.ScientificCalculator;
import main.java.by.epam.calc.impl.SimpleCalculator;

/**
 * @author Dzmitry_Lapitski on 2/8/2017.
 */
public class CalculatorFactory {

    public static AbstractCalculator getCalculator(int typeCalculator) {
        AbstractCalculator calculator;
        switch (typeCalculator) {
            case 1:
                calculator = new SimpleCalculator();
                break;
            case 2:
                calculator = new CalculatorWithMemoryCell(new SimpleCalculator());
                break;
            case 3:
                calculator = new ScientificCalculator();
                break;
            case 4:
                calculator = new CalculatorWithMemoryCell(new ScientificCalculator());
                break;
            default:
                throw new IllegalArgumentException();
        }
        return calculator;
    }

}
