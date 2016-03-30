package com.codeexercise.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeexercise.R;
import com.codeexercise.adapters.MoviesAdapter;
import com.codeexercise.models.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    Context      mContext;
    RecyclerView mRecycleList;
    RecyclerView.LayoutManager mRecycleLayoutManager;
    RecyclerView.Adapter mRecycleAdapter;

    private List<MovieModel> mMoviesList;

    TextView actorLabel;
    ImageView actorPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        //actorLabel = (TextView) findViewById(R.id.actorNameLabel);
        //actorPicture = (ImageView) findViewById(R.id.actorProfileImage);
        mRecycleList = (RecyclerView) findViewById(R.id.recycleList);
        mRecycleLayoutManager = new LinearLayoutManager(mContext);

        mContext = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent() != null) {

            String jsonData = getIntent().getExtras().getString("ResponseData");
            parseAndGetData(jsonData);
        }

        mRecycleAdapter = new MoviesAdapter(this, mMoviesList);
        mRecycleList.setLayoutManager(mRecycleLayoutManager);
        mRecycleList.setAdapter(mRecycleAdapter);
        mRecycleList.setHasFixedSize(true);

        // Need to work on how to handle recycler view item clicks
        mRecycleList.setClickable(true);

        // RecyclerView doesn't have OnClickListener callback method .. below callback will never get called
        // Need to implement onClick in Adapter ViewHolder

/*        mRecycleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("$$ Position: " +view.getId());
            }
        });*/

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void parseAndGetData(String rawJSONData) {

        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(rawJSONData);

            int pageNumber = jsonResponse.getInt("page");
            JSONArray moviesArray = jsonResponse.getJSONArray("results");

            int dataSize = moviesArray.length();

            mMoviesList = new ArrayList<>(dataSize);

            for (int i = 0; i < dataSize; i++) {

                JSONObject jsonFilm = moviesArray.getJSONObject(i);

                //setActorNameAndPicture(jsonFilm);

                MovieModel film = new MovieModel();

                String dateString = jsonFilm.getString("release_date");

                if (dateString != null && !dateString.equals("null")) {

                    film.setTitle(jsonFilm.getString("title"));
                    film.setPosterPath(jsonFilm.getString("poster_path"));
                    film.setRating(jsonFilm.getString("vote_average"));
                    film.setFormattedDate(dateString);

                    mMoviesList.add(film);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

/*    private void setActorNameAndPicture(JSONObject jsonResponse) throws JSONException {
        actorLabel.setText(jsonResponse.getString("title"));
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w185" + jsonResponse.getString("poster_path"))
                .transform(new RoundedTransformation(20, 5))
                .error(R.drawable.noprofile)
                .into(actorPicture);
    }*/

}
