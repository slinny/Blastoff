package productions.darthplagueis.capstone.fragments.infofragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractInfoFragment;
import productions.darthplagueis.capstone.controller.SpaceXRocketAdapter;
import productions.darthplagueis.capstone.model.spacex.RocketsResponse;
import productions.darthplagueis.capstone.networking.SpaceXRetrofitFactory;

/**
 *
 */
public class SpaceXRocketsFragment extends AbstractInfoFragment {

    private final String TAG = SpaceXRocketsFragment.class.getSimpleName();

    private RecyclerView recyclerView;

    @Override
    public void onCreateView() {
        recyclerView = parentView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(parentView.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void createApiCall() {
        getRockets();
    }

    private void getRockets() {
        SpaceXRetrofitFactory.SpaceXRocketListener listener = new SpaceXRetrofitFactory.SpaceXRocketListener() {
            @Override
            public void rocketListCallBack(List<RocketsResponse> responseList) {
                recyclerView.setAdapter(new SpaceXRocketAdapter(responseList));
                progressBar.setVisibility(View.GONE);
                showFragmentSnackbar("Learn about the SpaceX rockets.");
            }

            @Override
            public void onErrorCallBack(Throwable t) {
                progressBar.setVisibility(View.GONE);
                showFragmentSnackbar("Network error.");
            }
        };
        SpaceXRetrofitFactory.getInstance().setSpaceXRocketListener(listener);
        SpaceXRetrofitFactory.getInstance().getRocketResponseList();
    }
}
