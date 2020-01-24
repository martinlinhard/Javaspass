package at.htlkaindorf.travelplanner;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import at.htlkaindorf.travelplanner.bl.Trip;
import at.htlkaindorf.travelplanner.bl.TripAdapter;
import at.htlkaindorf.travelplanner.io.IO_Helper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TreeMap<String, List<Trip>> trips;
    private TripAdapter ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            trips = IO_Helper.loadTrips(this.getAssets());
            Log.i(MainActivity.class.getSimpleName(), Arrays.toString(trips.get("Poland").toArray()));
            recyclerView = findViewById(R.id.rvTrips);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
            ta = new TripAdapter(trips);
            recyclerView.setAdapter(ta);
        } catch (IOException e) {
            Toast.makeText(this, "Oops, something went wrong!", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
