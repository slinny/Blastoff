package productions.darthplagueis.capstone.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.fragments.infofragments.view.SpaceXLaunchesViewHolder;
import productions.darthplagueis.capstone.model.spacex.SpaceXResponse;

/**
 *
 */
public class SpaceXLaunchesAdapter extends RecyclerView.Adapter<SpaceXLaunchesViewHolder> {

    private List<SpaceXResponse> spaceXResponseList;

    public SpaceXLaunchesAdapter(List<SpaceXResponse> spaceXResponseList) {
        this.spaceXResponseList = spaceXResponseList;
    }

    @NonNull
    @Override
    public SpaceXLaunchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View childView;
        childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spacex_launches_itemview, parent, false);
        return new SpaceXLaunchesViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceXLaunchesViewHolder holder, int position) {
        holder.onBind(spaceXResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return spaceXResponseList.size();
    }
}
