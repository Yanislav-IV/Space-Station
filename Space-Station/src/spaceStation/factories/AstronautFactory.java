package spaceStation.factories;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;

public class AstronautFactory {

    public static Astronaut createAstronaut(String type, String name, int age) {
        return switch (type) {
            case "Biologist" -> new Biologist(name, age);
            case "Geodesist" -> new Geodesist(name, age);
            case "Meteorologist" -> new Meteorologist(name, age);
            default -> null;
        };
    }

}
