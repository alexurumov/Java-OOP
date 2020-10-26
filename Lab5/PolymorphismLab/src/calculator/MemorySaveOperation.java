package calculator;

import java.util.ArrayDeque;

public class MemorySaveOperation implements Operation {
    private boolean isAdded = false;
    private int result;
    private ArrayDeque<Integer> memory;

    public MemorySaveOperation(ArrayDeque<Integer> memory) {
        this.memory = memory;
        this.isAdded = false;
    }

    @Override
    public void addOperand(int operand) {
        this.memory.push(operand);
        this.result = operand;
        this.isAdded = true;
    }

    @Override
    public int getResult() {
        return this.result;
    }

    @Override
    public boolean isCompleted() {
        return this.isAdded;
    }
}
