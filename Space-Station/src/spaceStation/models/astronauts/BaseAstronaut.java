package spaceStation.models.astronauts;

import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public abstract class BaseAstronaut extends BasePerson implements Astronaut {

    private static final int DECREASED_OXYGEN_VALUE = 30;

    private double oxygen;
    private Bag bag;

    BaseAstronaut(String name, int age, double oxygen) {
        super(name, age);
        setOxygen(oxygen);
        this.bag = new Backpack();
    }

    public abstract String workDescription();

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    void setOxygen(double oxygen) {
        this.oxygen = oxygen;
    }


    @Override
    public Bag getBag() {
        return this.bag;
    }

    @Override
    public boolean canBreath() {
        return this.oxygen >= DECREASED_OXYGEN_VALUE;
    }

    @Override
    public void breath() {

        if (this.oxygen - DECREASED_OXYGEN_VALUE < 0) {
            this.setOxygen(0);
        } else {
            this.setOxygen(this.oxygen - DECREASED_OXYGEN_VALUE);
        }

    }


    @Override
    public String toString() {
        return String.format(REPORT_ASTRONAUT_NAME, this.getName())
                + System.lineSeparator()
                + String.format(REPORT_ASTRONAUT_AGE, this.getAge())
                + System.lineSeparator()
                + this.workDescription()
                + System.lineSeparator()
                + String.format(REPORT_ASTRONAUT_OXYGEN, this.getOxygen())
                + System.lineSeparator()
                + this.bag.toString();
    }
}
