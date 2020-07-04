package com.example.testtask.ui.photos;

import androidx.lifecycle.MutableLiveData;
import com.example.testtask.data.model.Photo;

public class PhotoListItemViewModel {
    private String mTitle;
    private String mUrl;
    private MutableLiveData<Boolean> mIsLoadingPhoto = new MutableLiveData<>();

    PhotoListItemViewModel(Photo photo) {
        mTitle = photo.getTitle();
        mUrl = photo.getUrl();
        mIsLoadingPhoto.setValue(false);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public MutableLiveData<Boolean> getIsLoadingPhoto() {
        return mIsLoadingPhoto;
    }
}
