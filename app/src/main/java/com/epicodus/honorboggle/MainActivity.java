package com.epicodus.honorboggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mTextview;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        String randomCharacters = generateRandomString().toString();
        mTextview = (TextView) findViewById(R.id.textView);
        mTextview.setText(randomCharacters);


    }
}
