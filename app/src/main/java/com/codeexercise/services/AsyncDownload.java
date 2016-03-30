package com.codeexercise.services;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.codeexercise.activities.RecyclerActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncDownload extends AsyncTask<String, Integer, String> {


    private Context context;
    private ProgressDialog dialog;

    private String url;

    public AsyncDownload(Context ctx) {
        context = ctx;
    }

    /**
     * onPreExecute runs on the UI thread before doInBackground.
     * This will start showing a small dialog that says Loading with a spinner
     * to let the user know download is in progress
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(context);
        dialog.setMessage("Please Wait..");
        dialog.setProgressStyle(dialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        String url = params[0];
        return openConnection(url); //This is returned to onPostExecute()
    }

    public String openConnection(String URL) {

        String JSONData = null;

        try {

            URL url = new URL(URL);

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            //System.out.println("$$ Response Code: " +httpConn.getResponseCode());
            if (httpConn.getResponseCode() == 200) {
                InputStream is = httpConn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                try {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    //System.out.println("$$ Response: " +sb.toString());
                    JSONData = sb.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    is.close();
                    reader.close();
                }
            }
        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        }
        return JSONData;
    }

    /**
     * onPostExecute runs on the  main (GUI) thread and receives
     * the result of doInBackground.
     *
     * Here we pass a string representation of jsonData to the child/receiver
     * activity.
     *
     * @param jsonData
     */
    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        dialog.dismiss();

        Intent i = new Intent(context, RecyclerActivity.class);
        i.putExtra("ResponseData", jsonData);
        context.startActivity(i);
    }
}
