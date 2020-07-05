package com.example.testtask.utils;

import android.util.Log;

import com.example.testtask.data.model.Album;
import com.example.testtask.data.model.Photo;
import com.example.testtask.data.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    private static final String TAG_ID = "id";
    private static final String TAG_ALBUM_ID = "albumId";
    private static final String TAG_USER_ID = "userId";
    private static final String TAG_NAME = "name";
    private static final String TAG_TITLE = "title";
    private static final String TAG_URL = "url";

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
            int k = 0;
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

    public static List<User> getJSONUsers(JSONArray jsonArray) {
        List<User> users = new ArrayList<>();
        try {
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject c = jsonArray.getJSONObject(i);
                User user = new User();
                user.setId(c.getInt(TAG_ID));
                user.setName(c.getString(TAG_NAME));
                users.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<Album> getJSONAlbums(JSONArray jsonArray, int userId) {
        List<Album> albums = new ArrayList<>();
        try {
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject c = jsonArray.getJSONObject(i);
                if(c.getInt(TAG_USER_ID) == userId) {
                    Album album = new Album();
                    album.setId(c.getInt(TAG_ID));
                    albums.add(album);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return albums;
    }

    public static List<Photo> getJSONPhoto(JSONArray jsonArray, List<Album> albums) {
        List<Photo> photos = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);
                for(int j = 0; j < albums.size(); j++) {
                    if(c.getInt(TAG_ALBUM_ID) == albums.get(j).getId()) {
                        Photo photo = new Photo();
                        photo.setTitle(c.getString(TAG_TITLE));
                        photo.setUrl(c.getString(TAG_URL));
                        photos.add(photo);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return photos;
    }
}

