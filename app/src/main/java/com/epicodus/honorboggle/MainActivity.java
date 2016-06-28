package com.epicodus.honorboggle;

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
//    private TextView mTextview;
//    private Button mSubmitWord;
//    private EditText mUserAnswer;
    private List<String> validWords = new ArrayList<>();

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
        for (int i = 0; i < answer.length(); i ++) {
            Character letter = answer.charAt(i);
            String stringLetter = letter.toString();
            if (!(randomCharacters.contains(stringLetter))) {
                letterInvalid = true;
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
//        mSubmitWord = (Button) findViewById(R.id.submitWord);
//        mUserAnswer = (EditText) findViewById(R.id.userAnswer);
//        mTextview = (TextView) findViewById(R.id.textView);
        mTextview.setText(randomCharacters);

        mSubmitWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = mUserAnswer.getText().toString();
                if ( (!(lengthCheck(answer))) && ((letterCheck(answer, randomCharacters)))) {
                    Toast.makeText(MainActivity.this, "Word must be at least 3 letters and contain only listed letters.", Toast.LENGTH_LONG).show();
                } else if (!(lengthCheck(answer))) {
                    Toast.makeText(MainActivity.this, "Word must be at least 3 letters.", Toast.LENGTH_LONG).show();
                } else if ((letterCheck(answer, randomCharacters))) {
                    Toast.makeText(MainActivity.this, "Word must contain only listed letters.", Toast.LENGTH_LONG).show();
                } else {
                    validWords.add(answer);
                    Log.d("validWords", "validWords" + validWords.toString());
                }
            }
        });
    }
}
