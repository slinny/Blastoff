package productions.darthplagueis.capstone.fragments.introfragments;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import productions.darthplagueis.capstone.GameActivity;
import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractIntroFragment;

import static productions.darthplagueis.capstone.util.Constants.ROCKET_ANIM_DURATION;

/**
 * Fragment created for on boarding. This fragment contains information related
 * to the rocketImage theme.
 */
public class RocketFragment extends AbstractIntroFragment {

    private ImageView rocketImage;
    private TextView rocketText;

    // Sets the layout for this fragment.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_rocket;
    }

    @Override
    public void onCreateView() {
        rocketImage = parentView.findViewById(R.id.rocket_ship);
        rocketText = parentView.findViewById(R.id.rocket_frag_text);
    }

    // Creates the fragment's animation after checking if the fragment
    // is visible to the user.
    @Override
    public void setAnimations() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getParentActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float screenHeight = displayMetrics.heightPixels;
        ValueAnimator valueAnimator = android.animation.ValueAnimator.ofFloat(0f, -screenHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (rocketImage != null) {
                    rocketImage.setTranslationY((float) animation.getAnimatedValue());
                }
            }
        });
        valueAnimator.setDuration(ROCKET_ANIM_DURATION);
        valueAnimator.setInterpolator(new AccelerateInterpolator(2.0f));
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.start();

        // Waits for two seconds after the rocket animation has started
        // to display the text and change the rocket's opacity.
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                rocketImage.setImageAlpha(100);
                rocketText.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(runnable, ROCKET_ANIM_DURATION);
    }

    /**
     * Sets an onDoubleTapListener to the entire screen and sends the
     * user to the GameActivity.
     */
    @Override
    public void nextScreen() {
        getParentActivity().startActivity(new Intent(getParentActivity(), GameActivity.class));
        getParentActivity().getSupportFragmentManager().popBackStack();
    }
}
