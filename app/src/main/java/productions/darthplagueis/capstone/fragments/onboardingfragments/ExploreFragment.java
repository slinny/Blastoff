package productions.darthplagueis.capstone.fragments.onboardingfragments;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import productions.darthplagueis.capstone.ArExperienceActivity;
import productions.darthplagueis.capstone.GameActivity;
import productions.darthplagueis.capstone.InfoActivity;
import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.TestGameActivity;
import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;

/**
 * Fragment created for on boarding. This fragment contains information related
 * to the exploration theme.
 */
public class ExploreFragment extends AbstractOnBoardingFragment {

    private ImageView blackHoleImage, astronautImage;
    private TextView marsText;

    // Sets the layout for this fragment.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_explore;
    }

    @Override
    public void onCreateView() {

        blackHoleImage = parentView.findViewById(R.id.black_hole);
        astronautImage = parentView.findViewById(R.id.astronaut_image);
        marsText = parentView.findViewById(R.id.explore_frag_text);
    }

    // Creates the fragment's animation after checking if the fragment
    // is visible to the user.
    @Override
    public void setAnimations() {
            Animation scalingOnce = AnimationUtils.loadAnimation(getParentActivity(), R.anim.scaling_once);
            Animation scalingTwice = AnimationUtils.loadAnimation(getParentActivity(), R.anim.scaling_twice);
            astronautImage.startAnimation(scalingTwice);
            marsText.startAnimation(scalingOnce);
            marsText.setVisibility(View.VISIBLE);
            astronautImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void nextScreen() {
        getParentActivity().startActivity(new Intent(getParentActivity(), InfoActivity.class));
    }
}
