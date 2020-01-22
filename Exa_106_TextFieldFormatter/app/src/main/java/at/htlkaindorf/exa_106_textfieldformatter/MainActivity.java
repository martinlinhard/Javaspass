package at.htlkaindorf.exa_106_textfieldformatter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    private EditText input;

    private CheckBox cbBold;
    private CheckBox cbItalics;

    private RadioGroup group;

    private SeekBar bar;

    private FontStyle style = FontStyle.NORMAL;

    private Typeface currentFont;

    private static final String tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.edInput);

        cbItalics = findViewById(R.id.cbItalic);
        cbBold = findViewById(R.id.cbBold);
        bar = findViewById(R.id.sbSize);

        group = findViewById(R.id.rg);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                input.setTextSize((float) progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int fontStyle = 0;
                switch (style) {
                    case BOLD:
                        fontStyle = Typeface.BOLD;
                        break;
                    case ITALIC:
                        fontStyle = Typeface.ITALIC;
                        break;
                    case BOLD_ITALIC:
                        fontStyle = Typeface.BOLD_ITALIC;

                        break;
                    case NORMAL:
                        fontStyle = Typeface.NORMAL;
                        break;
                }
                Typeface t = null;
                switch (((RadioButton)findViewById(checkedId)).getText().toString()) {
                    case "Ubuntu":
                        t = ResourcesCompat.getFont(getApplicationContext(), R.font.ubuntu);
                        break;
                    case "Raleway":
                        t = ResourcesCompat.getFont(getApplicationContext(), R.font.raleway);
                        break;
                    case "Coming Soon":
                        t = ResourcesCompat.getFont(getApplicationContext(), R.font.coming_soon);
                        break;
                    default:
                        t = ResourcesCompat.getFont(getApplicationContext(), R.font.ubuntu);
                }
                currentFont = t;
                input.setTypeface(t, fontStyle);
            }
        });

        FontStyleHandler fsh = new FontStyleHandler();
        cbBold.setOnClickListener(fsh);
        cbItalics.setOnClickListener(fsh);
    }

    public class FontStyleHandler implements CheckBox.OnClickListener {

        @Override
        public void onClick(View v) {
            if(cbBold.isChecked() && cbItalics.isChecked()) {
                input.setTypeface(currentFont, Typeface.BOLD_ITALIC);
                style = FontStyle.BOLD_ITALIC;
            }
            else if (cbBold.isChecked() && !cbItalics.isChecked()){
                input.setTypeface(currentFont, Typeface.BOLD);
                style = FontStyle.BOLD;
            }
            else if(!cbBold.isChecked() && cbItalics.isChecked()){
                input.setTypeface(currentFont, Typeface.ITALIC);
                style = FontStyle.ITALIC;
            }
            else {
                input.setTypeface(currentFont, Typeface.NORMAL);
                style = FontStyle.NORMAL;
            }
        }
    }
}
