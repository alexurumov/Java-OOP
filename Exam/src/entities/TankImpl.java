package entities;

import entities.interfaces.Tank;

public class TankImpl extends BaseMachine implements Tank {
    private boolean defenseMode;
    //TODO IMPLEMENT MODIFIERS IN BASE CLASS
    private double attackPointsModifier;
    private double deffencePointsModifier;

    public TankImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, 100);
        this.defenseMode = false;
        this.attackPointsModifier = 40.0;
        this.deffencePointsModifier = 30.0;
        this.toggleDefenseMode();
    }

    @Override
    public boolean getDefenseMode() {
        return this.defenseMode;
    }

    @Override
    public void toggleDefenseMode() {
        if (!this.getDefenseMode()) {
            super.setAttackPoints(super.getAttackPoints() - this.attackPointsModifier);
            super.setDefensePoints(super.getDefensePoints() + this.deffencePointsModifier);
            this.defenseMode = true;
        } else {
            super.setAttackPoints(super.getAttackPoints() + this.attackPointsModifier);
            super.setDefensePoints(super.getDefensePoints() - this.deffencePointsModifier);
            this.defenseMode = false;
        }
    }

    @Override
    public String toString() {
        String onoff = "";
        if (this.getDefenseMode()) {
            onoff = "(ON)";
        } else {
            onoff = "(OFF)";
        }
        return super.toString() + " *Defense Mode" + onoff;
    }
}
