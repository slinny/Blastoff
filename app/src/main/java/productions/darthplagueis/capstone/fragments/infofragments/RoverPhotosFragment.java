package productions.darthplagueis.capstone.fragments.infofragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractInfoFragment;
import productions.darthplagueis.capstone.controller.RoverPhotosAdapter;
import productions.darthplagueis.capstone.model.marsrover.responsemodel.Photos;
import productions.darthplagueis.capstone.networking.NasaRetrofitFactory;

import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getRandomText;

/**
 *
 */
public class RoverPhotosFragment extends AbstractInfoFragment {

    private final String TAG = RoverPhotosFragment.class.getSimpleName();

    private RecyclerView recyclerView;

    private GridLayoutManager gridLayoutManager;
    private RoverPhotosAdapter roverPhotosAdapter;

    private String roverName;
    private int solNumber = 1;
    private boolean loadMore;

    @Override
    public void onCreateView() {
        recyclerView = parentView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(parentView.getContext(), 2);
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

        loadMore = true;
        setScrollListener();
    }

    @Override
    public void createApiCall() {
        getRoverPhotos();
    }

    public void setRoverName(String roverName) {
        this.roverName = roverName;
    }

    private void getRoverPhotos() {
        NasaRetrofitFactory.PhotoListListener listener = new NasaRetrofitFactory.PhotoListListener() {
            @Override
            public void responseListCallBack(List<Photos> responseList) {
                if (roverPhotosAdapter == null) {
                    roverPhotosAdapter = new RoverPhotosAdapter(responseList);
                    recyclerView.setAdapter(roverPhotosAdapter);
                } else {
                    roverPhotosAdapter.updatePhotoList(responseList);
                }
                progressBar.setVisibility(View.GONE);
                showFragmentSnackbar(getRandomText(getParentActivity(), "greetings")
                        + ", " + responseList.size() + " " + "photos sent back from Mars.");
                solNumber++;
                loadMore = true;
            }

            @Override
            public void onErrorCallBack(Throwable t) {
                progressBar.setVisibility(View.GONE);
                showFragmentSnackbar("Network error.");
            }
        };
        NasaRetrofitFactory.getInstance().setPhotoListListener(listener);
        NasaRetrofitFactory.getInstance().setSolNumber(solNumber);
        NasaRetrofitFactory.getInstance().retrieveListOfRoverPhotos(roverName);
    }

    private void setScrollListener() {
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = gridLayoutManager.getChildCount();
                int totalItemCount = gridLayoutManager.getItemCount();
                int pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();

                if (dy > 0) {
                    if (loadMore) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.d(TAG, "onScrolled: ");
                            loadMore = false;
                            progressBar.setVisibility(View.VISIBLE);
                            getRoverPhotos();
                        }
                    }
                }
            }
        });
    }
}
