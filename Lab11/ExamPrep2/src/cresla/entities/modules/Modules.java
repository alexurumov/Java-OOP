package cresla.entities.modules;

import cresla.interfaces.Module;

public abstract class Modules implements Module {
    private int id;

    protected Modules(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" Module – ").append(this.getId()).append(System.lineSeparator());

        return sb.toString();
    }
}
