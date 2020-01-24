package at.htlkaindorf.travelplanner.bl;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.travelplanner.CountryOverviewActivity;

public class TripHolder extends RecyclerView.ViewHolder {
    private TextView tvCountry;
    private TextView numberOfTrips;

    private ArrayList<Trip> currentTrips;
    private View layout;

    public void setCurrentTrips(ArrayList<Trip> currentTrips) {
        this.currentTrips = currentTrips;
    }

    public TripHolder(@NonNull View itemView, TextView tvCountry, TextView numberOfTrips) {
        super(itemView);
        this.layout = itemView;
        this.tvCountry = tvCountry;
        this.numberOfTrips = numberOfTrips;

        itemView.setOnClickListener(v -> {
            Intent newIntent = new Intent(itemView.getContext(), CountryOverviewActivity.class);
            newIntent.putExtra("trips", this.currentTrips);
            itemView.getContext().startActivity(newIntent);
        });
    }

    public TextView getTvCountry() {
        return tvCountry;
    }

    public void setTvCountry(TextView tvCountry) {
        this.tvCountry = tvCountry;
    }

    public TextView getNumberOfTrips() {
        return numberOfTrips;
    }

    public void setNumberOfTrips(TextView numberOfTrips) {
        this.numberOfTrips = numberOfTrips;
    }
}
