package hell;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ManagerImpl manager = new ManagerImpl();

        String line = in.nextLine();

        while (!line.equals("Quit")) {

            String[] inputArgs = line.split(" ");

            String command = inputArgs[0];

            String result = "";

            switch (command) {
                case "Hero":
                    result = manager.addHero(Arrays.asList(inputArgs));
                    break;
                case "Item":
                    result = manager.addItem(Arrays.asList(inputArgs));
                    break;
                case "Recipe":
                    result = manager.addRecipe(Arrays.asList(inputArgs));
                    break;
                case "Inspect":
                    result = manager.inspect(Arrays.asList(inputArgs));
                    break;
            }

            System.out.println(result);

            line = in.nextLine();
        }

        System.out.println(manager.quit());
    }
}