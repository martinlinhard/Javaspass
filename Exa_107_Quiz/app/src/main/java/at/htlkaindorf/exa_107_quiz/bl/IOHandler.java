package at.htlkaindorf.exa_107_quiz.bl;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.htlkaindorf.exa_107_quiz.R;
import at.htlkaindorf.exa_107_quiz.enums.Category;

public class IOHandler {
    public static List<Question> parseCSV(Category cat, Resources resources) {
        InputStream stream = null;
        if (cat == Category.SPORT) {
            stream = resources.openRawResource(R.raw.sport);
        } else if (cat == Category.PC_COMPONENTS) {
            stream = resources.openRawResource(R.raw.pc_components);
        }

        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(streamReader);

        try {
            String line = br.readLine();

            List<Question> questions = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] split = line.split(";");

                String[] answers = split[1].split(",");
                List<String> ansList = new ArrayList<>(Arrays.asList(answers));
                int correctAns = Integer.parseInt(split[2]);

                questions.add(new Question(split[0], ansList, correctAns));
            }
            return questions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
