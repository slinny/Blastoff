package productions.darthplagueis.capstone.fragments.onboardingfragments.infofragments;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_rover_photos, container, false);
        setViews();
        getRoverPhotos();
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
        recyclerView.addItemDecoration(new DividerItemDecoration(rootView.getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(rootView.getContext(),
                DividerItemDecoration.HORIZONTAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 2));
    }

    private void getRoverPhotos() {
        NasaRetrofitFactory.PhotoListListener listener = new NasaRetrofitFactory.PhotoListListener() {
            @Override
            public void responseListCallBack(List<Photos> responseList) {
                recyclerView.setAdapter(new RoverPhotosAdapter(responseList));
                progressBar.setVisibility(View.GONE);
                if (((InfoActivity)getActivity()) != null) {
                    ((InfoActivity)getActivity()).showSnackbar(responseList.size() + " " + "Photos sent back from MARS"
                            + getRandomText(rootView.getContext(), "greetings"));
                }
            }

            @Override
            public void onErrorCallBack(Throwable t) {
                progressBar.setVisibility(View.GONE);
                if (((InfoActivity)getActivity()) != null) {
                    ((InfoActivity)getActivity()).showSnackbar("Network error.");
                }
            }
        };
        NasaRetrofitFactory.getInstance().setPhotoListListener(listener);
        NasaRetrofitFactory.getInstance().retrieveListofRoverPhotos(roverName);
    }
}
