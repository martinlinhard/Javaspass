package at.htlkaindorf.travelplanner.io;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import at.htlkaindorf.travelplanner.MainActivity;
import at.htlkaindorf.travelplanner.bl.Trip;

public class IO_Helper {

    public static TreeMap<String, List<Trip>> loadTrips(AssetManager asm) throws IOException {
        List<Trip> lines = new BufferedReader(new InputStreamReader(asm.open("travel_data.csv")))
                .lines()
                .skip(4)
                .map(Trip::fromLine)
                .collect(Collectors.toList());
        TreeMap<String, List<Trip>> trips = getTrips(lines);
        return trips;
    }

    private static TreeMap<String, List<Trip>> getTrips(List<Trip> trips) {
        TreeMap<String, List<Trip>> parsedTrips = new TreeMap<>();
        for (Trip t : trips) {
            if (parsedTrips.containsKey(t.getCountry())) {
                //country already present --> add trip
                List<Trip> alreadyPresent = parsedTrips.get(t.getCountry());
                alreadyPresent.add(t);
                parsedTrips.put(t.getCountry(), alreadyPresent);
            } else {
                //country not present
                parsedTrips.put(t.getCountry(), new ArrayList<Trip>() {{
                    add(t);
                }});
            }
        }
        return parsedTrips;
    }

}
