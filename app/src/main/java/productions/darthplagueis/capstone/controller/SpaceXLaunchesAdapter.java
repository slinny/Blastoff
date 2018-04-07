package productions.darthplagueis.capstone.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.fragments.infofragments.view.SpaceXLaunchesViewHolder;
import productions.darthplagueis.capstone.model.spacex.LaunchesResponse;

/**
 *
 */
public class SpaceXLaunchesAdapter extends RecyclerView.Adapter<SpaceXLaunchesViewHolder> {

    private List<LaunchesResponse> launchesResponseList;

    public SpaceXLaunchesAdapter(List<LaunchesResponse> launchesResponseList) {
        this.launchesResponseList = launchesResponseList;
    }

    @NonNull
    @Override
    public SpaceXLaunchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spacex_itemview, parent, false);
        return new SpaceXLaunchesViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceXLaunchesViewHolder holder, int position) {
        holder.onBind(launchesResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return launchesResponseList.size();
    }
}
