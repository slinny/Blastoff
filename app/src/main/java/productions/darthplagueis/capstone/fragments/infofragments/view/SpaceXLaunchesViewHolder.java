package productions.darthplagueis.capstone.fragments.infofragments.view;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.model.spacex.LaunchesResponse;

/**
 *
 */
public class SpaceXLaunchesViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textView;

    public SpaceXLaunchesViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_view_image);
        textView = itemView.findViewById(R.id.item_view_text);
    }

    public void onBind(LaunchesResponse response) {
        if (response.getLinks().getMission_patch() == null) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.spacex_tesla)
                    .into(imageView);
        } else {
            Glide.with(itemView.getContext())
                    .load(response.getLinks().getMission_patch())
                    .into(imageView);
        }
        String launchDate = response.getLaunch_date_utc().substring(0, 10);
        String launchDetails = "<font color='#6A2868'>Flight number:</font>" + " " + String.valueOf(response.getFlight_number()) + " "
                + "<font color='#6A2868'>Launch Year:</font>" + " " + response.getLaunch_year() + " "
                + "<font color='#6A2868'>Launch Date:</font>" + " " + launchDate + " "
                + "<font color='#6A2868'>Rocket Name:</font>" + " " + response.getRocket().getRocket_name() + " "
                + "<font color='#6A2868'>Rocket Type:</font>" + " " + response.getRocket().getRocket_type() + " "
                + "<font color='#6A2868'>Launch Site: </font>" + " " + response.getLaunch_site().getSite_name_long();
        textView.setText(Html.fromHtml(launchDetails), TextView.BufferType.SPANNABLE);
    }
}
