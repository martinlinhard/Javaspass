package at.htlkaindorf.pethome2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import at.htlkaindorf.pethome2.beans.Pet;
import at.htlkaindorf.pethome2.bl.PetAdapter;

public class PetListView extends AppCompatActivity {

    private List<Pet> pets;
    private String title;

    private PetAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list_view);

        pets = getIntent().getParcelableArrayListExtra("pets");
        title = getIntent().getStringExtra("title");

        adapter = new PetAdapter(pets);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        recyclerView.setAdapter(adapter);
    }
}
