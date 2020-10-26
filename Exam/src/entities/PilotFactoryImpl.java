package entities;

import core.interfaces.PilotFactory;
import entities.interfaces.Pilot;

public class PilotFactoryImpl implements PilotFactory {

    public PilotFactoryImpl() {
    }

    @Override
    public Pilot createPilot(String name) {
        return new PilotImpl(name);
    }
}
