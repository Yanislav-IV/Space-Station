package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static spaceStation.common.ConstantMessages.ASTRONAUT_COLLECTED_ITEM;
import static spaceStation.common.ConstantMessages.ASTRONAUT_NO_OXYGEN;

public class MissionImpl implements Mission {
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        for (Astronaut astronaut : astronauts) {

            if (astronaut.canBreath()) {

                List<String> collect = new ArrayList<>(planet.getItems());

                for (String item : collect) {

                    astronaut.getBag().addItem(item);
                    System.out.println(String.format(ASTRONAUT_COLLECTED_ITEM, astronaut.getType(), astronaut.getName(), item));
                    planet.removeItem(item);
                    astronaut.breath();

                    if (!astronaut.canBreath()) {
                        System.out.println(ASTRONAUT_NO_OXYGEN);
                        break;
                    }
                }


            } else {
                System.out.println(ASTRONAUT_NO_OXYGEN);
            }
        }
    }
}
