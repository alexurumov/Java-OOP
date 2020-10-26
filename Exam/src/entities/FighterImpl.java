package entities;

import entities.interfaces.Fighter;

public class FighterImpl extends BaseMachine implements Fighter {
    private boolean aggressiveMode;
    private double attackPointsModifier;
    private double defencePointsModifier;

    public FighterImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, 200);
        this.aggressiveMode = false;
        this.attackPointsModifier = 50.0;
        this.defencePointsModifier = 25.0;
        toggleAggressiveMode();
    }

    @Override
    public boolean getAggressiveMode() {
        return this.aggressiveMode;
    }

    @Override
    public void toggleAggressiveMode() {
        if (!this.getAggressiveMode()) {
            super.setAttackPoints(super.getAttackPoints() + this.attackPointsModifier);
            super.setDefensePoints(super.getDefensePoints() - this.defencePointsModifier);
            this.aggressiveMode = true;
        } else {
            super.setAttackPoints(super.getAttackPoints() - this.attackPointsModifier);
            super.setDefensePoints(super.getDefensePoints() + this.defencePointsModifier);
            this.aggressiveMode = false;
        }
    }

    @Override
    public String toString() {
        String onoff = "";
        if (this.getAggressiveMode()) {
            onoff = "(ON)";
        } else {
            onoff = "(OFF)";
        }
        return super.toString() + " *Aggressive Mode" + onoff;
    }
}
