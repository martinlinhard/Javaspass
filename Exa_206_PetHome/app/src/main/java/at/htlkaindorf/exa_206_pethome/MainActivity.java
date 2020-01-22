package at.htlkaindorf.exa_206_pethome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import at.htlkaindorf.exa_206_pethome.beans.Cat;
import at.htlkaindorf.exa_206_pethome.beans.Dog;
import at.htlkaindorf.exa_206_pethome.beans.Pet;

public class MainActivity extends AppCompatActivity {

    private ImageButton dogBtn;
    private ImageButton catBtn;

    private List<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dogBtn = findViewById(R.id.dogBtn);
        this.catBtn = findViewById(R.id.catBtn);

        try {
            List<Pet> values = IOHandler.loadPets(this.getAssets());
            this.pets = values;
        } catch (IOException e) {
            Toast.makeText(this.getApplicationContext(), "Error reading data", Toast.LENGTH_LONG).show();
            finish();
        }

        dogBtn.setOnClickListener(view -> {
            ArrayList newList = new ArrayList<Pet>() {{
                addAll(pets);
            }};
            newList.removeIf(animal -> !(animal instanceof Dog));

            newList.sort(Comparator.comparing(Dog::getSize).thenComparing(Dog::getDateOfBirth));

            startNewView(newList, "Dog list");
        });

        catBtn.setOnClickListener(view -> {

            ArrayList newList = new ArrayList<Pet>() {{
                addAll(pets);
            }};

            newList.removeIf(animal -> !(animal instanceof Cat));

            newList.sort(Comparator.comparing(Cat::getDateOfBirth));

            startNewView(newList, "Cat list");
        });
    }

    private void startNewView(ArrayList<Pet> pets, String heading) {
        Intent i = new Intent(this.getApplicationContext(), activity_pet_list.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("pets", pets);
        i.putExtra("heading", heading);
        ((Application) this.getApplicationContext()).startActivity(i);
    }
}
