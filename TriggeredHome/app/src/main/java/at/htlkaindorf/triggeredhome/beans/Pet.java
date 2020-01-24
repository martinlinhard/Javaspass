package at.htlkaindorf.triggeredhome.beans;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pet implements Parcelable {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");



    public Pet(String name, LocalDate dateOfBirth, Gender gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    protected Pet(Parcel in) {
        name = in.readString();
        gender = Gender.valueOf(in.readString().toUpperCase());
    }

    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static DateTimeFormatter getDtf() {
        return dtf;
    }

    public static void setDtf(DateTimeFormatter dtf) {
        Pet.dtf = dtf;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender.toString().toUpperCase());
    }

    public static Pet parsePet(String line) {
        //common stuff
        String[] tokens = line.split("\\,");
        String name = tokens[1];
        Gender gender = Gender.valueOf(tokens[2].toUpperCase());
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
}
