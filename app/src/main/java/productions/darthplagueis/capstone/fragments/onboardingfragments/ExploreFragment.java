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
        setDoubleTapToContinue();

        blackHoleImage = parentView.findViewById(R.id.black_hole);
        astronautImage = parentView.findViewById(R.id.astronaut_image);
        marsText = parentView.findViewById(R.id.explore_frag_text);
    }

    // Creates the fragment's animation after checking if the fragment
    // is visible to the user.
    @Override
    public void setAnimations() {
        if (blackHoleImage != null && astronautImage != null && marsText != null) {
            Animation scalingOnce = AnimationUtils.loadAnimation(getParentActivity(), R.anim.scaling_once);
            Animation scalingTwice = AnimationUtils.loadAnimation(getParentActivity(), R.anim.scaling_twice);
            blackHoleImage.startAnimation(scalingOnce);
            astronautImage.startAnimation(scalingTwice);
            marsText.startAnimation(scalingOnce);
            marsText.setVisibility(View.VISIBLE);
            blackHoleImage.setVisibility(View.VISIBLE);
            astronautImage.setVisibility(View.VISIBLE);
        }
    }

    // Detects double taps anywhere in the explore fragment and creates an intent.
    private void setDoubleTapToContinue() {
        final GestureDetectorCompat gestureDetector = new GestureDetectorCompat(getParentActivity(), new GestureDetector.SimpleOnGestureListener());
        parentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                Log.d("Explore Fragment", "onTouch: ");
                return true;
            }
        });
        gestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // getParentActivity().startActivity(new Intent(getParentActivity(), MainActivity.class));

                getParentActivity().startActivity(new Intent(getParentActivity(), GameActivity.class));

                // getParentActivity().startActivity(new Intent(getParentActivity(), TestGameActivity.class));

                // getParentActivity().startActivity(new Intent(getParentActivity(), ArExperienceActivity.class));
                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });
    }
}
