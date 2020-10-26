package BoatRaceSimulator;

import BoatRaceSimulator.exceptions.DuplicateModelException;
import BoatRaceSimulator.exceptions.InsufficientContestantsException;
import BoatRaceSimulator.exceptions.NoSetRaceException;
import BoatRaceSimulator.exceptions.RaceAlreadyExistsException;

import java.util.Scanner;

public class Exetutor {
    private static final String END_COMMAND = "End";
    private final Scanner scanner;
    private final CommandHandler commandHandler;

    public Exetutor() {
        this.scanner = new Scanner(System.in);
        this.commandHandler = new CommandHandler();
    }

    public void execute() throws DuplicateModelException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException {
        String line = this.scanner.nextLine();

        while (!line.equals(END_COMMAND)) {

            this.commandHandler.handle(line.split("\\\\"));

            line = this.scanner.nextLine();
        }
    }
}
