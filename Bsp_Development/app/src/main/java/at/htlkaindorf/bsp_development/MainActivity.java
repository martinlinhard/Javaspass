package at.htlkaindorf.bsp_development;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;

    private Button hello;
    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3);

        /*bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt1.setOnClickListener(new MyClickListener());
        bt2.setOnClickListener(new MyClickListener());
        bt3.setOnClickListener(new MyClickListener());
        bt4.setOnClickListener(this);
        bt4.setOnLongClickListener(this);*/

        //b innere klasse verwenden (evtl andere methode aufrufen und v übergeben)
        //c über xml datei (nur bei onclick) (public void onBtnClick(View v))
        //d activity implementiert den listener (siehe diese klasse xd)
        //e lambda - expr

        RadioButton r;
        CheckBox b;


        hello = findViewById(R.id.btnHello);
        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        hello.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // gestureCompat instanz dient als filter
                return gestureDetectorCompat.onTouchEvent(event);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button b1 = (Button) v;
        Toast.makeText(getApplicationContext(), "Button implements " + b1.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        Button b1 = (Button) v;
        Toast.makeText(getApplicationContext(), "(long) Button implements " + b1.getText(), Toast.LENGTH_SHORT).show();
        return false;
    }

    public class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Button b1 = (Button) v;
            Toast.makeText(getApplicationContext(), "Button" + b1.getText(), Toast.LENGTH_SHORT).show();
        }
    }
    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        public static final int MIN_DIST = 100;
        public static final int MAX_DIST = 1000;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e1.getX() - e2.getX();
            float deltaY = e1.getY() - e2.getY();
            float deltaXABS = deltaX < 0 ? deltaX * -1 : deltaX;
            float deltaYABS = deltaY < 0 ? deltaY* -1 : deltaY;

            if(deltaXABS > MIN_DIST && deltaXABS < MAX_DIST) {
                    if (deltaX > 0) {
                        Log.i(MainActivity.class.getSimpleName(), "Swipe left");
                    }
                    else {
                        Log.i(MainActivity.class.getSimpleName(), "Swipe right");
                    }
            }

            if(deltaYABS > MIN_DIST && deltaYABS< MAX_DIST) {
                if (deltaY > 0) {
                    Log.i(MainActivity.class.getSimpleName(), "Swipe up");
                }
                else {
                    Log.i(MainActivity.class.getSimpleName(), "Swipe down");
                }
            }

            return true;
        }
    }
}
