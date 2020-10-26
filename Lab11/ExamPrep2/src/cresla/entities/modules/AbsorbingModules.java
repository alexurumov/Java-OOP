package cresla.entities.modules;

import cresla.interfaces.AbsorbingModule;

public abstract class AbsorbingModules extends Modules implements AbsorbingModule {
    private int heatAbsorbing;

    protected AbsorbingModules(int id, int heatAbsorbing) {
        super(id);
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Heat Absorbing: ").append(this.getHeatAbsorbing());
        return super.toString() + sb.toString();
    }
}
