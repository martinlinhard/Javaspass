package at.htlkaindorf.plf_uebung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import at.htlkaindorf.plf_uebung.beans.Person;

public class MainActivity extends AppCompatActivity {

    private EditText firstname;
    private EditText lastname;
    private EditText age;

    private TextView output;
    private Button addBtn;

    private RadioButton rbFirstname;
    private RadioButton rbLastname;
    private RadioButton rbAge;

    private TreeSet<Person> personsByFirstname;
    private TreeSet<Person> personsByLastname;
    private TreeSet<Person> personsByAge;

    private String lastSort = "Vorname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeSets();
        this.initialize();
        this.setListenerAdd();
        this.setListenerRB();
    }

    private void initializeSets() {
        this.personsByFirstname = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFirstname().compareToIgnoreCase(o2.getFirstname());
            }
        });
        this.personsByLastname = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastname().compareToIgnoreCase(o2.getLastname());
            }
        });
        this.personsByAge = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
    }

    private void initialize() {
        this.firstname = this.findViewById(R.id.tvFirstname);
        this.lastname = this.findViewById(R.id.tvLastname);
        this.age = this.findViewById(R.id.tvAge);

        this.output = findViewById(R.id.outputET);
        this.addBtn = findViewById(R.id.btnAdd);

        this.rbFirstname = findViewById(R.id.rbFirstname);
        this.rbLastname = findViewById(R.id.rbLastname);
        this.rbAge = findViewById(R.id.rbAge);

        try {
            List<Person> list = IOHandler.readPersons(getApplicationContext());
            for (Person p: list) {
                addPersonToSets(p);
            }
        } catch (IOException e) {
            Log.d(MainActivity.class.getSimpleName(), "Error reading persons");
        }
        this.sort();
    }

    private void setListenerAdd() {
        this.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdd();
            }
        });
    }

    private void setListenerRB() {
        RBListener listener = new RBListener();
        rbFirstname.setOnClickListener(listener);
        rbLastname.setOnClickListener(listener);
        rbAge.setOnClickListener(listener);
    }

    private void addPersonToSets(Person p) {
        this.personsByFirstname.add(p);
        this.personsByLastname.add(p);
        this.personsByAge.add(p);

        Log.d(MainActivity.class.getSimpleName(), Arrays.toString(personsByFirstname.toArray()));
        Log.d(MainActivity.class.getSimpleName(), Arrays.toString(personsByLastname.toArray()));
        Log.d(MainActivity.class.getSimpleName(), Arrays.toString(personsByAge.toArray()));
        Log.d(MainActivity.class.getSimpleName(), "*\n\n");
    }

    private void onAdd() {
        String firstname = this.firstname.getText().toString();
        String lastname= this.lastname.getText().toString();

        int age = Integer.parseInt(this.age.getText().toString());
        this.addPersonToSets(new Person(firstname, lastname, age));
        this.sort();
    }

    private void sort() {
        switch (this.lastSort) {
            case "Vorname":
                this.writeSetToScreen(personsByFirstname);
                break;
            case "Nachname":
                this.writeSetToScreen(personsByLastname);
                break;
            case "Alter":
                this.writeSetToScreen(personsByAge);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.lastSort);
        }
    }

    private void writeSetToScreen(Set<Person> set) {
        String s = "";
        for(Person p : set) {
            s += (p.toString() + "\n");
        }
        s = s.trim();
        output.setText(s);
    }

    public class RBListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String buttonString = ((RadioButton) v).getText().toString();
            switch (buttonString) {
                case "Vorname":
                    lastSort = "Vorname";
                    break;
                case "Nachname":
                    lastSort = "Nachname";
                    break;
                case "Alter":
                    lastSort = "Alter";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + buttonString);
            }
            sort();
        }
    }
}
