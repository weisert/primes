package ru.weisert.primes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.TextView;

public class PrimesActivity extends AppCompatActivity {
//    private final static String TAG = "PrimesActivity";
    private ImageButton buttonUp = null;
    private ImageButton buttonDown = null;
    private EditText editText = null;
    private TextView infoTextView = null;
    enum Direction {
        Up,
        Down
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primes);

        buttonUp = findViewById(R.id.imageButtonUp);
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrimesActivity.this.onButtonPressed(Direction.Up);
            }
        });
        buttonDown = findViewById(R.id.imageButtonDown);
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrimesActivity.this.onButtonPressed(Direction.Down);
            }
        });
        editText = findViewById(R.id.editText);
        infoTextView = findViewById(R.id.textView1);
    }

    private void onButtonPressed(Direction direction) {
        String value = editText.getText().toString();
        if (value.isEmpty())
          value = "2";
        long userValue = Long.parseLong(value);
        if (direction == Direction.Up) {
            String info = "Looking for a prime greater than " + userValue;
            infoTextView.setText(info);
            long result = PrimesGenerator.nextPrime(userValue);
            editText.setText("" + result);
            info = info + "\nFound " + result;
            infoTextView.setText(info);
        } else {
            String info = "Looking for a prime less than " + userValue;
            infoTextView.setText(info);
            long result = PrimesGenerator.prevPrime(userValue);
            editText.setText("" + result);
            info = info + "\nFound " + result;
            infoTextView.setText(info);
        }
    }
}
