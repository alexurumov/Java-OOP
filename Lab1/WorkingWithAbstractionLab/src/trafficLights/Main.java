package trafficLights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] initialStates = reader.readLine().split(" ");
        int updatesCount = Integer.parseInt(reader.readLine());

        List<TrafficLight> trafficLights = new ArrayList<>();

        for (String initialState : initialStates) {
            TrafficLight trafficLight = new TrafficLight(LightStates.valueOf(initialState));
            trafficLights.add(trafficLight);
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < updatesCount; i++) {
            for (TrafficLight trafficLight : trafficLights) {
                trafficLight.update();
                stringBuilder.append(trafficLight.toString()).append(" ");
            }
            stringBuilder.append(System.lineSeparator());
        }

        System.out.println(stringBuilder.toString());
    }
}
