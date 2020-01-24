package at.htlkaindorf.triggeredhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.triggeredhome.beans.Cat;
import at.htlkaindorf.triggeredhome.beans.Dog;
import at.htlkaindorf.triggeredhome.beans.Pet;

public class MainActivity extends AppCompatActivity {

    private List<Pet> allPets = null;
    private ArrayList<Pet> cats = new ArrayList<>();
    private ArrayList<Pet> dogs = new ArrayList<>();

    private ImageButton btnCats;
    private ImageButton btnDogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCats = findViewById(R.id.catBtn);
        btnDogs = findViewById(R.id.dogBtn);

        try {
            allPets = IOHandler.getPets(this);

            cats.addAll(allPets);
            dogs.addAll(allPets);

            cats.removeIf(pet -> pet instanceof Dog);
            dogs.removeIf(pet -> pet instanceof Cat);

            btnCats.setOnClickListener(v -> loadList(cats, "Cat list"));
            btnDogs.setOnClickListener(v -> loadList(dogs, "Dog list"));

        } catch (IOException e) {
            Toast.makeText(this, "An error occured", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void loadList(ArrayList<Pet> pets, String title) {
        Intent newAct = new Intent(this.getApplicationContext(), PetListView.class);
        newAct.putParcelableArrayListExtra("pets", pets);
        newAct.putExtra("title", title);
        this.startActivity(newAct);
    }
}
