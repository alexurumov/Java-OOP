import core.MachinesManagerImpl;
import entities.MachineFactoryImpl;

import entities.PilotFactoryImpl;
import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PilotFactory pilotFactory = new PilotFactoryImpl();
        MachineFactory machineFactory = new MachineFactoryImpl();
        Map<String, Pilot> pilots = new LinkedHashMap<>();
        Map<String, Machine> machines = new LinkedHashMap<>();

        MachinesManager machinesManager = new MachinesManagerImpl(pilotFactory, machineFactory, pilots, machines);

        Scanner in = new Scanner(System.in);

        String line = in.nextLine();

        while (!line.equals("Over")) {

            String[] inputArgs = line.split("\\s+");

            String command = inputArgs[0];

            String result = "";

            switch (command) {

                case "Hire":
                    String pilotName = inputArgs[1];

                    result = machinesManager.hirePilot(pilotName);
                    break;
                case "Report":
                    pilotName = inputArgs[1];

                    result = machinesManager.pilotReport(pilotName);
                    break;
                case "ManufactureTank":
                    String tankName = inputArgs[1];
                    double attackPoints = Double.parseDouble(inputArgs[2]);
                    double defensePoints = Double.parseDouble(inputArgs[3]);

                    result = machinesManager.manufactureTank(tankName, attackPoints, defensePoints);
                    break;
                case "ManufactureFighter":
                    String fighterName = inputArgs[1];
                    attackPoints = Double.parseDouble(inputArgs[2]);
                    defensePoints = Double.parseDouble(inputArgs[3]);

                    result = machinesManager.manufactureFighter(fighterName, attackPoints, defensePoints);
                    break;
                case "Engage":
                    pilotName = inputArgs[1];
                    String machineName = inputArgs[2];

                    result = machinesManager.engageMachine(pilotName, machineName);
                    break;
                case "Attack":
                    String attacker = inputArgs[1];
                    String defender = inputArgs[2];

                    result = machinesManager.attackMachines(attacker, defender);
                    break;
                case "DefenseMode":
                    machineName = inputArgs[1];

                    result = machinesManager.toggleTankDefenseMode(machineName);
                    break;
                case "AggressiveMode":
                    machineName = inputArgs[1];

                    result = machinesManager.toggleFighterAggressiveMode(machineName);
                    break;

            }

            System.out.println(result);

            line = in.nextLine();
        }
    }
}
