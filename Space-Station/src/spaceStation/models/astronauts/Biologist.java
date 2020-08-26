package spaceStation.models.astronauts;

import static spaceStation.common.ConstantMessages.REPORT_ASTRONAUT_WORK_DESC;

public class Biologist extends BaseAstronaut {

    private static final double DEFAULT_OXYGEN = 120;
    private static final double DECREASED_OXYGEN_VALUE = 35;

    public Biologist(String name, int age) {
        super(name, age, DEFAULT_OXYGEN);
    }


    @Override
    public String workDescription() {
        return REPORT_ASTRONAUT_WORK_DESC + " about new organisms.";
    }

    @Override
    public void breath() {
        if (this.getOxygen() - DECREASED_OXYGEN_VALUE >=0) {
            this.setOxygen(this.getOxygen() - DECREASED_OXYGEN_VALUE);
        } else {
            this.setOxygen(0);
        }
    }
}
