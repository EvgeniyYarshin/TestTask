package com.example.testtask.data;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;

import com.example.testtask.data.api.BitmapPlaceHolderAPI;
import com.example.testtask.data.model.Album;
import com.example.testtask.data.model.Photo;
import com.example.testtask.data.model.User;
import com.example.testtask.utils.JSONParser;

import org.json.JSONArray;

import java.util.List;

public class Storage {

    public interface BitmapStorageAsyncResponse {
        void processFinish(Bitmap output);
    }

    private LruCache<String, Bitmap> mMemoryCache;

    public Storage() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory());
        final int cacheSize = maxMemory / 2;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
    }

    public List<User> getUsers(JSONArray jsonArray) {
        return JSONParser.getJSONUsers(jsonArray);
    }

    public List<Photo> getPhotos(JSONArray jsonArray, List<Album> albums) {
        return JSONParser.getJSONPhoto(jsonArray, albums);
    }

    public List<Album> getAlbums(JSONArray jsonArray, int userId) {
        return JSONParser.getJSONAlbums(jsonArray, userId);
    }

    public void getImage(String urlImage, BitmapStorageAsyncResponse asyncResponse) {
        Bitmap bitmap = getBitmapFromMemCache(urlImage);
        if (bitmap == null) {
            new BitmapPlaceHolderAPI(output -> {
                addBitmapToMemoryCache(urlImage, output);
                asyncResponse.processFinish(output);
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, urlImage);
        }
        else
            asyncResponse.processFinish(bitmap);
    }

    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    public interface StorageOwner {
        Storage obtainStorage();
    }
}
