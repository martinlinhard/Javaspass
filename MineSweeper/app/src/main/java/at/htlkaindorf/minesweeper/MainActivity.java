package at.htlkaindorf.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import at.htlkaindorf.minesweeper.beans.FieldHandler;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button[][] buttons;
    private FieldHandler fieldHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.grid);
        this.initializeButtons();
        this.fieldHandler = new FieldHandler(this.buttons);
    }

    private void initializeButtons() {
        buttons = new Button[9][9];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Button bt = new Button(this);
                bt.setText(i + "," + j);
                bt.setId(i * 10 + j);
                gridLayout.addView(bt);
                ViewGroup.LayoutParams params = bt.getLayoutParams();
                params.width = width / 9;
                params.height = width / 9;
                buttons[i][j] = bt;
            }
        }
    }
}
