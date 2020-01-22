package at.htlkaindorf.exa_206_pethome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.htlkaindorf.exa_206_pethome.beans.Pet;
import at.htlkaindorf.exa_206_pethome.beans.PetAdapter;

public class activity_pet_list extends AppCompatActivity {

    private List<Pet> pets;
    private String heading = "";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        this.pets = (ArrayList<Pet>) getIntent().getSerializableExtra("pets");
        this.heading = (String) getIntent().getSerializableExtra("heading");
        ((TextView) findViewById(R.id.heading)).setText(this.heading);

        this.recyclerView = findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        this.recyclerView.setAdapter(new PetAdapter(this.pets));
    }
}
