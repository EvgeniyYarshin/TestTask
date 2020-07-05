package com.example.testtask.data.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class BitmapPlaceHolderAPI extends AsyncTask<String, Void, Bitmap> {
    public interface BitmapAsyncResponse {
        void processFinish(Bitmap output);
    }

    private BitmapAsyncResponse callback;

    public BitmapPlaceHolderAPI(BitmapAsyncResponse callback) {
        this.callback = callback;
    }

    @Override
    protected Bitmap doInBackground(String... query) {
        try {
            URL url = new URL(query[0]);
            HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
            myConnection.setRequestProperty("User-Agent", "test-rest-app");
            if (myConnection.getResponseCode() == 200) {
                return BitmapFactory.decodeStream(myConnection.getInputStream());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        callback.processFinish(result);
    }
}
