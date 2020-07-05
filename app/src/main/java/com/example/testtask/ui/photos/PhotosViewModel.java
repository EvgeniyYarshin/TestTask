package com.example.testtask.ui.photos;

import androidx.lifecycle.MutableLiveData;

import com.example.testtask.BuildConfig;
import com.example.testtask.data.Storage;
import com.example.testtask.data.api.JsonPlaceHolderAPI;
import com.example.testtask.data.model.Album;
import com.example.testtask.data.model.Photo;
import com.example.testtask.ui.AbstractViewModel;

import java.util.List;

public class PhotosViewModel extends AbstractViewModel {

    private int mUserId;
    private Storage mStorage;
    private List<Album> albums;
    private JsonPlaceHolderAPI getAlbumsAsyncTask;
    private JsonPlaceHolderAPI getPhotosAsyncTask;
    private MutableLiveData<List<Photo>> mPhotos = new MutableLiveData<>();

    public PhotosViewModel(Storage storage, int userId) {
        mStorage = storage;
        mUserId = userId;
        updateData();
    }

    @Override
    protected void updateData() {
        mIsErrorVisible.setValue(false);
        mIsLoading.setValue(true);
        getAlbumsAsyncTask = new JsonPlaceHolderAPI(output -> {
            if(output == null) {
                mIsErrorVisible.setValue(true);
                mIsLoading.setValue(false);
                return;
            }

            albums = (mStorage.getAlbums(output, mUserId));
            mIsLoading.setValue(false);
        });
        getAlbumsAsyncTask.execute(BuildConfig.API_URL + BuildConfig.API_QUERY_ALBUMS);

        getPhotosAsyncTask = new JsonPlaceHolderAPI(output -> {
            if(output == null) {
                mIsErrorVisible.setValue(true);
                mIsLoading.setValue(false);
                return;
            }

            mPhotos.setValue(mStorage.getPhotos(output, albums));
            mIsLoading.setValue(false);
        });
        getPhotosAsyncTask.execute(BuildConfig.API_URL + BuildConfig.API_QUERY_PHOTOS);
    }



    public MutableLiveData<List<Photo>> getPhotos() {
        return mPhotos;
    }

    @Override
    protected void onCleared() {
        getAlbumsAsyncTask.cancel(false);
        getPhotosAsyncTask.cancel(false);
    }
}
