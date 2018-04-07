package productions.darthplagueis.capstone.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.fragments.infofragments.view.SpaceXRocketsViewHolder;
import productions.darthplagueis.capstone.model.spacex.RocketsResponse;

/**
 *
 */
public class SpaceXRocketAdapter extends RecyclerView.Adapter<SpaceXRocketsViewHolder> {

    private List<RocketsResponse> rocketsResponseList;

    public SpaceXRocketAdapter(List<RocketsResponse> rocketsResponseList) {
        this.rocketsResponseList = rocketsResponseList;
    }

    @NonNull
    @Override
    public SpaceXRocketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spacex_itemview, parent, false);
        return new SpaceXRocketsViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceXRocketsViewHolder holder, int position) {
        holder.onBind(rocketsResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return rocketsResponseList.size();
    }
}
