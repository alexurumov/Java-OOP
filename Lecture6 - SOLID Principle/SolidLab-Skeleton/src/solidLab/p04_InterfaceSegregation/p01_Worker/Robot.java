package solidLab.p04_InterfaceSegregation.p01_Worker;

import solidLab.p04_InterfaceSegregation.p01_Worker.interfaces.Worker;

public class Robot implements Worker {
    @Override
    public void eat() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void work() {
        //work
    }

    @Override
    public void sleep() {
        throw new UnsupportedOperationException();
    }
}
