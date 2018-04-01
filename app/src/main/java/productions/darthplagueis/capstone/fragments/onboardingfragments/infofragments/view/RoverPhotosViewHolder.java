package productions.darthplagueis.capstone.fragments.onboardingfragments.infofragments.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.model.marsrover.responsemodel.Photos;

/**
 *
 */
public class RoverPhotosViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public RoverPhotosViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_view_image);
    }

    public void onBind(Photos photos) {
        Glide.with(itemView.getContext())
                .load(photos.getImg_src())
                .into(imageView);
    }
}
