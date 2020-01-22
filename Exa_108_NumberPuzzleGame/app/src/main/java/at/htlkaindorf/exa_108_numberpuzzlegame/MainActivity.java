package at.htlkaindorf.exa_108_numberpuzzlegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import at.htlkaindorf.exa_108_numberpuzzlegame.beans.Coordinate;
import at.htlkaindorf.exa_108_numberpuzzlegame.beans.SwipeDirection;

public class MainActivity extends AppCompatActivity {

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn14;
    private Button btn15;

    private Button[] buttons;

    private Button currentBtn;
    private Button btnReset;

    private int currentFreeTile = 15;

    private List<String> correctOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getElements();
        this.initializeField();
        this.initializeListeners();
    }

    private void getElements() {
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);

        buttons = new Button[]{
                btn0, btn1, btn2, btn3,
                btn4, btn5, btn6, btn7,
                btn8, btn9, btn10, btn11,
                btn12, btn13, btn14, btn15
        };

        btnReset = findViewById(R.id.btnReset);
    }

    private void initializeField() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 15; i++){
            numbers.add(i);
            this.correctOrder.add(i + "");
        }

        Collections.shuffle(numbers);

        for (int i = 0; i < numbers.size(); i++) {
            buttons[i].setText(numbers.get(i) + "");
            if (numbers.get(i) % 2 == 0) {
                buttons[i].setBackgroundColor(Color.RED);
            } else {
                buttons[i].setBackgroundColor(Color.WHITE);
            }
        }

        this.currentFreeTile = new Random().nextInt(16);
        swapButtons(buttons[15], buttons[this.currentFreeTile], this.currentFreeTile);
    }

    private void initializeListeners() {
        final GestureDetectorCompat cdc = new GestureDetectorCompat(this, new CustomFlingListener());
        View.OnTouchListener tl = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                currentBtn = (Button) v;
                return cdc.onTouchEvent(event);
            }
        };

        for (Button b : buttons) {
            b.setOnTouchListener(tl);
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeField();
                changeEnabled(true);
            }
        });
    }

    public class CustomFlingListener extends GestureDetector.SimpleOnGestureListener {
        public static final int MIN_DIST = 100;
        public static final int MAX_DIST = 1000;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            float deltaX = e1.getX() - e2.getX();
            float deltaY = e1.getY() - e2.getY();
            float deltaXABS = deltaX < 0 ? deltaX * -1 : deltaX;
            float deltaYABS = deltaY < 0 ? deltaY * -1 : deltaY;

            int indexOfCurrentBtn = Arrays.asList(buttons).indexOf(currentBtn);
            SwipeDirection currentDirection = null;

            if (deltaXABS > MIN_DIST && deltaXABS < MAX_DIST) {
                if (deltaX > 0) {
                    currentDirection = SwipeDirection.LEFT;
                } else {
                    currentDirection = SwipeDirection.RIGHT;
                }
            }

            if (deltaYABS > MIN_DIST && deltaYABS < MAX_DIST) {
                if (deltaY > 0) {
                    currentDirection = SwipeDirection.UP;
                } else {
                    currentDirection = SwipeDirection.DOWN;
                }
            }

            if (swipePossible(indexOfCurrentBtn, currentDirection)) {
                swapButtons(buttons[currentFreeTile], buttons[indexOfCurrentBtn], indexOfCurrentBtn);
            }

            checkButtons();
            return true;
        }
    }

    private void swapButtons(Button oldGrayButton, Button newGrayButton, int newGreyIndex) {
        int btnToChangeColor = ((ColorDrawable) newGrayButton.getBackground()).getColor();
        String btnToChangeText = newGrayButton.getText().toString();

        oldGrayButton.setBackgroundColor(btnToChangeColor);
        oldGrayButton.setText(btnToChangeText);

        newGrayButton.setText("");
        newGrayButton.setBackgroundColor(Color.GRAY);
        this.currentFreeTile = newGreyIndex;
    }

    private boolean swipePossible(int index, SwipeDirection direction) {
        switch (direction) {
            case UP:
                return index - 4 >= 0 && (index - 4) == this.currentFreeTile;
            case DOWN:
                return index + 4 < buttons.length && (index + 4) == this.currentFreeTile;
            case LEFT:
                return !(index % 4 == 0) && (index - 1) == this.currentFreeTile;
            case RIGHT:
                return index != 3 && index != 7 && index != 11 && index != 15 && (index + 1) == this.currentFreeTile;
        }
        return false;
    }

    private void checkButtons() {
        List<String> numbers = new ArrayList<>();

        for (Button b : buttons) {
            numbers.add(b.getText().toString());
        }

        for(int i = 0; i < 15; i++) {
            if(!numbers.get(i).equals(correctOrder.get(i))) return;
        }

        changeEnabled(false);

        Toast.makeText(getApplicationContext(), "You won! Congratulations", Toast.LENGTH_LONG).show();
    }

    private void changeEnabled(boolean enabled) {
        for(Button b: buttons) {
            b.setEnabled(enabled);
        }
    }
}