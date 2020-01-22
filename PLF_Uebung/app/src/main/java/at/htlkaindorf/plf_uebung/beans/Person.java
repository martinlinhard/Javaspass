package at.htlkaindorf.plf_uebung.beans;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Objects;

import at.htlkaindorf.plf_uebung.MainActivity;

public class Person{
    private String firstname;
    private String lastname;
    private int age;

    public Person(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d)", this.firstname, this.lastname, this.age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        Log.d(MainActivity.class.getSimpleName(), (age == person.age &&
                firstname.equals(person.firstname) &&
                lastname.equals(person.lastname)) + "");
        return (age == person.age &&
                firstname.equals(person.firstname) &&
                lastname.equals(person.lastname));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, age);
    }

    public Person clone() {
        return new Person(
                this.firstname,
                this.lastname,
                this.age
        );
    }
}
