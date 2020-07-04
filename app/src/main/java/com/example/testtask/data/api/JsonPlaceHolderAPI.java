package com.example.testtask.data.api;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.testtask.utils.JSONParser;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class JsonPlaceHolderAPI {

    public static JSONArray loadData(String query) {
        JSONArray jsonArray = null;
        try {
            URL url = new URL(query);
            HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();


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

    public static Bitmap loadPicture(String urlPhoto) {
        try {
            URL url = new URL(urlPhoto);
            HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();


            myConnection.setRequestProperty("User-Agent", "test-rest-app");

            if (myConnection.getResponseCode() == 200) {
                Bitmap myBitmap = BitmapFactory.decodeStream(myConnection.getInputStream());
                return myBitmap;
            } else {
                //TODO Error handling code goes here
            }
        } catch (IOException e) {
            //TODO error catch
            return null;
        }
        return null;
    }
}

