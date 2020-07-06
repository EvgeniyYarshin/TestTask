package com.example.testtask.ui.users;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.testtask.R;
import com.example.testtask.databinding.UsersBinding;
import com.example.testtask.ui.AbstractFragment;
import com.example.testtask.ui.photos.PhotosActivity;
import com.example.testtask.ui.photos.PhotosFragment;
import com.example.testtask.utils.UsersViewModelCustomFactory;

import java.util.Objects;

public class UsersFragment extends AbstractFragment {

    private ImageView drawingImageView;

    private UsersViewModel mUsersViewModel;

    private UsersAdapter.OnItemClickListener mOnItemClickListener = id -> {
        Intent intent = new Intent(getActivity(), PhotosActivity.class);
        Bundle args = new Bundle();
        args.putInt(PhotosFragment.PROFILE_KEY, id);
        intent.putExtra(PhotosActivity.USERNAME_KEY, args);
        startActivity(intent);
    };

    static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Objects.requireNonNull(getActivity()).setTitle(R.string.users);
        UsersViewModelCustomFactory factory = new UsersViewModelCustomFactory(storage, mOnItemClickListener);
        mUsersViewModel = ViewModelProviders.of(this, factory).get(UsersViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UsersBinding binding = UsersBinding.inflate(inflater, container, false);
        binding.setVm(mUsersViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawingImageView = Objects.requireNonNull(getView()).findViewById(R.id.users_background);
        view.post(this::drawingLines);
    }

    private void drawingLines() {

        WindowManager windowManager = Objects.requireNonNull(getActivity()).getWindowManager();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);

        float dpi = displaymetrics.density;

        int ScreenStep;
        if(dpi > 1.5)
            ScreenStep = (int)(34.9 * displaymetrics.density);
        else if(dpi > 0.75)
            ScreenStep = (int)(35.9 * displaymetrics.density);
        else
            ScreenStep = (int)(36 * displaymetrics.density);


        int screenWidth = drawingImageView.getWidth();
        int screenHeight = drawingImageView.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawingImageView.setImageBitmap(bitmap);

        Paint paint = new Paint();

        paint.setColor(getResources().getColor(R.color.colorGray));

        int strokeWidth = (int)(1 * displaymetrics.density);
        if(strokeWidth == 0)
            strokeWidth = 1;

        paint.setStrokeWidth(strokeWidth);

        for (int i = ScreenStep; i <= screenHeight; i += ScreenStep)
            canvas.drawLine(0, i, screenWidth, i, paint);
    }
}
