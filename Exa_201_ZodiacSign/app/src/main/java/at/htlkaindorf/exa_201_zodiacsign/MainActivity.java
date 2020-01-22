package at.htlkaindorf.exa_201_zodiacsign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import at.htlkaindorf.exa_201_zodiacsign.bl.ZodiacSignAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    public static MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = this;

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        this.recyclerView.setAdapter(new ZodiacSignAdapter());
    }
}
