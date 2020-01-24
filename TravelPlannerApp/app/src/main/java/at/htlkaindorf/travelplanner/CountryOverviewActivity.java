package at.htlkaindorf.travelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.travelplanner.bl.Trip;

public class CountryOverviewActivity extends AppCompatActivity {

    private TextView tvCountry;
    private TextView tvOverview;
    private SearchView svCity;

    private List<Trip> allTrips;
    private List<Trip> filteredTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_overview);

        allTrips = (List<Trip>) getIntent().getSerializableExtra("trips");

        this.allTrips.sort(Comparator.comparing(Trip::getStartDate));

        filteredTrips = new ArrayList<>();

        tvCountry = findViewById(R.id.tvCountry);
        tvOverview = findViewById(R.id.tvOverview);
        svCity = findViewById(R.id.svCity);

        tvCountry.setText(allTrips.get(0).getCountry());
        this.updateFiltered("");

        svCity.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                updateFiltered(newText);
                return false;
            }
        });
    }

    private void updateFiltered(String query) {
        filteredTrips.clear();
        filteredTrips.addAll(allTrips);

        this.filteredTrips = this.filteredTrips.stream()
                .filter(item -> item.getCity().toLowerCase().contains(query))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (Trip t : filteredTrips) {
            sb.append(t.toString() + "\n");
        }

        tvOverview.setText(sb.toString());
    }
}
