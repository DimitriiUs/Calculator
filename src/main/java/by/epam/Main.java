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
