package com.example.testtask.ui.photos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.testtask.common.Refreshable;

//public class PhotosFragment extends Fragment implements Refreshable {
public class PhotosFragment extends Fragment {
    public static final String PROFILE_KEY = "PROFILE_KEY";

    /*private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private View mProfileView;
    private String mUsername;
    private Storage mStorage;
    private Disposable mDisposable;

    private ImageView mProfileImage;
    private TextView mProfileName;
    private TextView mProfileCreatedOn;
    private TextView mProfileLocation;

    public static PhotosFragment newInstance(Bundle args) {
        PhotosFragment fragment = new PhotosFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mStorage = context instanceof Storage.StorageOwner ? ((Storage.StorageOwner) context).obtainStorage() : null;
        mRefreshOwner = context instanceof RefreshOwner ? (RefreshOwner) context : null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mErrorView = view.findViewById(R.id.errorView);
        mProfileView = view.findViewById(R.id.view_profile);

        mProfileImage = view.findViewById(R.id.iv_profile);
        mProfileName = view.findViewById(R.id.tv_display_name_details);
        mProfileCreatedOn = view.findViewById(R.id.tv_created_on_details);
        mProfileLocation = view.findViewById(R.id.tv_location_details);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mUsername = getArguments().getString(PROFILE_KEY);
        }

        if (getActivity() != null) {
            getActivity().setTitle(mUsername);
        }

        mProfileView.setVisibility(View.VISIBLE);

        onRefreshData();
    }

    @Override
    public void onRefreshData() {
        getProfile();
    }

    private void getProfile() {
        mDisposable = ApiUtils.getApiService().getUserInfo(mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(mUsername) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mRefreshOwner.setRefreshState(true))
                .doFinally(() -> mRefreshOwner.setRefreshState(false))
                .subscribe(
                        response -> {
                            mErrorView.setVisibility(View.GONE);
                            mProfileView.setVisibility(View.VISIBLE);
                            bind(response.getUser());
                        },
                        throwable -> {
                            mErrorView.setVisibility(View.VISIBLE);
                            mProfileView.setVisibility(View.GONE);
                        });
    }

    private void bind(User user) {
        Picasso.with(getContext())
                .load(user.getImage().getPhotoUrl())
                .fit()
                .into(mProfileImage);
        mProfileName.setText(user.getDisplayName());
        mProfileCreatedOn.setText(DateUtils.format(user.getCreatedOn()));
        mProfileLocation.setText(user.getLocation());
    }

    @Override
    public void onDetach() {
        mStorage = null;
        mRefreshOwner = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        super.onDetach();
    }*/
}
