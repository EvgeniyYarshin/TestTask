package com.example.testtask.data.api;

import android.os.AsyncTask;
import com.example.testtask.utils.JSONParser;
import org.json.JSONArray;
import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


public class JsonPlaceHolderAPI extends AsyncTask<String, Void, JSONArray> {

    public interface JsonAsyncResponse {
        void processFinish(JSONArray output);
    }

    private JsonAsyncResponse callback;

    public JsonPlaceHolderAPI(JsonAsyncResponse callback) {
        this.callback = callback;
    }

    @Override
    protected JSONArray doInBackground(String... query) {
        try {
            URL url = new URL(query[0]);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "test-rest-app");
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() == 200) {
                return JSONParser.getJSONArray(connection.getInputStream());
            }
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        callback.processFinish(result);
    }
}

