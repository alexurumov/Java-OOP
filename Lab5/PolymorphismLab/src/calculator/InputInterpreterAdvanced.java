package calculator;

import java.util.ArrayDeque;

public class InputInterpreterAdvanced extends InputInterpreter {
    private ArrayDeque<Integer> memory;
    public InputInterpreterAdvanced(CalculationEngine engine) {
        super(engine);
        this.memory = new ArrayDeque<>();
    }

    @Override
    public Operation getOperation(String operation) {
        if (operation.equals("/")) {
            return new DivisionOperation();
        }
        if (operation.equals("ms")) {
            return new MemorySaveOperation(this.memory);
        }
        if (operation.equals("mr")) {
            return new MemoryRecallOperation(this.memory);
        }

        return super.getOperation(operation);

    }
}

