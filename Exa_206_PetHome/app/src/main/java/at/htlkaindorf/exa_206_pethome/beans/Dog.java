package at.htlkaindorf.exa_206_pethome.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Dog extends Pet implements Serializable {
    private Size size;

    public Dog(String name, LocalDate dateOfBirth, MyGender gender, Size size) {
        super(name, dateOfBirth, gender);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }
}
