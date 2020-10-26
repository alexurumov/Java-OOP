package calculator;

public class Extensions {
    public static InputInterpreterAdvanced buildInterpreter(CalculationEngine engine) {
        return new InputInterpreterAdvanced(engine);
    }
}
