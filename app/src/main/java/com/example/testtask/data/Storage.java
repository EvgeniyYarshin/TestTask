package com.example.testtask.data;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.testtask.data.api.JsonPlaceHolderAPI;

public class Storage {
    private LruCache<String, Bitmap> mMemoryCache;

    public Storage() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory());
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
    }

    public void getImage(ImageView imageView, String urlImage) {
        imageView.setTag(urlImage);
        Bitmap bitmap = getBitmapFromMemCache(urlImage);
        if (bitmap == null) {
            new AsyncTask<Void, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(Void... voids) {
                    Bitmap bitmap = JsonPlaceHolderAPI.loadPicture(urlImage);

                    return bitmap;
                }

                @Override
                protected void onPostExecute(Bitmap data) {
                    addBitmapToMemoryCache(urlImage, data);
                    addBitmapToImageView(imageView, data, urlImage);
                }
            }.execute();
        }
        addBitmapToImageView(imageView, bitmap, urlImage);
    }

    public void addBitmapToImageView(ImageView imageView, Bitmap bitmap, String urlImage) {
        imageView.setImageBitmap(null);
        if (urlImage == imageView.getTag())
            imageView.setImageBitmap(bitmap);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}
