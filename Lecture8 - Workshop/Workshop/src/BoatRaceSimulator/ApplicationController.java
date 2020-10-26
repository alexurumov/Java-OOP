package BoatRaceSimulator;

import BoatRaceSimulator.exceptions.*;
import BoatRaceSimulator.models.boat.*;
import BoatRaceSimulator.models.engine.Engine;
import BoatRaceSimulator.models.engine.JetEngine;
import BoatRaceSimulator.models.engine.SterndriveEngine;
import BoatRaceSimulator.models.race.Race;
import BoatRaceSimulator.repository.BoatRepository;
import BoatRaceSimulator.repository.EngineRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationController {

    private static final String JET_ENGINE = "Jet";
    private static final String STERN_ENGINE = "Sterndrive";
    private EngineRepository engineRepository;
    private BoatRepository boatRepository;
    private Race currentRace;

    public ApplicationController() {
        this.engineRepository = new EngineRepository();
        this.boatRepository = new BoatRepository();

        this.currentRace = null;
    }

    public void createEngine(String model, int horsepower, int displacement, String type) throws DuplicateModelException, ArgumentException, IncorrectEngineTypeMessage {

        Engine result;

        if (type.equals(JET_ENGINE)) {
            result = new JetEngine(model, horsepower, displacement);
        } else if (type.equals(STERN_ENGINE)){
            result = new SterndriveEngine(model, horsepower, displacement);
        } else {
            throw new IncorrectEngineTypeMessage("The specified model does not exist.");
        }

        boolean saveResult = this.engineRepository.save(result);

        if (!saveResult) {
            throw new DuplicateModelException("An entry for the given model already exists.");
        }
    }

    public void createRowBoat(String model, double weight, int oars) throws ArgumentException {
        Boat boat = new RowBoat(model, weight, oars);
        this.boatRepository.save(boat);
    }

    public void createSailBoat(String model, double weight, int sailEfficiency) throws ArgumentException {
        Boat boat = new SailBoat(model, weight, sailEfficiency);
        this.boatRepository.save(boat);
    }

    public void createPowerBoat(String model, double weight, String firstEngineModel, String secondEngineModel) throws ArgumentException, NonExistantModelExeption {
        Engine engineOne = this.engineRepository.getByModel(firstEngineModel);
        Engine engineTwo = this.engineRepository.getByModel(secondEngineModel);

        Boat boat = new PowerBoat(model, weight, engineOne, engineTwo);

        this.boatRepository.save(boat);
    }

    public void createYacht(String model, double weight, String engineModel, double cargo) throws ArgumentException, NonExistantModelExeption {
        Engine engine = this.engineRepository.getByModel(engineModel);

        Boat boat = new Yacht(model, weight, engine, cargo);

        this.boatRepository.save(boat);
    }

    public void openRace(double distance, double windSpeed, double currentSpeed, boolean allowsMotorboats) throws RaceAlreadyExistsException {
        if (this.currentRace != null) {
            throw new RaceAlreadyExistsException("The current race has already been set.");
        }

        this.currentRace = new Race(distance, windSpeed, currentSpeed, allowsMotorboats);
    }

    public void signUpBoat(String model) throws NoSetRaceException, DuplicateModelException, ArgumentException, NonExistantModelExeption {
        if (this.currentRace == null) {
            throw new NoSetRaceException("There is currently no race set.");
        }

        Boat participant = this.boatRepository.getByModel(model);
        this.currentRace.addParticipant(participant);
    }

    public List<BoatResult> startRace() throws NoSetRaceException, InsufficientContestantsException {
        if (this.currentRace == null) {
            throw new NoSetRaceException("There is currently no race set.");
        }

        if (!this.currentRace.enoughPatricipants()) {
            throw new InsufficientContestantsException("Not enough contestants for the race.");
        }
        return this.currentRace.getParticipants()
                .stream()
                .sorted((f, s) -> Double.compare(s.getSpeed(this.currentRace.getConditions()), f.getSpeed(this.currentRace.getConditions())))
                .limit(3)
                .map(b ->  {
                    double speed = b.getSpeed(this.currentRace.getConditions());
                    double time = this.currentRace.getDistance() / speed;

                    return new BoatResult(b, time);
                })
                .collect(Collectors.toList());
    }

    public String getStatistic() {

        //TODO

        int powerBoats = (int) this.currentRace.getParticipants().stream().filter(participant -> participant instanceof PowerBoat).count();
        int rowBoats = (int) this.currentRace.getParticipants().stream().filter(participant -> participant instanceof RowBoat).count();
        int sailBoats = (int) this.currentRace.getParticipants().stream().filter(participant -> participant instanceof SailBoat).count();
        int yachts = (int) this.currentRace.getParticipants().stream().filter(participant -> participant instanceof Yacht).count();

        int totalParticipants = this.currentRace.getParticipants().size();

        double powerPercentage = (double) powerBoats / totalParticipants * 100;
        double rowPercentage = (double) rowBoats / totalParticipants * 100;
        double sailPercentage = (double) sailBoats / totalParticipants * 100;
        double yachtPercentage = (double) yachts / totalParticipants * 100;

        StringBuilder sb = new StringBuilder();

        sb.append("PowerBoat -> ").append(String.format("%.2f", powerPercentage)).append("%").append(System.lineSeparator())
                .append("RowBoat -> ").append(String.format("%.2f", rowPercentage)).append("%").append(System.lineSeparator())
                .append("SailBoat -> ").append(String.format("%.2f", sailPercentage)).append("%").append(System.lineSeparator())
                .append("Yacht -> ").append(String.format("%.2f", yachtPercentage)).append("%");

        return sb.toString();
    }
}
