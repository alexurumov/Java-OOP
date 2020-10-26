package systemSplit;

import systemSplit.core.Dump;
import systemSplit.core.Engine;
import systemSplit.core.System;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        System system = new System();

        Dump dump = new Dump();

        Engine engine = new Engine(system, dump);

        engine.run();

    }
}
