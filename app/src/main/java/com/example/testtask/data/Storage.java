package com.example.testtask.data;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.testtask.data.api.JsonPlaceHolderAPI;
import com.example.testtask.utils.ImageRoundCorners;

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

    public void getImage(ImageView imageView, String urlImage, ProgressBar bar) {
        imageView.setTag(urlImage);
        bar.setVisibility(View.VISIBLE);
        Bitmap bitmap = getBitmapFromMemCache(urlImage);
        int o = 1;
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
                    bar.setVisibility(View.GONE);
                    addBitmapToImageView(imageView, data, urlImage);
                }
            }.execute();
        }
        else
            addBitmapToImageView(imageView, bitmap, urlImage);
    }

    public void addBitmapToImageView(ImageView imageView, Bitmap bitmap, String urlImage) {
        imageView.setImageBitmap(null);
        if (urlImage == imageView.getTag()) {
            int k = 0;
            bitmap = ImageRoundCorners.getRoundedCornerBitmap(bitmap, 6);
            imageView.setImageBitmap(bitmap);
        }
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
