package at.htlkaindorf.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.testapp.beans.Cat;
import at.htlkaindorf.testapp.beans.CatColor;
import at.htlkaindorf.testapp.beans.Dog;
import at.htlkaindorf.testapp.beans.MyGender;
import at.htlkaindorf.testapp.beans.Pet;
import at.htlkaindorf.testapp.beans.Size;

public class MainActivity extends AppCompatActivity {
    private Button btn;

    private ArrayList<Pet> list = new ArrayList<Pet>(){{
        add(new Dog("asdf", LocalDate.now(), MyGender.MALE, Size.LARGE));
        add(new Cat("asdf2", LocalDate.now(), MyGender.FEMALE, CatColor.AMBER, Uri.parse("https://robohash.org/etrerumratione.jpg?size=100x100&set=set4")));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(v -> {
            Intent i = new Intent(this.getApplicationContext(), Main2Activity.class);
            i.putExtra("pets", list);
            startActivity(i);
        });
    }
}
