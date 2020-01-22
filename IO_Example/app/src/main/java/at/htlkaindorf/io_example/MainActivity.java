package at.htlkaindorf.io_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button readBtn;
    private TextView outputTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readBtn = findViewById(R.id.btnReadFile);
        outputTV = findViewById(R.id.outputTV);

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = IOHandler.readFile(getApplicationContext());
                    outputTV.setText("Content of file:\n" + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
