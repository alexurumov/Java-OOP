package cresla.entities.modules;

import cresla.interfaces.EnergyModule;

public abstract class EnergyModules extends Modules implements EnergyModule {
    private int energyOutput;

    protected EnergyModules(int id, int energyOutput) {
        super(id);
        this.energyOutput = energyOutput;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Energy Output: ").append(this.getEnergyOutput());
        return super.toString() + sb.toString();
    }
}
