package at.htlkaindorf.io_example;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOHandler {
    public static String readFile(Context ctx) throws IOException {
        String result ="";
        //gibt an, welches file ausgelesen werden soll
        InputStream inputStream = ctx.getResources().openRawResource(R.raw.hello_world);

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        //die jeweilige zeile
        String line;

        //file zeile für zeile auslesen
        while((line = br.readLine()) != null) {
            result += (line + "\n");
        }

        //WICHTIG: wie letztes jahr solltest du den BufferedReader wieder schließen :D
        br.close();

        return result;
    }
}
