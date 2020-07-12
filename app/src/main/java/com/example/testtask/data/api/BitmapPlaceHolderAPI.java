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
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "test-rest-app");
            if (connection.getResponseCode() == 200) {
                Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                connection.disconnect();
                return bitmap;
            }
            connection.disconnect();
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
