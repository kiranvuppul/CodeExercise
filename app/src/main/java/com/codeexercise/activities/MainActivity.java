package com.codeexercise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.codeexercise.R;
import com.codeexercise.services.AsyncDownload;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String  mMoviesURL = "http://api.themoviedb.org/3/discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22&api_key=64b6f3a69e5717b13ed8a56fe4417e71";

    public void reverseNumber(int number){

        int reverse = 0;
        while (number != 0) {
            reverse = (reverse * 10) + (number % 10);
            number = number / 10;
        }
        System.out.println("$$ Reverse Value: " +reverse);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int value = 12;
        reverseNumber(value);

        ((Button) findViewById(R.id.recyclerActivityBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncDownload download = new AsyncDownload(MainActivity.this);
                download.execute(mMoviesURL);
            }
        });

        ((Button) findViewById(R.id.palindromActivityBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PalindromeActivity.class);
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.loaderActivityBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoaderActivity.class);
                startActivity(intent);
            }
        });

/*       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override8
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
