package BoatRaceSimulator.models.race;

import BoatRaceSimulator.exceptions.ArgumentException;
import BoatRaceSimulator.exceptions.DuplicateModelException;
import BoatRaceSimulator.models.boat.Motorboat;
import BoatRaceSimulator.models.boat.Boat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Race {
    private double distance;
    private Conditions conditions;

    private Map<String, Boat> participants;
    private boolean allowMotorboats;

    public Race(double distance, double windSpeed, double currentSpeed, boolean allowMotorboats) {
        this.distance = distance;
        this.conditions = new Conditions(windSpeed, currentSpeed);
        this.allowMotorboats = allowMotorboats;

        this.participants = new LinkedHashMap<>();
    }

    public void addParticipant(Boat newParticipant) throws DuplicateModelException, ArgumentException {
        if (!this.areMotorboatsAllowed() && newParticipant instanceof Motorboat) {
            throw new ArgumentException("The specified boat does not meet the race constraints.");
            
        }

        if (this.participants.containsKey(newParticipant.getModel())) {
            throw new DuplicateModelException("An entry for the given model already exists.");
        }
        this.participants.put(newParticipant.getModel(), newParticipant);
    }

    public boolean enoughPatricipants() {
        return this.getParticipants().size() >= 3;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) throws ArgumentException {
        if (distance <= 0) {
            throw new ArgumentException("Distance must be a positive integer.");
        }
        this.distance = distance;
    }

    public List<Boat> getParticipants() {
        return new ArrayList<>(this.participants.values());
    }

    public boolean areMotorboatsAllowed() {
        return allowMotorboats;
    }

    public void setAllowMotorboats(boolean allowMotorboats) {
        this.allowMotorboats = allowMotorboats;
    }

    public Conditions getConditions() {
        return this.conditions;
    }
}
