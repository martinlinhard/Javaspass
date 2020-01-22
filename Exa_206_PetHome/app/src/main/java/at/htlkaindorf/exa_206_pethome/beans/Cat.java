package at.htlkaindorf.exa_206_pethome.beans;

import android.net.Uri;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class Cat extends Pet implements Serializable {
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
}
