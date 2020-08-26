package spaceStation;

import spaceStation.common.Command;
import spaceStation.core.ControllerImpl;
import spaceStation.core.Manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static spaceStation.common.ConstantMessages.TYPE_INFO_MESSAGE;

public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager(new ControllerImpl());
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to Space Station App!");
        System.out.println(TYPE_INFO_MESSAGE);

        while (true) {
            String result;

            try {
                String[] tokens = rd.readLine().split("\\s+");
                result = manager.processInput(tokens);

                if (result.equals(Command.Exit.name())) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IOException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }
}
