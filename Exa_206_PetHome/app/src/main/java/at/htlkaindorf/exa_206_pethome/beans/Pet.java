package at.htlkaindorf.exa_206_pethome.beans;

import android.net.Uri;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pet implements Serializable {
    private String name;
    private LocalDate dateOfBirth;
    private MyGender Gender;

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static Pet readPet(String line) {
        //common stuff
        String[] tokens = line.split("\\,");
        String name = tokens[1];
        MyGender gender = MyGender.valueOf(tokens[2].toUpperCase());
        LocalDate birth = LocalDate.parse(tokens[3], dtf);

        //pet-specific stuff
        if (tokens[0].equals("dog")) {
            Size size = Size.parse(tokens[4]);
            return new Dog(name, birth, gender, size);
        } else {
            CatColor c = CatColor.valueOf(tokens[5].toUpperCase());
            Uri u = Uri.parse(tokens[6]);
            return new Cat(name, birth, gender, c, u);
        }
    }

    public Pet(String name, LocalDate dateOfBirth, MyGender gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        Gender = gender;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public MyGender getGender() {
        return Gender;
    }

    public static DateTimeFormatter getDtf() {
        return dtf;
    }
}
