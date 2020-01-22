package at.htlkaindorf.exa_login;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button signInBTN;

    private TextView email;
    private TextView password;

    private ImageView imgEmail;
    private ImageView imgPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInBTN = findViewById(R.id.btnSignIN);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);

        imgEmail = findViewById(R.id.imgEmail);
        imgPassword = findViewById(R.id.imgPassword);

        signInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignIn();
            }
        });
    }

    public void onSignIn() {

        if (email.getText().toString().equals("admin")){
            imgEmail.setVisibility(View.VISIBLE);
        }

        if (password.getText().toString().equals("admin")){
            imgPassword.setVisibility(View.VISIBLE);
        }
    }
}
