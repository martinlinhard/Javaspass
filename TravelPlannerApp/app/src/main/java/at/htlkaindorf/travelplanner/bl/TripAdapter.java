package at.htlkaindorf.travelplanner.bl;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import at.htlkaindorf.travelplanner.MainActivity;
import at.htlkaindorf.travelplanner.R;

public class TripAdapter extends RecyclerView.Adapter<TripHolder> {
    private TreeMap<String, List<Trip>> trips;
    private List<String> keys;

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public TripAdapter(TreeMap<String, List<Trip>> trips) {
        this.trips = trips;
        this.keys = new ArrayList<>();
        for (String key : trips.keySet()) {
            keys.add(key);
        }
    }

    @NonNull
    @Override
    public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);

        TextView tvCountry = layout.findViewById(R.id.tvCountry);
        TextView tvNumberOfTrips = layout.findViewById(R.id.tvNumberOfTrips);

        return new TripHolder(layout, tvCountry, tvNumberOfTrips);
    }

    @Override
    public void onBindViewHolder(@NonNull TripHolder holder, int position) {
        String currCountry = keys.get(position);
        String currAbb = trips.get(currCountry).get(0).getCountryCode();
        TripResult res = Trip.compute(this.trips.get(currCountry));

        //set header
        holder.getTvCountry().setText(String.format("%s (%s)", currCountry, currAbb));

        String trip = "trip";
        if (res.getAmountOfTrips() > 1) {
            trip += "s";
        }

        holder.getNumberOfTrips().setText(String.format("%d %s - %d days (%s - %s)", res.getAmountOfTrips(), trip, res.getTotalDays(), dtf.format(res.getStartDate()), dtf.format(res.getEndDate())));
        holder.setCurrentTrips((ArrayList<Trip>) trips.get(currCountry));
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }
}
