package spaceStation.core;

import spaceStation.factories.AstronautFactory;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;


public class ControllerImpl implements Controller {
    private final Repository<Astronaut> astronautRepository;
    private final Repository<Planet> planetRepository;
    private final Mission mission;
    private int exploredPlanets = 0;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.mission = new MissionImpl();
    }


    @Override
    public String addAstronaut(String type, String astronautName, int age) {
        Astronaut astronaut = AstronautFactory.createAstronaut(type, astronautName, age);

        if (astronaut == null) {
            throw new NullPointerException(ASTRONAUT_INVALID_TYPE);
        }

        if (astronautRepository.findByName(astronautName) != null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_ALREADY_EXISTS, astronautName));
        }

        this.astronautRepository.add(astronaut);

        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName, items);

        if (planetRepository.findByName(planetName) != null) {
            throw new IllegalArgumentException(String.format(PLANET_ALREADY_EXISTS, planetName));
        }

        this.planetRepository.add(planet);

        return String.format(PLANET_ADDED, planetName, items.length);
    }

    @Override
    public String retireAstronaut(String astronautName) {

        Astronaut astronaut = astronautRepository.findByName(astronautName);

        if (astronaut == null) {
            throw new NullPointerException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        this.astronautRepository.remove(astronaut);

        return String.format(ASTRONAUT_RETIRED, astronaut.getType(), astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {

        Collection<Astronaut> astronauts = this.astronautRepository
                .getModels()
                .stream()
                .filter(a -> a.getOxygen() >= 35)
                .collect(Collectors.toCollection(ArrayList::new));

        if (astronauts.size() == 0) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Planet planet = this.planetRepository.findByName(planetName);

        if (planet == null) {
            throw new NullPointerException(String.format(PLANET_DOES_NOT_EXIST, planetName));
        }

        int itemsCountStart = planet.getItems().size();
        this.mission.explore(planet, astronauts);
        this.exploredPlanets++;

        int itemsCountFinal = itemsCountStart - planet.getItems().size();
        String result = "";

        if (planet.getItems().isEmpty()) {
            planetRepository.remove(planet);
            result += String.format(PLANET_REMOVED, planetName);
            result += System.lineSeparator();
        }

        result += String.format(PLANET_EXPLORED, planetName, itemsCountFinal);
        result += System.lineSeparator();

        return result;
    }

    @Override
    public String showInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append("Available commands:\n");
        sb.append("AddAstronaut {astronautType} {astronautName} {astronautAge}\n");
        sb.append("AddPlanet {planetName} {item1} {item2}… {itemN}\n");
        sb.append("RetireAstronaut {astronautName}\n");
        sb.append("ExplorePlanet {planetName}\n");
        sb.append("Report\n");
        sb.append("Exit\n\n");
        sb.append("Available Astronaut Types: Biologist, Geodesist, Meteorologist\n");

        return sb.toString().trim();
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(REPORT_PLANET_EXPLORED, this.exploredPlanets));
        sb.append(System.lineSeparator());

        if (this.astronautRepository.getModels().size() > 0) {
            sb.append(REPORT_ASTRONAUT_INFO);
            this.astronautRepository.getModels().forEach(e -> sb.append(System.lineSeparator()).append(e.toString()));
        } else {
            sb.append(REPORT_NO_ASTRONAUTS);
        }

        return sb.toString();
    }
}