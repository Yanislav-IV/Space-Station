package spaceStation.models.bags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static spaceStation.common.ConstantMessages.*;

public class Backpack implements Bag {

    private List<String> items;

    public Backpack() {
        this.items = new ArrayList<>();
    }

    @Override
    public Collection<String> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    @Override
    public void addItem(String item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        return String.format(REPORT_ASTRONAUT_BAG_ITEMS, this.items.size() == 0 ? "none" :
                String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, this.items));
    }
}
