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
