# Калькулятор
<details>
<summary>**Задание**</summary>

Создать ООП-модель для следующей предметной области:

1. Калькулятор простой. Выполняет четыре арифметические операции: сложение, вычитание, умножение, деление.

2. Калькулятор простой с памятью. Выполняет четыре арифметические операции и имеет одну ячейку памяти. С данной ячейкой памяти калькулятор выполняет пять операций: очистить ячейку памяти, записать в ячейку памяти текущее значение, присвоить текущему значению содержимое ячейки памяти, увеличить или уменьшить значение в ячейке памяти на текущее значение.

3. Калькулятор инженерный. Выполняет четыре арифметические операции и вычисляет значения трех функций: косинус, экспонента, корень квадратный.

4. Калькулятор инженерный с памятью. Это комбинация пунктов 2 и 3.

Создать консольное приложение с единственным аргументом командной строки – имя входного файла.

Структура входного файла: строка 1 – тип калькулятора, остальные строки – числа и операции для вычисления выражения, при этом каждое данное находится в одной строке. Например, содержимое файла для выражения cos(5*7-2)+1, которое необходимо вычислить инженерным калькулятором, может иметь вид:

`3`  
`5`  
`*`  
`7`  
`–`  
`2`  
`cos`  
`+`  
`1`

Все исходные данные корректные. Если в исходных данных есть ошибка, то результат работы программы может быть любым, в том числе программа может завершиться аварийно.
В приложении создать экземпляр калькулятора заданного типа и с его помощью вычислить значение выражения, определяемого содержимым файла.

Требования:
- Использовать объектно-ориентированный подход для описания сущностей предметной области.
- Приложение должно быть консольным. Не использовать графический интерфейс!
</details>

<details>
<summary>**Решение**</summary>

```java
package main.java.by.epam;

import main.java.by.epam.calc.AbstractCalculator;
import main.java.by.epam.calc.impl.factory.CalculatorFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Dzmitry_Lapitski on 2/7/2017.
 *
 * Github: https://github.com/DimitriiUs/Calculator
 */
public class Main {

    public static void main(String[] args) {
        List<String> data = readFile(args[0]);

        int calculatorType = Integer.parseInt(data.get(0));
        data.remove(0);

        System.out.print("INPUT EXPRESSION: ");
        for (String str : data) {
            System.out.print(str);
        }
        System.out.println();

        AbstractCalculator calculator = CalculatorFactory.getCalculator(calculatorType);
        double result = calculator.performBatch(data);

        System.out.println("final: " + result);
    }

    private static List<String> readFile(String file) {
        List<String> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file");
        }
        return data;
    }

}
```
***
```java
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
```
***
```java
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
```
***
```java
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
```
***
```java
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
```
***
```java
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
```
***
```java
package main.java.by.epam.calc;

import java.util.List;

/**
 * @author Dzmitry_Lapitski on 2/8/2017.
 */
public interface Calculate {

    double perform(Operation operation, double operand1, double operand2);

    double performBatch(List<String> data);

}
```
***
```java
package main.java.by.epam.calc;

/**
 * @author Dzmitry_Lapitski on 2/8/2017.
 */
public class MemoryCell {

    private double cell;

    public double getCell() {
        return cell;
    }

    public void setCell(double operand) {
        cell = operand;
    }

    public void increaseCell(double operand) {
        cell += operand;
    }

    public void decreaseCell(double operand) {
        cell -= operand;
    }

    public void cleanCell() {
        cell = 0;
    }

}
```
***
```java
package main.java.by.epam.calc;

/**
 * @author Dzmitry_Lapitski on 2/8/2017.
 */
public enum Operation {

    /* Basic operations*/
    SUMMATION("+", OperationType.BINARY),
    SUBTRACTION("-", OperationType.BINARY),
    MULTIPLICATION("*", OperationType.BINARY),
    DIVISION("/", OperationType.BINARY),

    /* Engineering operations */
    COS("COS", OperationType.UNARY),
    EXP("EXP", OperationType.UNARY),
    SQRT("SQRT", OperationType.UNARY),

    /* Memory flow */
    MEMORY_SAVE("MS", OperationType.UNARY),
    MEMORY_INCREASE("MI", OperationType.UNARY),
    MEMORY_DECREASE("MD", OperationType.UNARY),
    MEMORY_RETURN("MR", OperationType.UNARY),
    MEMORY_CLEAN("MC", OperationType.UNARY);

    private String code;
    private OperationType type;

    Operation(String code, OperationType type) {
        this.code = code;
        this.type = type;
    }

    public boolean isUnary() {
        return this.type == OperationType.UNARY;
    }

    public static Operation getOperation(String code) {
        for (Operation operation : values()) {
            if (operation.code.equals(code)) {
                return operation;
            }
        }
        throw new IllegalArgumentException();
    }

}
```
***
```java
package main.java.by.epam.calc;

/**
 * @author Dzmitry_Lapitski on 2/8/2017.
 */
public enum OperationType {

    UNARY, BINARY

}
```
</details>
