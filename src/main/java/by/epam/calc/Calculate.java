package main.java.by.epam.calc;

import java.util.List;

/**
 * @author Dzmitry_Lapitski on 2/8/2017.
 */
public interface Calculate {

    double perform(Operation operation, double operand1, double operand2);

    double performBatch(List<String> data);

}
