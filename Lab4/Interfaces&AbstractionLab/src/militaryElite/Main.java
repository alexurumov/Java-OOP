package militaryElite;

import militaryElite.classImpls.*;
import militaryElite.interfaces.Private;
import militaryElite.interfaces.Soldier;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        List<Soldier> privates = new ArrayList<>();
        List<Soldier> soldiers = new ArrayList<>();
        while (!line.equals("End")) {
            String[] input = line.split("\\s+");
            String kindOfSoldier = input[0];
            String id = input[1];
            String firstName = input[2];
            String lastName = input[3];
            Soldier soldier = null;
            switch (kindOfSoldier) {
                case "Private":
                    double salary = Double.parseDouble(input[4]);
                    soldier = new PrivateImpl(id, firstName, lastName, salary);
                    privates.add(soldier);
                    break;
                case "LeutenantGeneral":
                    salary = Double.parseDouble(input[4]);
                    List<Private> listOfPrivates = new ArrayList<>();
                    for (int i = 5; i < input.length; i++) {
                        String currentId = input[i];
                        Soldier first = privates.stream()
                                .filter(pr -> pr.getId().equals(currentId))
                                .findFirst()
                                .orElse(null);
                        listOfPrivates.add((Private) first);
                    }
                    soldier = new LeutenantGeneralImpl(id, firstName, lastName, salary, listOfPrivates);
                    break;
                case "Engineer":
                    try {
                        salary = Double.parseDouble(input[4]);
                        String corps = input[5];
                        List<Repair> listOfRepairs = new ArrayList<>();
                        for (int i = 6; i < input.length; i++) {
                            try {
                                String partName = input[i];
                                i++;
                                int hoursWorked = Integer.parseInt(input[i]);
                                Repair repair = new Repair(partName, hoursWorked);
                                listOfRepairs.add(repair);
                            } catch (IllegalArgumentException error) { }
                        }
                        soldier = new EngineerImpl(id, firstName, lastName, salary, corps, listOfRepairs);
                    } catch (IllegalArgumentException error) { }
                    break;
                case "Commando":
                    try {
                        salary = Double.parseDouble(input[4]);
                        String corps = input[5];
                        List<Mission> listOfMissions = new ArrayList<>();
                        for (int i = 6; i < input.length; i++) {
                            try {
                                String codeName = input[i];
                                i++;
                                String state = input[i];
                                Mission mission = new Mission(codeName, state);
                                listOfMissions.add(mission);
                            } catch (IllegalArgumentException error) { }
                        }
                        soldier = new ComandoImpl(id, firstName, lastName, salary, corps, listOfMissions);
                    } catch (IllegalArgumentException error) { }
                    break;
                case "Spy":
                    String codeNumber = input[4];
                    soldier = new SpyImpl(id, firstName, lastName, codeNumber);
                    break;
                default:
                    break;
            }
            if (soldier != null) { soldiers.add(soldier); }
            line = in.nextLine();
        }
        soldiers.forEach(System.out::println);
    }
}
