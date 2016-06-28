package com.epicodus.boggle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.rollDiceButton) Button mRollDiceButton;
    @Bind(R.id.submitAnswerButton) Button mSubmitAnswerButton;
    @Bind(R.id.endRoundButton) Button mEndRoundButton;
    @Bind(R.id.lettersDisplay) TextView mLettersDisplay;
    @Bind(R.id.userInput) EditText mUserInput;
    private static final String TAG = MainActivity.class.getSimpleName();
    String letters = "";
    String puzzleLetters;
    ArrayList<String> wordList = new ArrayList<String>();

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
                char letter;

                Random r = new Random();
                for (int i = 0; i < 8; i++) {
                    if (i < 2) {
                        letter = vowels.charAt(r.nextInt(5));
                    } else {
                        letter = alphabet.charAt(r.nextInt(26));
                    }
                    letters = letters + letter;
                    mLettersDisplay.setText(letters);
                }
                puzzleLetters = letters;
                letters = "";
            }
        });

       mSubmitAnswerButton.setOnClickListener(new View.OnClickListener() {
           char[] wordChars;
           @Override
           public void onClick(View v) {
               String word = mUserInput.getText().toString();
               String tempLetters = puzzleLetters;
               wordChars = word.toCharArray();
               Log.d(TAG, puzzleLetters);

               if (wordChars.length >= 3) {
                   int i = 0;
                   for (char c : wordChars) {
                       StringBuilder sb = new StringBuilder(tempLetters);
                       String test = "" + tempLetters.indexOf(c);
                       if (tempLetters.indexOf(c) > -1) {
                           sb.deleteCharAt(tempLetters.indexOf(c));
                           tempLetters = sb.toString();
                           i++;
                           if (i == wordChars.length) {
                               wordList.add(word);
                               Log.d(TAG, String.valueOf(wordList));
                           }
                       } else {
                           Toast.makeText(MainActivity.this, "Invalid Answer!", Toast.LENGTH_LONG).show();
                       }
                   }
               } else {
                   Toast.makeText(MainActivity.this, "Invalid Answer!", Toast.LENGTH_LONG).show();
               }
           }
       });

        mEndRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putExtra("wordList", wordList);
                startActivity(intent);
            }
        });
    }
}
