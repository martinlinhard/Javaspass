package at.htlkaindorf.exa_107_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.List;

import at.htlkaindorf.exa_107_quiz.bl.Question;
import at.htlkaindorf.exa_107_quiz.bl.QuestionPool;
import at.htlkaindorf.exa_107_quiz.enums.Category;

public class MainActivity extends AppCompatActivity {

    private Button btnNext;
    private Button startBtn;
    private ViewFlipper viewFlipper;
    private TextView tvCategory;
    private TextView tvQuestion;

    private Button btnOk;
    private Button btnCancel;

    private Button leftTop;
    private Button rightTop;
    private Button leftBottom;
    private Button rightBottom;

    private Button firstAnswer;
    private Button secondAnswer;
    private Button thirdAnswer;
    private Button fourthAnswer;
    private Button fifthAnswer;

    private TextView hint;

    private Button[] answerButtons;
    private Button[] resultButtons;

    private List<Question> questions;

    private int questionsIndex = 0;

    private Question currentQuestion;

    private int correctAnswers = 0;

    private TextView startScreenHeading;
    private TextView correctAnswersView;
    private TextView totalQuestions;
    private TextView categoryPlayed;

    private Category currentCategory;

    private TableLayout questionsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize all variables
        findViews();

        totalQuestions.setVisibility(View.INVISIBLE);
        correctAnswersView.setVisibility(View.INVISIBLE);
        categoryPlayed.setVisibility(View.INVISIBLE);
        btnNext.setEnabled(false);


        //event handler that fires if answer is selected
        AnswerClickListener acl = new AnswerClickListener();
        for (Button b : answerButtons) {
            b.setOnClickListener(acl);
        }

        //method responsible for loading the initial questions and answers
        this.initialize();

        //handle next click
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionsIndex == (questions.size() - 1)) {
                    setResultScreenValues();
                    viewFlipper.showNext();
                    resetAllButtons();
                    initialize();
                }
                else {
                    //you can be sure it's not the last question as its not possible to click this button anymore once the last answer has been selected
                    resetButtons();
                    questionsIndex++;
                    setValues();
                    btnNext.setEnabled(false);
                    enableAllButtons();
                }
            }
        });

        btnNext.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopupWindow(questionsLayout);
                return false;
            }
        });

        startBtn = findViewById(R.id.startButton);
        viewFlipper = findViewById(R.id.viewFlipper);

        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });

    }
    private void showPopupWindow(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.custom_popup, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        //get buttons from different layout
        btnOk = popupView.findViewById(R.id.btnOk);
        btnCancel = popupView.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                resetGame();
            }
        });
    }

    private void initialize() {
        this.questionsIndex = 0;
        this.correctAnswers = 0;
        this.btnNext.setText("NEXT");
        enableAllButtons();
        this.currentCategory = Category.getRandomVariant();
        QuestionPool questionPool = new QuestionPool(getResources());
        this.questions = questionPool.getQuestionsByCategory(this.currentCategory);
        tvCategory.setText("Kategorie: " + this.initCap(this.currentCategory));
        this.setValues();
        this.btnNext.setEnabled(true);
    }

    private void setResultScreenValues() {
        startBtn.setText("Start new game!");
        startScreenHeading.setText("The results of your last game:");
        totalQuestions.setText("Total questions: " + (this.questionsIndex + 1));
        correctAnswersView.setText("Correct Answers: " + this.correctAnswers);
        categoryPlayed.setText("Chosen Category: " + initCap(this.currentCategory));

        totalQuestions.setVisibility(View.VISIBLE);
        correctAnswersView.setVisibility(View.VISIBLE);
        categoryPlayed.setVisibility(View.VISIBLE);
        hint.setVisibility(View.INVISIBLE);
    }

    private void resetGame() {
        totalQuestions.setVisibility(View.INVISIBLE);
        correctAnswersView.setVisibility(View.INVISIBLE);
        categoryPlayed.setVisibility(View.INVISIBLE);
        hint.setVisibility(View.VISIBLE);
        startScreenHeading.setText("Welcome To The Quiz!");
        startBtn.setText("GET STARTED");
        startBtn.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        initialize();
        resetAllButtons();
        btnNext.setEnabled(false);
        viewFlipper.showNext();
    }

    private void findViews() {
        startScreenHeading = findViewById(R.id.headingStartScreen);
        correctAnswersView = findViewById(R.id.correctAnswers);
        totalQuestions = findViewById(R.id.totalQuestions);
        categoryPlayed = findViewById(R.id.lastCategory);

        hint = findViewById(R.id.hint);

        btnNext = findViewById(R.id.btnNext);

        tvCategory = findViewById(R.id.tvCategory);
        tvQuestion = findViewById(R.id.tvQuestion);

        leftTop = findViewById(R.id.btnLeftTop);
        rightTop = findViewById(R.id.btnRightTop);
        leftBottom = findViewById(R.id.btnLeftBottom);
        rightBottom = findViewById(R.id.btnRightBottom);

        firstAnswer = findViewById(R.id.firstAnswer);
        secondAnswer = findViewById(R.id.secondAnswer);
        thirdAnswer = findViewById(R.id.thirdAnswer);
        fourthAnswer = findViewById(R.id.fourthAnswer);
        fifthAnswer = findViewById(R.id.fifthAnswer);

        answerButtons = new Button[]{leftTop, rightTop, leftBottom, rightBottom};
        resultButtons = new Button[]{firstAnswer, secondAnswer, thirdAnswer, fourthAnswer, fifthAnswer};
        questionsLayout = findViewById(R.id.questionLayout);
    }

    private String initCap(Category category) {
        return category.toString().length() > 1 ? (category.toString().charAt(0) + "".toUpperCase() + category.toString().substring(1).toLowerCase()) : category.toString();
    }

    private void setValues() {
        currentQuestion = this.questions.get(this.questionsIndex);
        tvQuestion.setText(currentQuestion.getQuestion());
        for (int i = 0; i < this.answerButtons.length; i++) {
            answerButtons[i].setText(currentQuestion.getAnswers().get(i));
        }
    }

    private void resetButtons() {
        for (Button b : answerButtons) {
            b.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
        }
    }

    private void resetAllButtons() {
        resetButtons();
        for (Button b : resultButtons) {
            b.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        }
    }

    private void disableAllButtons() {
        for (Button b : answerButtons) {
            b.setEnabled(false);
        }
    }

    private void enableAllButtons() {
        for (Button b : answerButtons) {
            b.setEnabled(true);
        }
    }

    public class AnswerClickListener implements
            View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(questionsIndex == (questions.size() - 1)) {
                colorButtons((Button) v);
                disableAllButtons();
                Toast.makeText(getApplicationContext(), "The game has ended.", Toast.LENGTH_LONG).show();
                btnNext.setText("Back to start");
            }
            else {
                disableAllButtons();
                colorButtons((Button) v);
            }
            btnNext.setEnabled(true);
        }

        private void colorButtons(Button v) {
            String currentAnswerString = v.getText().toString();
            //color buttons
            v.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            Button correctButton = answerButtons[currentQuestion.getCorrectAnswer()];
            correctButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

            if (currentAnswerString.equals(currentQuestion.getCorrectAnswerString())) {
                //correct answer --> change color to green
                correctAnswers++;
                resultButtons[questionsIndex].setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            } else {
                //wrong answer --> change color to red
                resultButtons[questionsIndex].setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
        }
    }
}
