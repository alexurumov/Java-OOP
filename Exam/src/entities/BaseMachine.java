package entities;

import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.List;

abstract class BaseMachine implements Machine {
    private String name;
    private Pilot pilot;
    // FIXME Try with Double If Problem...
    private double attackPoints;
    private double defensePoints;
    private double healthPoints;
    private List<String> targets;

    protected BaseMachine(String name, double attackPoints, double defensePoints, double healthPoints) {
        this.setName(name);
        this.setAttackPoints(attackPoints);
        this.setDefensePoints(defensePoints);
        this.setHealthPoints(healthPoints);
        this.targets = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Machine name cannot be null or empty.");
        }
        this.name = name;
    }

    protected void setAttackPoints(double attackPoints) {
        this.attackPoints = attackPoints;
    }

    protected void setDefensePoints(double defensePoints) {
        this.defensePoints = defensePoints;
    }

    @Override
    public Pilot getPilot() {
        return this.pilot;
    }

    @Override
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new NullPointerException("Pilot cannot be null.");
        }

        this.pilot = pilot;
    }

    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public double getAttackPoints() {
        return this.attackPoints;
    }

    @Override
    public double getDefensePoints() {
        return this.defensePoints;
    }

    @Override
    public List<String> getTargets() {
        return this.targets;
    }

    @Override
    public void attack(String target) {
        if (target == null || target.trim().isEmpty()) {
            throw new IllegalArgumentException("Attack target cannot be null or empty string.");
        }
        this.getTargets().add(target);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("- ").append(this.getName()).append(System.lineSeparator());

        String type = this.getClass().getSimpleName();
        type = type.substring(0, type.indexOf("I"));


        sb.append(" *Type: ").append(type).append(System.lineSeparator())
                .append(" *Health: ").append(String.format("%.2f", this.getHealthPoints())).append(System.lineSeparator())
                .append(" *Attack: ").append(String.format("%.2f", this.getAttackPoints())).append(System.lineSeparator())
                .append(" *Defense: ").append(String.format("%.2f", this.getDefensePoints())).append(System.lineSeparator())
                .append(" *Targets: ");

        String targets = "";
        if (!this.getTargets().isEmpty()) {
            targets = String.join(", ", this.getTargets());
        } else {
            targets = "None";
        }

        sb.append(targets).append(System.lineSeparator());

        return sb.toString();
    }
}
