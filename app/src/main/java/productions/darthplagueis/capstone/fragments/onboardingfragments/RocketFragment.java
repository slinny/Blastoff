package productions.darthplagueis.capstone.fragments.onboardingfragments;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;

/**
 * Fragment created for on boarding. This fragment contains information related
 * to the rocketImage theme.
 */
public class RocketFragment extends AbstractOnBoardingFragment {

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
        valueAnimator.setInterpolator(new AccelerateInterpolator(1.5f));
        valueAnimator.setDuration(3000L);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

        // Waits for two seconds after the rocket animation has started
        // to display the text.
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                rocketText.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(runnable, 2000L);
    }
}
