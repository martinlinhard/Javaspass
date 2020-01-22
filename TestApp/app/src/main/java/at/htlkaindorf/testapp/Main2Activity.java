package at.htlkaindorf.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import at.htlkaindorf.testapp.beans.Pet;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ArrayList<Pet> pets = (ArrayList<Pet>) getIntent().getSerializableExtra("pets");
        Log.i(MainActivity.class.getSimpleName(), (pets == null) + "");
        Pet pet1 = pets.get(0);
        Pet pet2 = pets.get(1);

        Log.i(MainActivity.class.getSimpleName(), pet1.toString());
        Log.i(MainActivity.class.getSimpleName(), pet2.toString());
    }
}
