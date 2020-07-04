package com.example.testtask.ui.photos;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.testtask.BuildConfig;
import com.example.testtask.data.api.JsonPlaceHolderAPI;
import com.example.testtask.data.model.Album;
import com.example.testtask.data.model.Photo;
import com.example.testtask.ui.AbstractViewModel;
import com.example.testtask.utils.JSONParser;

import org.json.JSONArray;
import java.util.List;

public class PhotosViewModel extends AbstractViewModel {

    private int mUserId;
    private List<Album> albums;
    protected MutableLiveData<List<Photo>> mPhotos = new MutableLiveData<>();

    public PhotosViewModel(int userId) {
        mUserId = userId;
        updateData();
    }

    @Override
    protected void updateData() {
        mIsLoading.setValue(true);
        new AsyncTask<Void,Void,List<Album>>() {
            @Override
            protected List<Album> doInBackground(Void... voids) {
                JSONArray array = JsonPlaceHolderAPI.loadData(BuildConfig.API_URL + BuildConfig.API_QUERY_ALBUMS);
                List<Album> photos = JSONParser.getJSONAlbums(array, mUserId);
                return photos;
            }
            @Override
            protected void onPostExecute(List<Album> data) {
                albums = data;
            }
        }.execute();

        new AsyncTask<Void,Void,List<Photo>>() {
            @Override
            protected List<Photo> doInBackground(Void... voids) {
                JSONArray array = JsonPlaceHolderAPI.loadData(BuildConfig.API_URL + BuildConfig.API_QUERY_PHOTOS);

                List<Photo> photos = JSONParser.getJSONPhoto(array, albums);
                return photos;
            }
            @Override
            protected void onPostExecute(List<Photo> data) {
                mIsLoading.setValue(false);
                mPhotos.setValue(data);
            }
        }.execute();
    }



    public MutableLiveData<List<Photo>> getPhotos() {
        return mPhotos;
    }

    @Override
    protected void onCleared() {
        //TODO clean up resources
    }
}
