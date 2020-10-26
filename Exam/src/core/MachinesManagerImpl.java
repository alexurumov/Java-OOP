package core;

import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;

import entities.FighterImpl;
import entities.TankImpl;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.Map;

import static common.OutputMessages.*;

public class MachinesManagerImpl implements MachinesManager {
    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;

    public MachinesManagerImpl(PilotFactory pilotFactory, MachineFactory machineFactory, Map<String, Pilot> pilots, Map<String, Machine> machines) {
        this.pilotFactory = pilotFactory;
        this.machineFactory = machineFactory;
        this.pilots = pilots;
        this.machines = machines;
    }

    @Override
    public String hirePilot(String name) {
        Pilot pilot = this.pilotFactory.createPilot(name);

        if (this.pilots.containsKey(name)) {
            return String.format(pilotExists, name);
        }

        this.pilots.put(name, pilot);

        return String.format(pilotHired, name);
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        Machine machine = this.machineFactory.createTank(name, attackPoints, defensePoints);
        if (this.machines.containsKey(name)) {
            return String.format(machineExists, name);
        }

        this.machines.put(name, machine);

        return String.format(tankManufactured, name, attackPoints, defensePoints);
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        Machine machine = this.machineFactory.createFighter(name, attackPoints, defensePoints);
        if (this.machines.containsKey(name)) {
            return String.format(machineExists, name);
        }

        this.machines.put(name, machine);

        return String.format(fighterManufactured, name, attackPoints, defensePoints);
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {
        if (!this.pilots.containsKey(selectedPilotName)) {
            return String.format(pilotNotFound, selectedPilotName);
        }
        Pilot pilot = this.pilots.get(selectedPilotName);

        if (!this.machines.containsKey(selectedMachineName)) {
            return String.format(machineNotFound, selectedMachineName);
        }

        Machine machine = this.machines.get(selectedMachineName);
        if (machine.getPilot() != null) {
            return String.format(machineHasPilotAlready, selectedMachineName);
        }

        machine.setPilot(pilot);
        pilot.addMachine(machine);

        return String.format(machineEngaged, selectedPilotName, selectedMachineName);
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {
        if (!machines.containsKey(attackingMachineName)) {
            return String.format(machineNotFound, attackingMachineName);
        }
        if (!machines.containsKey(defendingMachineName)) {
            return String.format(machineNotFound, defendingMachineName);
        }

        Machine attacker = this.machines.get(attackingMachineName);
        Machine defender = this.machines.get(defendingMachineName);
        attacker.getTargets().add(defendingMachineName);

        if (attacker.getAttackPoints() > defender.getDefensePoints()) {
            defender.setHealthPoints(defender.getHealthPoints() - (attacker.getAttackPoints() - defender.getDefensePoints()));
            if (defender.getHealthPoints() < 0) {
                defender.setHealthPoints(0);
            }
        }

        return String.format(attackSuccessful, defendingMachineName, attackingMachineName, defender.getHealthPoints());
    }

    @Override
    public String pilotReport(String pilotName) {
        if (!this.pilots.containsKey(pilotName)) {
            return String.format(pilotNotFound, pilotName);
        }
        Pilot pilot = this.pilots.get(pilotName);

        return pilot.report();
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        if (!this.machines.containsKey(fighterName)) {
            return String.format(machineNotFound, fighterName);
        }

        Machine machine = this.machines.get(fighterName);

        if (machine instanceof TankImpl) {
            return String.format(notSupportedOperation, fighterName);
        }

        FighterImpl fighter = (FighterImpl) machine;
        fighter.toggleAggressiveMode();

        return String.format(fighterOperationSuccessful, fighterName);
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        if (!this.machines.containsKey(tankName)) {
            return String.format(machineNotFound, tankName);
        }

        Machine machine = this.machines.get(tankName);

        if (machine instanceof FighterImpl) {
            return String.format(notSupportedOperation, tankName);
        }

        TankImpl tank = (TankImpl) machine;
        tank.toggleDefenseMode();

        return String.format(tankOperationSuccessful, tankName);
    }
}
