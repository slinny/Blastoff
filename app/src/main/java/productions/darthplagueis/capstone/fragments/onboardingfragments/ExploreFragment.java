package productions.darthplagueis.capstone.fragments.onboardingfragments;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import productions.darthplagueis.capstone.MainActivity;
import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;

/**
 * Fragment created for on boarding. This fragment contains information related
 * to the exploration theme.
 */
public class ExploreFragment extends AbstractOnBoardingFragment {

    private ImageView marsImage, rocketShip, astronautImage;
    private TextView marsText;

    // Sets the layout for this fragment.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_explore;
    }

    @Override
    public void onCreateView() {
        setOnTapToContinue();

        marsImage = parentView.findViewById(R.id.mars_image);
        rocketShip = parentView.findViewById(R.id.rocket_ship);
        astronautImage = parentView.findViewById(R.id.astronaut_image);
        marsText = parentView.findViewById(R.id.explore_frag_text);
    }

    @Override
    public void setAnimations() {
        if (marsImage != null && rocketShip != null && astronautImage != null && marsText != null) {
            Animation scalingThrice = AnimationUtils.loadAnimation(getParentActivity(), R.anim.scaling_thrice);
            Animation scalingOnce = AnimationUtils.loadAnimation(getParentActivity(), R.anim.scaling_once);
            marsImage.startAnimation(scalingThrice);
            rocketShip.startAnimation(scalingThrice);
            astronautImage.startAnimation(scalingThrice);
            marsText.startAnimation(scalingOnce);
            marsText.setVisibility(View.VISIBLE);
            marsImage.setVisibility(View.VISIBLE);
            rocketShip.setVisibility(View.VISIBLE);
            astronautImage.setVisibility(View.VISIBLE);
        }
    }

    private void setOnTapToContinue() {
        parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentActivity().startActivity(new Intent(getParentActivity(), MainActivity.class));
            }
        });
    }
}
