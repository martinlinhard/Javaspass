package at.htlkaindorf.exa_107_quiz.bl;

import android.content.res.Resources;
import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.htlkaindorf.exa_107_quiz.MainActivity;
import at.htlkaindorf.exa_107_quiz.enums.Category;

public class QuestionPool {
    private Map<Category, List<Question>> questionPerCategory;
    private Resources resources;

    public QuestionPool(Resources resources) {
        this.resources = resources;
        this.readQuestions();
    }

    public List<Question> getQuestionsByCategory(Category category) {
        return questionPerCategory.get(category);
    }

    private void readQuestions() {
        questionPerCategory = new HashMap<>();
        for (Category c: Category.values()) {
            questionPerCategory.put(c, IOHandler.parseCSV(c, resources));
        }
        String tag = MainActivity.class.getSimpleName();

    }

}
