package at.htlkaindorf.exa_105_pocket_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import at.htlkaindorf.exa_105_pocket_calculator.bl.Sign;
import at.htlkaindorf.exa_105_pocket_calculator.bl.Stack;
import at.htlkaindorf.exa_105_pocket_calculator.exceptions.NotEnoughOperandsException;
import at.htlkaindorf.exa_105_pocket_calculator.exceptions.RuntimeStackOverflowError;

public class MainActivity extends AppCompatActivity {

    Button clrButton;
    Button enterButton;

    private Button comma;
    private Button signToggle;

    private Button btnPlus;
    private Button btnMinus;
    private Button btnDivide;
    private Button btnMultiply;

    TextView inputTV;


    private Stack stack;

    private boolean enterPressed = false;

    public final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TOZIP: /home/martin/Documents/School/JavaApps/Exa_105_Pocket_Calculator/app/src/main/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.clrButton = findViewById(R.id.btnClear);
        this.enterButton = findViewById(R.id.btnEnter);
        this.inputTV = findViewById(R.id.tvInput);
        this.comma = findViewById(R.id.btnComma);
        this.signToggle = findViewById(R.id.btnSignToggle);

        this.btnPlus = findViewById(R.id.btnPlus);
        this.btnMinus = findViewById(R.id.btnMinus);
        this.btnMultiply = findViewById(R.id.btnMultiply);
        this.btnDivide = findViewById(R.id.btnDivide);

        SignHandler snh = new SignHandler();

        this.btnDivide.setOnClickListener(snh);
        this.btnMultiply.setOnClickListener(snh);
        this.btnMinus.setOnClickListener(snh);
        this.btnPlus.setOnClickListener(snh);


        this.clrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stack.clear();
                inputTV.setText("");
                inputTV.setHint("0");
            }
        });

        this.comma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputTV.getText().toString().isEmpty() && !inputTV.getText().toString().contains(",")) {
                    inputTV.setText(inputTV.getText().toString() + ",");
                }
            }
        });

        this.signToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputTV.getText().toString().isEmpty()) {
                    if (inputTV.getText().toString().startsWith("-")) {
                        inputTV.setText(inputTV.getText().toString().replace("-", ""));
                    } else {
                        inputTV.setText("-" + inputTV.getText().toString());
                    }
                }
            }
        });
        this.stack = new Stack();
    }

    public void onNumberClick(View v) {
        //String btnText = ((Button) v).getText().toString();
        if (this.enterPressed) {
            inputTV.setText(((Button) v).getText().toString());
            this.enterPressed = false;
        } else inputTV.append(((Button) v).getText().toString());
    }

    public void onEnterClick(View v) {
        //parse current number and put it on the stack
        String input = inputTV.getText().toString();
        if (!input.isEmpty()) {
            try {
                this.stack.push(Double.parseDouble(input.replace(",", ".")));
                this.enterPressed = true;
            } catch (RuntimeStackOverflowError e) {
                stack.clear();
                inputTV.setText("");
                inputTV.setHint("0");
                Toast.makeText(getApplicationContext(), "You entered too many numbers! Buffer Overflow!", Toast.LENGTH_LONG).show();
            }

        }
    }

    public class SignHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String input = inputTV.getText().toString();
            if (!input.isEmpty()) {
                try {
                    stack.push(Double.parseDouble(input.replace(",", ".")));
                    String operator = ((Button) v).getText().toString();
                    double result;
                    switch (operator) {
                        case "+":
                            result = stack.performCalculation(Sign.Plus);
                            inputTV.setHint((result + "").replace(".", ","));
                            break;
                        case "-":
                            result = stack.performCalculation(Sign.Minus);
                            inputTV.setHint((result + "").replace(".", ","));
                            break;
                        case "*":
                            result = stack.performCalculation(Sign.Multiply);
                            inputTV.setHint((result + "").replace(".", ","));
                            break;
                        case "/":
                            result = stack.performCalculation(Sign.Divide);
                            inputTV.setHint((result + "").replace(".", ","));
                            break;
                    }
                    inputTV.setText("");
                } catch (NotEnoughOperandsException e) {
                    Toast.makeText(getApplicationContext(), "Please enter number before continuing", Toast.LENGTH_LONG).show();
                } catch (RuntimeStackOverflowError e) {
                    stack.clear();
                    inputTV.setText("");
                    inputTV.setHint("0");
                    Toast.makeText(getApplicationContext(), "You entered too many numbers! Buffer Overflow!", Toast.LENGTH_LONG).show();
                } catch (ArithmeticException e) {
                    stack.clear();
                    inputTV.setText("");
                    inputTV.setHint("0");
                    Toast.makeText(getApplicationContext(), "Division by 0 not allowed!", Toast.LENGTH_LONG).show();
                }

            } else {
                if (stack.calculationPossible()) {
                    try {
                        String operator = ((Button) v).getText().toString();
                        double result;
                        switch (operator) {
                            case "+":
                                result = stack.performCalculation(Sign.Plus);
                                inputTV.setHint((result + "").replace(".", ","));
                                break;
                            case "-":
                                result = stack.performCalculation(Sign.Minus);
                                inputTV.setHint((result + "").replace(".", ","));
                                break;
                            case "*":
                                result = stack.performCalculation(Sign.Multiply);
                                inputTV.setHint((result + "").replace(".", ","));
                                break;
                            case "/":
                                result = stack.performCalculation(Sign.Divide);
                                inputTV.setHint((result + "").replace(".", ","));
                                break;
                        }
                        inputTV.setText("");
                    } catch (ArithmeticException e) {
                        stack.clear();
                        inputTV.setText("");
                        inputTV.setHint("0");
                        Toast.makeText(getApplicationContext(), "Division by 0 not allowed!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Please enter number before continuing", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
