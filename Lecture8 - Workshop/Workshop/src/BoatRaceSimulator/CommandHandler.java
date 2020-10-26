package BoatRaceSimulator;

import BoatRaceSimulator.exceptions.*;
import BoatRaceSimulator.models.boat.BoatResult;

import java.util.List;

public class CommandHandler {

    private static final String TRUE_STRING = "true";
    private final ApplicationController applicationController;

    public CommandHandler() {
        this.applicationController = new ApplicationController();
    }

    public void handle(String[] split) throws DuplicateModelException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException {
        String result = "";
        try {
            switch (split[0]) {
                case "CreateBoatEngine":
                    result = createBoatEngine(split);
                    break;
                case "CreateRowBoat":
                    result = createRowBoat(split);
                    break;
                case "CreateSailBoat":
                    result = createSailBoat(split);
                    break;
                case "CreatePowerBoat":
                    result = createPowerBoat(split);
                    break;
                case "CreateYacht":
                    result = createYacht(split);
                    break;
                case "OpenRace":
                    result = openRace(split);
                    break;
                case "SignUpBoat":
                    result = singUpBoat(split);
                    break;
                case "StartRace":
                    result = startRace();
                    break;
                case "GetStatistic":
                    result = getStatistic();
                default:
                    throw new IllegalAccessException();
            }

            if (!result.isEmpty()) {
                System.out.println(result);
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    private String getStatistic() {
        return this.applicationController.getStatistic();
    }

    private String startRace() throws NoSetRaceException, InsufficientContestantsException {
        List<BoatResult> top3 = this.applicationController.startRace();

        return String.format("First place: %s Model: %s Time: %s \n" +
                "Second place: %s Model: %s Time: %s \n" +
                "Third place: %s Model: %s Time: %s ",
                top3.get(0).getBoat().getClass().getSimpleName(), top3.get(0).getBoat().getModel(), top3.get(0).getRaceTime() > 0 ? String.format("%.2f sec", top3.get(0).getRaceTime()) : "Did not finish!",
                top3.get(1).getBoat().getClass().getSimpleName(), top3.get(1).getBoat().getModel(), top3.get(1).getRaceTime() > 0 ? String.format("%.2f sec", top3.get(1).getRaceTime()) : "Did not finish!",
                top3.get(2).getBoat().getClass().getSimpleName(), top3.get(2).getBoat().getModel(), top3.get(2).getRaceTime() > 0 ? String.format("%.2f sec", top3.get(2).getRaceTime()) : "Did not finish!"
                );
    }

    private String singUpBoat(String[] args) throws NoSetRaceException, DuplicateModelException, ArgumentException, NonExistantModelExeption {
        String model = args[1];

        this.applicationController.signUpBoat(model);

        return String.format("Boat with model %s has signed up for the current Race.",
                model);
    }

    private String openRace(String[] args) throws RaceAlreadyExistsException {
        double distance = Double.parseDouble(args[1]);
        double windSpeed = Double.parseDouble(args[2]);
        double currentSpeed = Double.parseDouble(args[3]);
        boolean allowsMotorboats = args[4].equals(TRUE_STRING);
        this.applicationController.openRace(distance, windSpeed, currentSpeed, allowsMotorboats);

        return String.format("A new race with distance %.0f meters, wind speed %.0f m/s and ocean current speed %.0f m/s has been set.",
                distance, windSpeed, currentSpeed);
    }

    private String createYacht(String[] args) throws ArgumentException, NonExistantModelExeption {
        String model = args[1];
        double weight = Double.parseDouble(args[2]);
        String engineModel = args[3];
        double cargo = Double.parseDouble(args[4]);
        this.applicationController.createYacht(model, weight, engineModel, cargo);

        return String.format("Yacht with model %s registered successfully.", model);
    }

    private String createPowerBoat(String[] args) throws ArgumentException, NonExistantModelExeption {
        String model = args[1];
        double weight = Double.parseDouble(args[2]);
        String firstEngineModel = args[3];
        String secondEngineModel = args[4];
        this.applicationController.createPowerBoat(model, weight, firstEngineModel, secondEngineModel);

        return String.format("Power boat with model %s registered successfully.", model);
    }

    private String createSailBoat(String[] args) throws ArgumentException {
        String model = args[1];
        double weight = Double.parseDouble(args[2]);
        int sailEfficiency = Integer.parseInt(args[3]);
        this.applicationController.createSailBoat(model, weight, sailEfficiency);

        return String.format("Sail boat with model %s registered successfully.", model);
    }

    private String createRowBoat(String[] args) throws ArgumentException {
        String model = args[1];
        double weight = Double.parseDouble(args[2]);
        int oars = Integer.parseInt(args[3]);
        this.applicationController.createRowBoat(model, weight, oars);

        return String.format("Row boat with model %s registered successfully.", model);
    }

    private String createBoatEngine(String[] args) throws DuplicateModelException, ArgumentException, IncorrectEngineTypeMessage {
        String model = args[1];
        int horsepower = Integer.parseInt(args[2]);
        int displacement = Integer.parseInt(args[3]);
        String type = args[4];
        this.applicationController.createEngine(
                model,
                horsepower,
                displacement,
                type
        );

        return String.format("Engine model %s with %s HP and displacement %s cm3 created successfully.",
                model, horsepower, displacement);
    }
}
