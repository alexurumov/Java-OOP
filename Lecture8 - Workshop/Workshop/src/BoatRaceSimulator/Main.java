package BoatRaceSimulator;

import BoatRaceSimulator.exceptions.*;


public class Main {

    public static void main(String[] args) throws DuplicateModelException, ArgumentException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException {

        Exetutor exetutor = new Exetutor();
        exetutor.execute();

    }
}
