package spaceStation.core;

import spaceStation.common.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

import static spaceStation.common.ConstantMessages.TYPE_INFO_MESSAGE;

public class Manager {
    private final Controller controller;

    public Manager(Controller controller) {
        this.controller = controller;
    }

    public String processInput(String[] tokens) {

        if (Stream.of(Command.values()).noneMatch(v -> v.name().equals(tokens[0]))) {
            return invalidCommand();
        }

        Command command = Command.valueOf(tokens[0]);
        String result;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        result = switch (command) {
            case AddAstronaut -> addAstronaut(data);
            case AddPlanet -> addPlanet(data);
            case RetireAstronaut -> retireAstronaut(data);
            case ExplorePlanet -> explorePlanet(data);
            case Report -> report();
            case Info -> showInfo();
            case Exit -> Command.Exit.name();
        };

        return result;
    }

    private String invalidCommand() {
        return "Invalid command!\n" + TYPE_INFO_MESSAGE;
    }

    private String showInfo() {
        return this.controller.showInfo();
    }

    private String retireAstronaut(String[] data) {
        return this.controller.retireAstronaut(data[0]);
    }

    private String report() {
        return this.controller.report();
    }

    private String explorePlanet(String[] data) {
        return this.controller.explorePlanet(data[0]);
    }

    private String addPlanet(String[] data) {
        return this.controller.addPlanet(data[0], Arrays.copyOfRange(data, 1, data.length));
    }

    private String addAstronaut(String[] data) {
        return this.controller.addAstronaut(data[0], data[1], Integer.parseInt(data[2]));
    }
}
