package at.htlkaindorf.exa_103_currency_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //fixed rates
    private static final double EUR_TO_DOLLAR_RATE = 1.11;
    private static final double DOLLAR_TO_EURO_RATE = 0.9;

    private ExchangeState state;

    private EditText inputField;
    private TextView outputField;

    private TextView leftTV;
    private TextView rightTV;

    private ImageButton swapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize variables
        swapBtn = findViewById(R.id.btnSwap);
        inputField = findViewById(R.id.numberInput);
        outputField = findViewById(R.id.tvResult);

        state = ExchangeState.EUR;

        leftTV = findViewById(R.id.left_tv);
        rightTV = findViewById(R.id.right_tv);

        inputField.setHint(R.string.euro_hint);

        swapBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //swap content of tv
                String swap = rightTV.getText().toString();
                rightTV.setText(leftTV.getText().toString());
                leftTV.setText(swap);

                //swap state for further processing + set a new hint
                if(state == ExchangeState.EUR) {
                    state = ExchangeState.DOLLAR;
                    inputField.setHint(R.string.dollar_hint);
                }
                else {
                    state = ExchangeState.EUR;
                    inputField.setHint(R.string.euro_hint);
                }

                //recalculate if input was provided
                if(!inputField.getText().toString().isEmpty()) convert();
            }
        });

        inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //input was provided --> calculate result
                if (s.length() > 0) {
                    convert();
                }
                //no input provided --> just reset output
                else {
                    outputField.setText(getString(R.string.result_default_value));
                }
            }
        });
    }

    private void convert() {
        //read input
        double input = Double.parseDouble(inputField.getText().toString());

        //convert euro to dollar & update output
        if (state == ExchangeState.EUR) {

            String res = String.format("%,.2f", input) +" € are ";
            input *= EUR_TO_DOLLAR_RATE;
            res += String.format("%,.2f", input);
            res += " $.";
            outputField.setText(res);

        }

        //convert dollar to euro & update output
        else {
            String res = String.format("%,.2f", input) +" $ are ";
            input *= DOLLAR_TO_EURO_RATE;
            res += String.format("%,.2f", input);
            res += " €.";
            outputField.setText(res);
        }
    }
}
