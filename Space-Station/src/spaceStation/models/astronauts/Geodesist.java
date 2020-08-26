package spaceStation.models.astronauts;

import static spaceStation.common.ConstantMessages.REPORT_ASTRONAUT_WORK_DESC;

public class Geodesist extends BaseAstronaut {

    private static final double DEFAULT_OXYGEN = 135;

    public Geodesist(String name, int age) {
        super(name, age, DEFAULT_OXYGEN);
    }

    @Override
    public String workDescription() {
        return REPORT_ASTRONAUT_WORK_DESC + " about gravity and planet's shape.";
    }
}
