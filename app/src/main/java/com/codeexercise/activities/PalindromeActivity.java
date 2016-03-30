package com.codeexercise.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codeexercise.R;

public class PalindromeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palindrom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((Button) findViewById(R.id.okBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = ((EditText) findViewById(R.id.editTextView)).getText().toString();

                if (data.length() < 3) {
                    Toast.makeText(PalindromeActivity.this, "Please enter atleast 3 characters", Toast.LENGTH_SHORT).show();
                } else if (data.contains(" ")) {
                    Toast.makeText(PalindromeActivity.this, "Please enter data without spaces", Toast.LENGTH_SHORT).show();
                } else {
                    String longestPalindrome = longestPalindrome(data);
                    if (longestPalindrome != null && longestPalindrome.length() > 0)
                        ((TextView) findViewById(R.id.textView)).setText("Longest Palindrome: " +longestPalindrome);
                    else
                        ((TextView) findViewById(R.id.textView)).setText("No Palindrome in given string");
                }
            }
        });


/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private String longestPalindrome(String sData) {
        if (sData == null)
            return null;

        if(sData.length() <=1)
            return sData;

        int maxLen = 0;
        String longestStr = null;

        int length = sData.length();

        int[][] table = new int[length][length];

        //every single letter is palindrome
        for (int i = 0; i < length; i++) {
            table[i][i] = 1;
        }

        //e.g. bcba
        //two consecutive same letters are palindrome
        for (int i = 0; i <= length - 2; i++) {
            if (sData.charAt(i) == sData.charAt(i + 1)){
                table[i][i + 1] = 1;
                longestStr = sData.substring(i, i + 2);
            }
        }

        //condition for calculate whole table
        for (int l = 3; l <= length; l++) {
            for (int i = 0; i <= length-l; i++) {
                int j = i + l - 1;
                if (sData.charAt(i) == sData.charAt(j)) {
                    table[i][j] = table[i + 1][j - 1];
                    if (table[i][j] == 1 && l > maxLen)
                        longestStr = sData.substring(i, j + 1);
                } else {
                    table[i][j] = 0;
                }
            }
        }

        return longestStr;
    }
}
