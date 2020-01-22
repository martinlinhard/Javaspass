package at.htlkaindorf.exa_107_quiz.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Category {
    SPORT,
    PC_COMPONENTS;
    //LITERATURE,
    //HISTORY;

    private static final List<Category> VALUES = Collections.unmodifiableList(Arrays.asList(Category.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Category getRandomVariant() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}