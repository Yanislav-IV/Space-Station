package spaceStation.models.planets;

import java.util.*;

import static spaceStation.common.ExceptionMessages.PLANET_NAME_NULL_OR_EMPTY;

public class PlanetImpl implements Planet {

    private String name;
    private LinkedList<String> items;

    public PlanetImpl(String name, String[] items) {
        setName(name);
        setItems(items);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(PLANET_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setItems(String[] items) {
        this.items = items.length > 0 ? new LinkedList<>(Arrays.asList(items)) : new LinkedList<>();
    }

    @Override
    public void removeItem(String item) {
        this.items.remove(item);
    }

    @Override
    public Collection<String> getItems() {
        return Collections.unmodifiableCollection(this.items);
    }

    @Override
    public String getName() {
        return this.name;
    }


}
