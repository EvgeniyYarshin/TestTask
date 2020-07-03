package com.example.testtask.data.api;

import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.util.Log;

import com.example.testtask.utils.JSONParser;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class JsonPlaceHolderAPI extends AsyncTaskLoader<JSONArray> {

    public JsonPlaceHolderAPI(Context context) {
        super(context);
    }

    @Override
    public JSONArray loadInBackground() {
        JSONArray jsonArray = null;
        try {
            URL githubEndpoint = new URL("https://jsonplaceholder.typicode.com/users");
            HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();

            myConnection.setRequestProperty("User-Agent", "test-rest-app");

            if (myConnection.getResponseCode() == 200) {
                jsonArray = JSONParser.getJSONArray(myConnection.getInputStream());

            } else {
                // Error handling code goes here
            }
        } catch (IOException e) {
            //TODO error catch
            e.printStackTrace();
        }

        return jsonArray;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
