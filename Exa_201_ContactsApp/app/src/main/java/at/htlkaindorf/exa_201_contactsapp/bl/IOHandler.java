package at.htlkaindorf.exa_201_contactsapp.bl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.exa_201_contactsapp.MainActivity;

public class IOHandler {
    public static List<Contact> getContacts() {
        try {
            return new BufferedReader(new InputStreamReader(MainActivity.main.getAssets().open("contact_data.csv")))
                    .lines()
                    .skip(1)
                    .map(Contact::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
