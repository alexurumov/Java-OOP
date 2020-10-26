package BoatRaceSimulator.repository;

import BoatRaceSimulator.exceptions.NonExistantModelExeption;
import BoatRaceSimulator.models.Model;
import BoatRaceSimulator.models.engine.Engine;

import java.util.HashMap;
import java.util.Map;

public class BaseRepository<T extends Model> {
    private Map<String, T> dataStore;

    public BaseRepository() {
        this.dataStore = new HashMap<>();
    }

    public boolean save(T entry) {
        if (this.dataStore.containsKey(entry.getModel())) return false;

        this.dataStore.put(entry.getModel(), entry);

        return true;
    }

    public T getByModel(String model) throws NonExistantModelExeption {
        if (!this.dataStore.containsKey(model)) {
            throw new NonExistantModelExeption("An entry for the given model already exists.");
        }
        return this.dataStore.get(model);
    }
}
