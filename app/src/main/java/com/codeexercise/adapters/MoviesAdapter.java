package com.codeexercise.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeexercise.R;
import com.codeexercise.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<MovieModel> mMoviesList = null;

    public MoviesAdapter(Context mCtx, List<MovieModel> list) {
        mContext = mCtx;
        mMoviesList = list;
    }

    @Override
    public void onClick(View view) {
        System.out.println("$$ Position: " +view.getId());
        if (view instanceof ImageView)
            System.out.println("$$ Clicked on item Image");
        else if (view instanceof TextView) {
            System.out.println("$$ Clicked on item Text");
        } else {
            System.out.println("$$ Something else");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView   imageView;
        TextView    titleText;
        TextView    releaseDateText;
        TextView    ratingText;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.moviePosterImage);
            titleText = (TextView) itemView.findViewById(R.id.movieTitleLabel);
            releaseDateText = (TextView) itemView.findViewById(R.id.dateValue);
            ratingText = (TextView) itemView.findViewById(R.id.ratingValue);

            imageView.setOnClickListener(MoviesAdapter.this);
            titleText.setOnClickListener(MoviesAdapter.this);
            releaseDateText.setOnClickListener(MoviesAdapter.this);
            ratingText.setOnClickListener(MoviesAdapter.this);
        }
    }

    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {

        if (mMoviesList != null && mMoviesList.size() >= position) {
            MovieModel  model = mMoviesList.get(position);

            Picasso.with(mContext)
                    .load("https://image.tmdb.org/t/p/w185" + model.getPosterPath())
                    .transform(new RoundedTransformation(20, 5))
                    .error(R.drawable.noprofile)
                    .into(holder.imageView);
            holder.titleText.setText(model.getTitle());
            holder.releaseDateText.setText(model.getFormattedDate());
            holder.ratingText.setText(model.getRating());

            holder.imageView.setId(position);
            holder.titleText.setId(position);
        }
    }

    @Override
    public int getItemCount() {
        return mMoviesList != null ? mMoviesList.size() : 0;
    }
}
