package cresla;

import cresla.core.ManagerImpl;
import cresla.interfaces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String EXIT_CMD = "Exit";
        final String REACTOR_CMD = "Reactor";
        final String MODULE_CMD = "Module";
        final String REPORT_CMD = "Report";

        Scanner in = new Scanner(System.in);
        Manager manager = new ManagerImpl();

        String line = in.nextLine();

        while (!line.equals(EXIT_CMD)) {
            String[] arguments = line.split("\\s+");
            String command = arguments[0];
            String result = "";

            switch (command) {

                case REACTOR_CMD:
                    result = manager.reactorCommand(Arrays.asList(arguments));
                    break;
                case MODULE_CMD:
                    result = manager.moduleCommand(Arrays.asList(arguments));
                    break;
                case REPORT_CMD:
                    result = manager.reportCommand(Arrays.asList(arguments));
                    break;
            }

            if (!result.isEmpty()) {
                System.out.println(result);
            }
            line = in.nextLine();
        }
        String result = manager.exitCommand(new ArrayList<String>());
        System.out.println(result);
    }
}
