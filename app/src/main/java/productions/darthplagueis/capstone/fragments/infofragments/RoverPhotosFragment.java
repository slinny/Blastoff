package productions.darthplagueis.capstone.fragments.infofragments;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import productions.darthplagueis.capstone.InfoActivity;
import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.controller.RoverPhotosAdapter;
import productions.darthplagueis.capstone.model.marsrover.responsemodel.Photos;
import productions.darthplagueis.capstone.networking.NasaRetrofitFactory;

import static productions.darthplagueis.capstone.util.NetworkConnectivity.isConnected;
import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getRandomText;

/**
 *
 */
public class RoverPhotosFragment extends Fragment {

    private View rootView;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private String roverName;

    public RoverPhotosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_rover_photos, container, false);
        setViews();

        if (isConnected(rootView.getContext())) {
            getRoverPhotos();
        } else {
            if (getActivity() != null) {
                ((InfoActivity) getActivity()).showSnackbar("No network connectivity.");
            }
            removeFromView();
        }
        return rootView;
    }

    public void setRoverName(String roverName) {
        this.roverName = roverName;
    }

    private void setViews() {
        AnimationDrawable animationDrawable = (AnimationDrawable)
                rootView.findViewById(R.id.roverphotos_layout).getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position) {
                    case 0:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        rootView.findViewById(R.id.close_btn_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFromView();
            }
        });
    }

    private void getRoverPhotos() {
        NasaRetrofitFactory.PhotoListListener listener = new NasaRetrofitFactory.PhotoListListener() {
            @Override
            public void responseListCallBack(List<Photos> responseList) {
                recyclerView.setAdapter(new RoverPhotosAdapter(responseList));
                progressBar.setVisibility(View.GONE);
                showFragmentSnackbar(getRandomText(rootView.getContext(), "greetings")
                        + ", " + responseList.size() + " " + "photos sent back from Mars.");
            }

            @Override
            public void onErrorCallBack(Throwable t) {
                progressBar.setVisibility(View.GONE);
                showFragmentSnackbar("Network error.");
            }
        };
        NasaRetrofitFactory.getInstance().setPhotoListListener(listener);
        NasaRetrofitFactory.getInstance().retrieveListofRoverPhotos(roverName);
    }

    private void removeFromView() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        }
    }

    private void showFragmentSnackbar(String message) {
        Snackbar.make(rootView.findViewById(R.id.roverphotos_layout), message, Snackbar.LENGTH_LONG).show();
    }
}
