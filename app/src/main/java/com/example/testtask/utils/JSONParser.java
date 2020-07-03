package com.example.testtask.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONParser {

    public static JSONArray getJSONArray(InputStream responseBody) {

        JSONArray jsonArray = null;
        String json = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(responseBody));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return jsonArray;
    }
}
