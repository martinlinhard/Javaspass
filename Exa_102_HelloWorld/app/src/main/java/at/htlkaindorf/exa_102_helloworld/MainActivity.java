package at.htlkaindorf.exa_102_helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button b;
    EditText t;
    TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.editButton);
        t = findViewById(R.id.editName);
        tw = findViewById(R.id.tvHeader);
        final TextView change = findViewById(R.id.textView);
        b.setEnabled(false);

        //read int from resource file
        int val = getResources().getInteger(R.integer.value);

        //event handler
        //new object which implements OnClickListener

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = t.getText().toString();
                change.setText(getString(R.string.new_text, input));
                t.setVisibility(View.INVISIBLE);
                b.setEnabled(false);
                b.setText("Finished");
            }
        });

        //b.setOnClickListener(new TestHandler(tw));

        t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                b.setEnabled(s.length() > 0);
            }
        });
    }
}

class TestHandler implements View.OnClickListener {
    TextView t;
    TextView change;



    public TestHandler(TextView t) {
        this.t = t;
    }

    @Override
    public void onClick(View v) {
        t.setText("This is a test too");
        Log.d("", "This is a awesome message");


    }
}
