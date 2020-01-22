package at.htlkaindorf.testapp.beans;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class Cat extends Pet implements Parcelable {
    private CatColor color;
    private transient Uri pictureURI;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(pictureURI.toString());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        pictureURI = Uri.parse((String) in.readObject());
    }

    public Cat(String name, LocalDate dateOfBirth, MyGender gender, CatColor color, Uri pictureURI) {
        super(name, dateOfBirth, gender);
        this.color = color;
        this.pictureURI = pictureURI;
    }

    public CatColor getColor() {
        return color;
    }

    public Uri getPictureURI() {
        return pictureURI;
    }

    protected Cat(Parcel in) {
        super(in);
        color = CatColor.valueOf(in.readString().toUpperCase());
        pictureURI = Uri.parse(in.readString());
    }

    public static final Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(color.toString().toUpperCase());
        dest.writeString(pictureURI.toString());
    }

    @Override
    public String toString() {
        return "Cat{" +
                "color=" + color +
                ", pictureURI=" + pictureURI +
                '}' + super.toString();
    }
}
