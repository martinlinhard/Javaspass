package at.htlkaindorf.plf_uebung;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.plf_uebung.beans.Person;

public class IOHandler {
    public static List<Person> readPersons(Context ctx) throws IOException {
        List<Person> result = new ArrayList<>();
        //Martin Linhard;16
        InputStream is = ctx.getResources().openRawResource(R.raw.persons);
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = bf.readLine()) != null) {
            String[] tokens = line.split("\\;");
            result.add(new Person(
                    tokens[0].split(" ")[0],
                    tokens[0].split(" ")[1],
                    Integer.parseInt(tokens[1])
            ));
        }
        return result;
    }
}
