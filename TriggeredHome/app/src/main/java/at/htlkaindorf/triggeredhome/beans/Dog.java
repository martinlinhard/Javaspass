package at.htlkaindorf.triggeredhome.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;

public class Dog extends Pet implements Parcelable{
    private Size size;

    public Dog(String name, LocalDate dateOfBirth, Gender gender, Size size) {
        super(name, dateOfBirth, gender);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel in) {
            return new Dog(in);
        }

        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };

    protected Dog(Parcel in) {
        super(in);
        size = Size.valueOf(in.readString().toUpperCase());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(size.toString().toUpperCase());
    }
}
