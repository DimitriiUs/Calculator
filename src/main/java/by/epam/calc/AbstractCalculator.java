package main.java.by.epam.calc;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Dzmitry_Lapitski on 2/8/2017.
 */
public abstract class AbstractCalculator implements Calculate {

    @Override
    public double performBatch(List<String> data) {
        Queue<Double> operandStack = new LinkedList<>();
        Operation operation = null;
        for (String item : data) {
            if (isOperand(item)) {
                operandStack.offer(getOperand(item));
            } else {
                operation = Operation.getOperation(item);
            }
            if (operation != null && operation.isUnary()) {
                operandStack.offer(perform(operation, operandStack.poll(), 0));
                operation = null;
            } else if (operation != null && operandStack.size() > 1) {
                operandStack.offer(perform(operation, operandStack.poll(), operandStack.poll()));
                operation = null;
            }
        }

        return operandStack.peek();
    }

    private boolean isOperand(String item) {
        String regex = "^\\d+(\\.\\d+)*$";
        return item.matches(regex);
    }

    private double getOperand(String item) {
        return Double.parseDouble(item);
    }

}
