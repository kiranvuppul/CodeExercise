package com.codeexercise.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieModel {

    private String title;
    private String posterPath;
    private String rating;

    private Date date;
    private String formattedDate;

    public MovieModel() {
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String role) {
        this.rating = role;
    }

    public String getFormattedDate() {

        if (formattedDate == null || formattedDate.equals("null")){
            return "null";
        }

        return formattedDate;
    }

    public void setDate(Date d) {
        date = d;

    }

    public void setFormattedDate(String stringDate) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date unformatedDate = formatter.parse(stringDate);

            setDate(unformatedDate);

            formattedDate = formatter.format(unformatedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String dateInString) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date date = formatter.parse(dateInString);

            this.date = date;

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

}
