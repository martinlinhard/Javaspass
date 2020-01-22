package at.htlkaindorf.exa_201_contactsapp.bl;

import android.net.Uri;

import java.io.Serializable;
import java.net.URL;
import java.util.Objects;

public class Contact implements Serializable {
    private int id;
    private String firstname;
    private String lastname;
    private String language;
    private Gender gender;
    private String picture;
    private String phoneNumber;

    public Contact(String line) {
        //id,first_name,last_name,language,gender,picture,phone_number

        String[] tokens = line.split("\\,");
        this.id = Integer.parseInt(tokens[0]);
        this.firstname = tokens[1];
        this.lastname = tokens[2];
        this.language = tokens[3];
        Gender gender;
        switch (tokens[4]) {
            case "Male":
                gender = Gender.MALE;
                break;
            case "Female":
                gender = Gender.FEMALE;
                break;
            default:
                gender = Gender.OTHER;
        }
        this.gender = gender;
        this.picture = tokens[5];
        this.phoneNumber = tokens[6];
    }

    public Contact(int id, String firstname, String lastname, String language, Gender gender, String picture, String phoneNumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.language = language;
        this.gender = gender;
        this.picture = picture;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                Objects.equals(firstname, contact.firstname) &&
                Objects.equals(lastname, contact.lastname) &&
                Objects.equals(language, contact.language) &&
                gender == contact.gender &&
                Objects.equals(picture, contact.picture) &&
                Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, language, gender, picture, phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", language='" + language + '\'' +
                ", gender=" + gender +
                ", picture='" + picture + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static Contact fromContact(Contact other) {
        return new Contact(other.id, other.firstname, other.lastname, other.language, other.gender, other.picture, other.phoneNumber);
    }
}
