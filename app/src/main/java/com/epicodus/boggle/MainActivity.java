package com.epicodus.boggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.rollDiceButton) Button mRollDiceButton;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String alphabet = "abcdefghijklmnopqrstuvwxyz";
                final String vowels = "aeiou";

                Random r = new Random();
                for (int i = 0; i < 8; i++) {
                    char letter = alphabet.charAt(r.nextInt(26));
                    String outputLetter = Character.toString(letter);
                    Log.d(TAG, outputLetter);
                }
            }
        });
    }
}
