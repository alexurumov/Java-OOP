package footballTeamGen;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.contains(" ")) {
            throw new IllegalArgumentException("A name should not be empty. ");
        }
        this.name = name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {
        int index = -1;
        for (int i = 0; i < players.size(); i++) {
            if (playerName.equals(players.get(i).getName())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            players.remove(index);
        } else {
            throw new IllegalArgumentException("Player " + playerName + " is not in " + this.name + " team. ");
        }
    }

    public double getRating() {
        return Math.round(this.players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0));
    }
}
