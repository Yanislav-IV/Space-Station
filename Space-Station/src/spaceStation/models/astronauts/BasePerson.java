package spaceStation.models.astronauts;

import static spaceStation.common.ConstantMessages.ASTRONAUT_INVALID_AGE;
import static spaceStation.common.ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY;

public abstract class BasePerson {

    private String name;
    private int age;

    BasePerson(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected int getAge() {
        return this.age;
    }

    protected void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException(ASTRONAUT_INVALID_AGE);
        }
        this.age = age;
    }
}
