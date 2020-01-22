package at.htlkaindorf.linhard_plf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView outputHappiness;
    private EditText inputName;
    private Button btnSend;
    private TextView outputNames;
    private ImageView imageView;

    private HappinessModel happinessModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.get_values();
        this.seekBar.setOnSeekBarChangeListener(new SeekbarHandler());
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSend((Button) v);
            }
        });
        happinessModel = new HappinessModel();
        outputNames.setText(happinessModel.getTopThreeString());
    }

    private void get_values() {
        seekBar = findViewById(R.id.sbHappiness);
        outputHappiness = findViewById(R.id.outputHappiness);
        inputName = findViewById(R.id.inputName);
        btnSend = findViewById(R.id.btnSend);
        outputNames = findViewById(R.id.outputNames);
        imageView = findViewById(R.id.imageView);
    }

    private void onSend(Button b) {
        if(!inputName.getText().toString().isEmpty()){
            Log.i(MainActivity.class.getSimpleName(), "here");
            happinessModel.addHappinessValue(inputName.getText().toString(), seekBar.getProgress());
            happinessModel.getTopThreeString();
        }
    }

    public class SeekbarHandler implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (progress <= 3) {
                imageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp);
            }
            else if (progress <= 7) {
                imageView.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp);
            }
            else {
                imageView.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp);
            }
            outputHappiness.setText("Happiness: " + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
