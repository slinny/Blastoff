package productions.darthplagueis.capstone.fragments.introfragments;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.UnavailableException;

import productions.darthplagueis.capstone.ArExperienceActivity;
import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractIntroFragment;

import static productions.darthplagueis.capstone.util.Constants.EXPLORE_FRAGMENT;
import static productions.darthplagueis.capstone.util.Constants.MARS_ANIM_DURATION;

/**
 * Fragment created for on boarding. This fragment contains information related
 * to the Mars theme.
 */
public class MarsFragment extends AbstractIntroFragment {

    private final String TAG = MarsFragment.class.getSimpleName();

    private ImageView marsImage;

    // Sets the layout for this fragment.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mars;
    }

    @Override
    public void onCreateView() {
        marsImage = parentView.findViewById(R.id.mars_image);
    }

    // Creates the fragment's animation after checking if the fragment
    // is visible to the user.
    @Override
    public void setAnimations() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 360f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                marsImage.setRotation((float) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(MARS_ANIM_DURATION);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.start();
    }

    /**
     * Checks to see if the current device is capable of using ARCore.
     */
    @Override
    public void nextScreen() {
        try {
            switch (ArCoreApk.getInstance().checkAvailability(getParentActivity())) {
                case UNSUPPORTED_DEVICE_NOT_CAPABLE:
                    setExploreIntent();
                    break;
                case SUPPORTED_NOT_INSTALLED:
                    getParentActivity().startActivity(new Intent(getParentActivity(), ArExperienceActivity.class));
                    getParentActivity().getSupportFragmentManager().popBackStack();
                    break;
                case SUPPORTED_INSTALLED:
                    getParentActivity().startActivity(new Intent(getParentActivity(), ArExperienceActivity.class));
                    getParentActivity().getSupportFragmentManager().popBackStack();
                    //setExploreIntent();
                    Log.d(TAG, "nextScreen: " + "Supported installed.");
                    break;
                default:
                    // For emulator use
                    setExploreIntent();
                    Log.e(TAG, "Unknown response received by ArCore check.");
                    break;
            }
        } catch (UnavailableException e) {
            Log.e(TAG, "nextScreen: " + e.toString());
            setExploreIntent();
        }
    }

    private void setExploreIntent() {
        getParentActivity().introFragmentSwitcher(EXPLORE_FRAGMENT);
    }
}
