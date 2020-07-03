package com.example.testtask.utils;

import android.util.Log;

import com.example.testtask.data.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONtoUsers {

    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_USERNAME = "username";

    public static List<User> getJSONUsers(JSONArray jsonArray) {
        List<User> users = new ArrayList<>();
        try {
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject c = jsonArray.getJSONObject(i);
                User user = new User();
                user.setId(c.getInt(TAG_ID));
                user.setName(c.getString(TAG_NAME));
                user.setUsername(c.getString(TAG_USERNAME));
                users.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }

}
