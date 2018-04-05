package productions.darthplagueis.capstone.fragments.infofragments;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.controller.SpaceXLaunchesAdapter;
import productions.darthplagueis.capstone.model.spacex.SpaceXResponse;
import productions.darthplagueis.capstone.networking.SpaceXRetrofitFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpaceXLaunchesFragment extends Fragment {

    private final String TAG = SpaceXLaunchesFragment.class.getSimpleName();

    private View rootView;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;

    private String typeOfLaunch;

    public SpaceXLaunchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_info, container, false);
        setViews();
        getLaunches();
        return rootView;
    }

    public void setTypeOfLaunch(String typeOfLaunch) {
        this.typeOfLaunch = typeOfLaunch;
    }

    private void setViews() {
        AnimationDrawable animationDrawable = (AnimationDrawable)
                rootView.findViewById(R.id.info_fragment_layout).getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

        progressBar = rootView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        rootView.findViewById(R.id.close_btn_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFromView();
            }
        });
    }

    private void getLaunches() {
        SpaceXRetrofitFactory.LaunchListListener listener = new SpaceXRetrofitFactory.LaunchListListener() {
            @Override
            public void responseListCallBack(List<SpaceXResponse> responseList) {
                recyclerView.setAdapter(new SpaceXLaunchesAdapter(responseList));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onErrorCallBack(Throwable t) {
                progressBar.setVisibility(View.GONE);
                showFragmentSnackbar("Network error.");
            }
        };
        SpaceXRetrofitFactory.getInstance().setLaunchListListener(listener);
        SpaceXRetrofitFactory.getInstance().getLaunchesResponseList(typeOfLaunch);
    }

    private void removeFromView() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        }
    }

    private void showFragmentSnackbar(String message) {
        Snackbar.make(rootView.findViewById(R.id.info_fragment_layout), message, Snackbar.LENGTH_SHORT).show();
    }
}
