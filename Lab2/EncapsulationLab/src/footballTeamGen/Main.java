package footballTeamGen;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Map<String, Team> teams = new HashMap<>();

        String line = in.nextLine();

        while (!line.equals("END")) {

            String[] input = line.split(";");
            String command = input[0];
            String teamName = input[1];

            try {
                switch (command) {
                    case "Team":
                        teams.put(input[1], new Team(teamName));
                        break;
                    case "Add":
                        if (teams.containsKey(teamName)) {
                            teams.get(teamName).addPlayer(new Player(input[2],
                                    Integer.parseInt(input[3]),
                                    Integer.parseInt(input[4]),
                                    Integer.parseInt(input[5]),
                                    Integer.parseInt(input[6]),
                                    Integer.parseInt(input[7])));
                        } else {
                            throw new IllegalArgumentException("Team " + teamName + " does not exist.");
                        }
                        break;
                    case "Remove":
                        teams.get(teamName).removePlayer(input[2]);
                        break;
                    case "Rating":
                        if (teams.containsKey(teamName)) {
                            System.out.println(teamName + " - " + (int)teams.get(teamName).getRating());
                        } else {
                            throw new IllegalArgumentException("Team " + teamName + " does not exist.");
                        }
                        break;
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
            line = in.nextLine();
        }
    }
}
