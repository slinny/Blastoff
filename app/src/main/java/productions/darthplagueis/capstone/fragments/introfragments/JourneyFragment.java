package productions.darthplagueis.capstone.fragments.introfragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractIntroFragment;

/**
 *
 */
public class JourneyFragment extends AbstractIntroFragment implements View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_journey;
    }

    @Override
    public void onCreateView() {
        setViews();
    }

    @Override
    public void setAnimations() {
        // No animations in the splash screen.
    }

    @Override
    public void nextScreen() {
        // Does not use double taps.
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_rocket:
                getParentActivity().addIntroFragment(new RocketFragment());
                break;
            case R.id.card_mars:
                getParentActivity().addIntroFragment(new MarsFragment());
                break;
            case R.id.card_explore:
                getParentActivity().addIntroFragment(new ExploreFragment());
                break;
            default:
                break;
        }
    }

    private void setViews() {
        RelativeLayout layout01 = parentView.findViewById(R.id.card_relative_layout_01);
        ImageView imageView01 = parentView.findViewById(R.id.card_imageview_01);
        Glide.with(parentView.getContext())
                .load(R.drawable.starship_planets)
                .apply(new RequestOptions().override(layout01.getWidth(), layout01.getHeight()))
                .apply(new RequestOptions().centerCrop())
                .into(imageView01);

        RelativeLayout layout02 = parentView.findViewById(R.id.card_relative_layout_02);
        ImageView imageView02 = parentView.findViewById(R.id.card_imageview_02);
        Glide.with(parentView.getContext())
                .load(R.drawable.space_planets)
                .apply(new RequestOptions().override(layout02.getWidth(), layout02.getHeight()))
                .apply(new RequestOptions().centerCrop())
                .into(imageView02);

        RelativeLayout layout03 = parentView.findViewById(R.id.card_relative_layout_03);
        ImageView imageView03 = parentView.findViewById(R.id.card_imageview_03);
        Glide.with(parentView.getContext())
                .load(R.drawable.space_alien_rocket)
                .apply(new RequestOptions().override(layout03.getWidth(), layout03.getHeight()))
                .apply(new RequestOptions().centerCrop())
                .into(imageView03);

        parentView.findViewById(R.id.card_rocket).setOnClickListener(this);
        parentView.findViewById(R.id.card_mars).setOnClickListener(this);
        parentView.findViewById(R.id.card_explore).setOnClickListener(this);
    }
}
