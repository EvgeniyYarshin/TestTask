package com.example.testtask.ui.photos;

import com.example.testtask.data.model.Photo;

public class PhotoListItemViewModel {
    private String mTitle;
    private String mUrl;

    PhotoListItemViewModel(Photo photo) {
        mTitle = photo.getTitle();
        mUrl = photo.getUrl();
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }
}
