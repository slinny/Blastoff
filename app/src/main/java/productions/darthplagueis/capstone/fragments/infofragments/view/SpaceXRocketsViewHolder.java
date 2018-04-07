package productions.darthplagueis.capstone.fragments.infofragments.view;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.model.spacex.RocketsResponse;

/**
 *
 */
public class SpaceXRocketsViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textView;

    public SpaceXRocketsViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_view_image);
        textView = itemView.findViewById(R.id.item_view_text);
    }

    public void onBind(RocketsResponse response) {
        if (response.getId().equalsIgnoreCase("falcon1")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.falcon1)
                    .into(imageView);
        } else if (response.getId().equalsIgnoreCase("falcon9")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.falcon9)
                    .into(imageView);
        } else if (response.getId().equalsIgnoreCase("falconheavy")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.falconheavy)
                    .into(imageView);
        }
        String rocketDetails = "<font color='#6A2868'>Rocket Name:</font>" + " " + response.getName() + " "
                + "<font color='#6A2868'>Rocket Type:</font>" + " " + response.getType() + " "
                + "<font color='#6A2868'>Number of Stages:</font>" + " " + String.valueOf(response.getStages()) + " "
                + "<font color='#6A2868'>Active Status:</font>" + " " + String.valueOf(response.isActive()) + " "
                + "<font color='#6A2868'>Rate of Success:</font>" + " " + String.valueOf(response.getSuccess_rate_pct()) + " % "
                + "<font color='#6A2868'>First Flight:</font>" + " " + response.getFirst_flight() + " "
                + "<font color='#6A2868'>Height:</font>" + " " + String.valueOf(response.getHeight().getFeet()) + " ft "
                + "<font color='#6A2868'>Diameter:</font>" + " " + String.valueOf(response.getDiameter().getFeet()) + " ft "
                + "<font color='#6A2868'>Mass:</font>" + " " + String.valueOf(response.getMass().getLb()) + " lb "
                + "<font color='#6A2868'>Description:</font>" + " " + response.getDescription();
        textView.setText(Html.fromHtml(rocketDetails), TextView.BufferType.SPANNABLE);
    }
}
