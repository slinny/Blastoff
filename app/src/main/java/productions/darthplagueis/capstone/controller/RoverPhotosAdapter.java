package productions.darthplagueis.capstone.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.fragments.infofragments.view.RoverPhotosViewHolder;
import productions.darthplagueis.capstone.model.marsrover.responsemodel.Photos;

/**
 *
 */
public class RoverPhotosAdapter extends RecyclerView.Adapter<RoverPhotosViewHolder> {

    private List<Photos> photosList;

    public RoverPhotosAdapter(List<Photos> photosList) {
        this.photosList = photosList;
    }

    @NonNull
    @Override
    public RoverPhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View childView;
        if (viewType == 1) {
            childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.roverphotos_itemview_title, parent, false);
        } else {
            childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.roverphoto_itemview, parent, false);
        }
        return new RoverPhotosViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoverPhotosViewHolder holder, int position) {
        if (position != 0) {
            holder.onBind(photosList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;
    }
}
