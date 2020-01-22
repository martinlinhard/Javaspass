package at.htlkaindorf.exa_107_quiz.bl;

import java.util.List;

public class Question {
    private String question;
    private List<String> answers;
    private int correctAnswer;

    public Question(String question, List<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswerString() {
        return this.answers.get(this.correctAnswer);
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
