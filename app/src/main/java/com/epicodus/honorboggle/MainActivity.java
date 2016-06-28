package com.epicodus.honorboggle;

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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.submitWord) Button mSubmitWord;
    @Bind(R.id.textView) TextView mTextview;
    @Bind(R.id.userAnswer) EditText mUserAnswer;
    @Bind(R.id.button2) Button mScoreButton;
    private ArrayList<String> validWords = new ArrayList<>();

    private List generateRandomString() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String vowels = "aeiou";
        Integer counter = 2;
        ArrayList<String> returnList = new ArrayList<>();

        for (int i = 0; i < 8 - counter; i++){
            int randomIndex = (int)(Math.random() * (26));
            String letter = Character.toString((alphabet.charAt(randomIndex)));
            returnList.add(letter);
            if (vowels.contains(letter) && counter > 0) {
                counter --;
            }
        }
        while (returnList.size() < 8) {
            int randomIndex = (int)(Math.random() * (5));
            String vowel = Character.toString(vowels.charAt(randomIndex));
            returnList.add(vowel);
        }
        return returnList;
    }

    private boolean lengthCheck(String answer) {
        if (answer.length() < 3) {
            return false;
        } else {
            return true;
        }
    }

    private boolean letterCheck(String answer, String randomCharacters) {
        boolean letterInvalid = false;
        String localCharacters = randomCharacters;
        for (int i = 0; i < answer.length(); i ++) {
            Character letter = answer.charAt(i);
            String stringLetter = letter.toString();
            if (!(localCharacters.contains(stringLetter))) {
                letterInvalid = true;
            } else {
                localCharacters.indexOf(letter);
                localCharacters = localCharacters.substring(0, localCharacters.indexOf(letter)) + localCharacters.substring(localCharacters.indexOf(letter) + 1, localCharacters.length());
            }
        }
        return letterInvalid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final String randomCharacters = generateRandomString().toString();
        mTextview.setText(randomCharacters);

        mScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putStringArrayListExtra("validWords", validWords);
                startActivity(intent);
            }
        });

        mSubmitWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = mUserAnswer.getText().toString();
                if ( (!(lengthCheck(answer))) && ((letterCheck(answer, randomCharacters)))) {
                    Toast.makeText(MainActivity.this, "Word must be at least 3 letters and contain only listed letters.", Toast.LENGTH_LONG).show();
                } else if (!(lengthCheck(answer))) {
                    Toast.makeText(MainActivity.this, "Word must be at least 3 letters.", Toast.LENGTH_LONG).show();
                } else if ((letterCheck(answer, randomCharacters))) {
                    Toast.makeText(MainActivity.this, "Word must contain only listed letters and letters may only be used as many times as they appear.", Toast.LENGTH_LONG).show();
                } else {
                    validWords.add(answer);
                    Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    mUserAnswer.setText("");
                    Log.d("validWords", "validWords" + validWords.toString());
                }
            }
        });
    }
}
