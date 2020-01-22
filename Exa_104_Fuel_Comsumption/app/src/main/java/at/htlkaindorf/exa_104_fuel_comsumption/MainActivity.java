package at.htlkaindorf.exa_104_fuel_comsumption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText fuel;
    EditText distance;

    Button calculateButton;
    TextView output;

    boolean fuel_content = false;
    boolean distance_content = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fuel = findViewById(R.id.inputFuel);
        distance = findViewById(R.id.inputKM);

        calculateButton = findViewById(R.id.buttonCalculate);
        output = findViewById(R.id.tv_output);

        calculateButton.setEnabled(false);

        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double fuel_val = Double.parseDouble(fuel.getText().toString());
                double distance_val = Double.parseDouble(distance.getText().toString());

                fuel.setText("");
                distance.setText("");

                if (distance_val <= 0) {
                    output.setText("Invalid value.");
                } else {
                    double res = (fuel_val / distance_val) * 100;
                    output.setText("Consumption: " + String.format("%.2f", res) + "l/100km");
                }
            }
        });


        fuel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fuel_content = s.length() > 0;
                calculateButton.setEnabled(fuel_content && distance_content);
            }
        });

        distance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                distance_content = s.length() > 0;
                calculateButton.setEnabled(fuel_content && distance_content);
            }
        });
    }
}
