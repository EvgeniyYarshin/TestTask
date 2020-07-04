package com.example.testtask.ui.photos;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask.data.model.Photo;
import com.example.testtask.databinding.PhotoBinding;

public class PhotosHolder extends RecyclerView.ViewHolder {

    private PhotoBinding mPhotoBinding;

    public PhotosHolder(PhotoBinding binding) {
        super(binding.getRoot());
        mPhotoBinding = binding;
    }

    public void bind(Photo item) {
        mPhotoBinding.setPhoto(new PhotoListItemViewModel(item));
        mPhotoBinding.executePendingBindings();
    }
}
