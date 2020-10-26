package systemSplit.core;

import systemSplit.hardware.Hardware;
import systemSplit.hardware.HeavyHardware;
import systemSplit.hardware.PowerHardware;
import systemSplit.software.ExpressSoftware;
import systemSplit.software.LightSoftware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static systemSplit.utils.Constants.*;

public class Engine {

    private System system;
    private Dump dump;

    public Engine(System system, Dump dump) {
        this.system = system;
        this.dump = dump;
    }

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(java.lang.System.in));
        String input = reader.readLine();

        while (!input.equals(END_COMMAND)) {

            String command = input.substring(0, input.indexOf("("));
            String[] args = input
                    .substring(input.indexOf("(") + 1, input.length() - 1)
                    .split(", ");

            switch (command) {

                case REGISTER_POWER_CMD:
                    this.system.addHardware(new PowerHardware(
                            args[0],
                            POWER_TYPE,
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2])));
                    break;
                case REGISTER_HEAVY_CMD:
                    this.system.addHardware(new HeavyHardware(
                            args[0],
                            HEAVY_TYPE,
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2])));
                    break;
                case REGISTER_EXPRESS_CMD:
                    this.system.addSoftware(args[0], new ExpressSoftware(
                            args[1],
                            EXPRESS_TYPE,
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3])));
                    break;
                case REGISTER_LIGHT_CMD:
                    this.system.addSoftware(args[0], new LightSoftware(
                            args[1],
                            LIGHT_TYPE,
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3])));
                    break;
                case RELEASE_CMD:
                    this.system.removeSoftware(args[0], args[1]);
                    break;
                case ANALYSE_CMD:
                    java.lang.System.out.println(this.system.analyse());
                    break;
                case DUMP_CMD:
                    Hardware hardware = this.system.removeHardware(args[0]);
//                    this.system.getDump().putIfAbsent(hardware.getName(), hardware);
                    this.dump.addHardware(hardware);
                    break;
                case RESTORE_CMD:
//                    Hardware dumpHardware = this.system.getDump().remove(args[0]);
                    Hardware dumpHardware = this.dump.removeHardware(args[0]);
                    if (dumpHardware != null) {
                        this.system.addHardware(dumpHardware);
                    }
                    break;
                case DESTROY_CMD:
//                    this.system.getDump().remove(args[0]);
                    this.dump.removeHardware(args[0]);
                    break;
                case DUMP_ANALYSE_CMD:
//                    java.lang.System.out.println(this.system.dumpAnalyse());
                    java.lang.System.out.println(this.dump.analyse());
                    break;

            }

            input = reader.readLine();
        }

        java.lang.System.out.println(this.system.split());
    }

}

