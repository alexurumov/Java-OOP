package greedyTimes;

import java.util.HashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private long currentWeight;
    private long gold;
    private Map<String, Long> gems;
    private long totalGems;
    private Map<String, Long> cash;
    private long totalCash;
    private boolean goldIsAdded;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.currentWeight = 0;
        this.gold = 0;
        this.gems = new HashMap<>();
        this.totalGems = 0;
        this.cash = new HashMap<>();
        this.totalCash = 0;
        this.goldIsAdded = false;
    }


    public void addCash(String item, long weight) {
        if (this.hasFreeCapacity(weight) && this.totalGems >= this.totalCash + weight) {
            if (!this.cash.containsKey(item)) {
                this.cash.put(item, weight);
            } else {
                this.cash.put(item, this.cash.get(item) + weight);
            }
            this.totalCash += weight;
            this.currentWeight += weight;
        }
    }

    private boolean hasFreeCapacity(long weight) {
        return this.currentWeight + weight <= this.capacity;
    }

    public void addGems(String item, long weight) {
        if (this.hasFreeCapacity(weight) && this.gold >= this.totalGems + weight) {
            if (!this.gems.containsKey(item)) {
                this.gems.put(item, weight);
            } else {
                this.gems.put(item, this.gems.get(item) + weight);
            }
            this.totalGems += weight;
            this.currentWeight += weight;
        }
    }

    public void addGold(long weight) {
        if (this.hasFreeCapacity(weight)) {
            this.goldIsAdded = true;
            this.gold += weight;
            this.currentWeight += weight;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.goldIsAdded) {
            sb.append("<Gold> $").append(this.gold).append(System.lineSeparator());
            sb.append("##Gold - ").append(this.gold).append(System.lineSeparator());
        }
        if (this.gems.size() > 0) {
            sb.append("<Gem> $").append(this.totalGems).append(System.lineSeparator());

            this.gems.entrySet().stream().sorted((f, s) -> {
                int result = s.getKey().compareTo(f.getKey());
                if (result == 0) {
                    result = f.getValue().compareTo(s.getValue());
                }
                return result;
            }).forEach(es -> {
                sb.append("##").append(es.getKey()).append(" - ").append(es.getValue()).append(System.lineSeparator());
            });
        }

        if (this.cash.size() > 0) {
            sb.append("<Cash> $").append(this.totalCash).append(System.lineSeparator());

            this.cash.entrySet().stream().sorted((f, s) -> {
                int result = s.getKey().compareTo(f.getKey());
                if (result == 0) {
                    result = f.getValue().compareTo(s.getValue());
                }
                return result;
            }).forEach(es -> {
                sb.append("##").append(es.getKey()).append(" - ").append(es.getValue()).append(System.lineSeparator());
            });
        }

        return sb.toString();
    }
}
