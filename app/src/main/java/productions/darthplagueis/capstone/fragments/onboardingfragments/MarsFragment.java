package productions.darthplagueis.capstone.fragments.onboardingfragments;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.UnavailableException;

import me.toptas.fancyshowcase.FancyShowCaseView;
import productions.darthplagueis.capstone.ArExperienceActivity;
import productions.darthplagueis.capstone.GameActivity;
import productions.darthplagueis.capstone.OnBoardingActivity;
import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;

import static productions.darthplagueis.capstone.util.Constants.EXPLORE_FRAGMENT;
import static productions.darthplagueis.capstone.util.Constants.MARS_ANIM_DURATION;
import static productions.darthplagueis.capstone.util.Constants.MARS_DELAY_ANIM_DURATION;
import static productions.darthplagueis.capstone.util.Constants.TYPE_FRAGMENT;

/**
 * Fragment created for on boarding. This fragment contains information related
 * to the Mars theme.
 */
public class MarsFragment extends AbstractOnBoardingFragment {

    private final String TAG = MarsFragment.class.getSimpleName();

    private ImageView marsImage;
    private FancyShowCaseView fancyShowCaseView;

    // Sets the layout for this fragment.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mars;
    }

    @Override
    public void onCreateView() {
        marsImage = parentView.findViewById(R.id.mars_image);
        setShowCaseView();
    }

    // Creates the fragment's animation after checking if the fragment
    // is visible to the user.
    @Override
    public void setAnimations() {
        fancyShowCaseView.show();
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
                    break;
                case SUPPORTED_INSTALLED:
                    // getParentActivity().startActivity(new Intent(getParentActivity(), ArExperienceActivity.class));
                    setExploreIntent();
                    Log.d(TAG, "nextScreen: " + "Supported installed.");
                    break;
                default:
                    // For emulator use
                    setExploreIntent();
                    Log.e(TAG,  "Unknown response received by ArCore check.");
                    break;
            }
        } catch (UnavailableException e) {
            Log.e(TAG, "nextScreen: " + e.toString());
            setExploreIntent();
        }
    }

    private void setExploreIntent() {
        Intent exploreFragmentIntent = new Intent(getParentActivity(), OnBoardingActivity.class);
        exploreFragmentIntent.putExtra(TYPE_FRAGMENT, EXPLORE_FRAGMENT);
        startActivity(exploreFragmentIntent);
    }

    private void setShowCaseView() {
        marsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FancyShowCaseView.isVisible(getParentActivity())) {
                    FancyShowCaseView.hideCurrent(getParentActivity());
                }
            }
        });
        fancyShowCaseView = new FancyShowCaseView.Builder(getParentActivity())
                .focusOn(marsImage)
                .enableTouchOnFocusedView(true)
                .closeOnTouch(true)
                .title("Get ready to BLASTOFF \n Swipe left to explore \n a final frontier \n BUT first " +
                        "\n Tap here to STRAP IN")
                .titleGravity(Gravity.BOTTOM | Gravity.CENTER)
                .showOnce("show only once at initial start")
                .build();
    }
}
