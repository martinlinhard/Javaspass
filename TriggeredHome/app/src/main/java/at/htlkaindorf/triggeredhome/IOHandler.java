package at.htlkaindorf.triggeredhome;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.triggeredhome.beans.Pet;

public class IOHandler {
    public static List<Pet> getPets(Context ctx) throws IOException {
        return new BufferedReader(new InputStreamReader(ctx.getAssets().open("pets.csv")))
                .lines()
                .skip(1)
                .map(Pet::parsePet)
                .collect(Collectors.toList());
    }
}
