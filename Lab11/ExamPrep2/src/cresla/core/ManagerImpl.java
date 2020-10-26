package cresla.core;

import cresla.entities.modules.CooldownSystem;
import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.*;
import cresla.interfaces.Module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {
    private static final String CRYO_TYPE = "Cryo";
    private static final String HEAT_TYPE = "Heat";
    private static final String CRYOGEN_ROD_TYPE = "CryogenRod";
    private static final String HEAT_PROCESSOR_TYPE = "HeatProcessor";
    private static final String COOLING_TYPE = "CooldownSystem";

    private int id;
    private Map<Integer, Reactor> reactors;
    private Map<Integer, Module> modules;

    public ManagerImpl() {
        this.id = 1;
        this.reactors = new HashMap<>();
        this.modules = new HashMap<>();
    }

    @Override
    public String reactorCommand(List<String> arguments) {
        String reactorType = arguments.get(1);
        int additionalParameter = Integer.parseInt(arguments.get(2));
        int moduleCapacity = Integer.parseInt(arguments.get(3));

        Reactor reactor = null;

        switch (reactorType) {
            case CRYO_TYPE:
                reactor = new CryoReactor(this.id, moduleCapacity, additionalParameter);
                break;
            case HEAT_TYPE:
                reactor = new HeatReactor(this.id, moduleCapacity, additionalParameter);
                break;
        }

        this.reactors.putIfAbsent(this.id, reactor);

        return String.format("Created %s Reactor - %d", reactorType, this.id++);
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        int reactorId = Integer.parseInt(arguments.get(1));
        String moduleType = arguments.get(2);
        int additionalParameter = Integer.parseInt(arguments.get(3));

        Module module = null;

        switch (moduleType) {
            case CRYOGEN_ROD_TYPE:
                module = new CryogenRod(this.id, additionalParameter);
                break;
            case HEAT_PROCESSOR_TYPE:
                module = new HeatProcessor(this.id, additionalParameter);
                break;
            case COOLING_TYPE:
                module = new CooldownSystem(this.id, additionalParameter);
                break;
        }

        try{
            if (moduleType.equals(CRYOGEN_ROD_TYPE)) {
                this.reactors.get(reactorId).addEnergyModule((EnergyModule) module);
            } else {
                this.reactors.get(reactorId).addAbsorbingModule((AbsorbingModule) module);
            }
        } catch (Exception error) {

        }

        modules.putIfAbsent(this.id, module);

        return String.format("Added %s - %d to Reactor - %d", moduleType, this.id++, reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        int id = Integer.parseInt(arguments.get(1));

        String result = "";

        if (this.reactors.containsKey(id)) {
            result = this.reactors.get(id).toString();
        } else if (this.modules.containsKey(id)) {
            result = this.modules.get(id).toString();
        }

        return result;
    }

    @Override
    public String exitCommand(List<String> arguments) {

        int cryoReactorsCount = (int) this.reactors.values()
                .stream()
                .filter(reactor -> reactor.getClass().getSimpleName().equals("CryoReactor"))
                .count();
        
        int heatReactorsCount = (int) this.reactors.values()
                .stream()
                .filter(reactor -> reactor.getClass().getSimpleName().equals("HeatReactor"))
                .count();
        
        int energyModulesCount = (int) this.modules.values()
                .stream()
                .filter(module -> module.getClass().getSimpleName().equals(CRYOGEN_ROD_TYPE))
                .count();
        
        int absorbingModulesCount = (int) this.modules.values()
                .stream()
                .filter(module -> (!module.getClass().getSimpleName().equals(CRYOGEN_ROD_TYPE)))
                .count();
        
        long totalEnergyOutput = 0;
        long totalHeatAbsorbing = 0;

        for (Reactor reactor : this.reactors.values()) {
            if (reactor.getTotalEnergyOutput() <= reactor.getTotalHeatAbsorbing()) {
                totalEnergyOutput += reactor.getTotalEnergyOutput();
            }
            totalHeatAbsorbing += reactor.getTotalHeatAbsorbing();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Cryo Reactors: ").append(cryoReactorsCount).append(System.lineSeparator())
                .append("Heat Reactors: ").append(heatReactorsCount).append(System.lineSeparator())
                .append("Energy Modules: ").append(energyModulesCount).append(System.lineSeparator())
                .append("Absorbing Modules: ").append(absorbingModulesCount).append(System.lineSeparator())
                .append("Total Energy Output: ").append(totalEnergyOutput).append(System.lineSeparator())
                .append("Total Heat Absorbing: ").append(totalHeatAbsorbing).append(System.lineSeparator());
        return sb.toString();
    }
}
