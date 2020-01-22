package at.htlkaindorf.exa_206_pethome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import android.content.res.AssetManager;

import at.htlkaindorf.exa_206_pethome.beans.Pet;


public class IOHandler {
    public static String filename = "pets.csv";

    public static List<Pet> loadPets(AssetManager asm) throws IOException {
        return new BufferedReader(new InputStreamReader(asm.open(filename))).lines().skip(1).map(Pet::readPet).collect(Collectors.toList());
    }
}
