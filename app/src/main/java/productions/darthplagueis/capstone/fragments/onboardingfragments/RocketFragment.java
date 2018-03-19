package productions.darthplagueis.capstone.fragments.onboardingfragments;

import android.animation.ValueAnimator;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;

/**
 * Fragment created for on boarding. This fragment contains information related
 * to the rocket theme.
 */
public class RocketFragment extends AbstractOnBoardingFragment {

    // Sets the layout for this fragment.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_rocket;
    }

    @Override
    public void onCreateView() {
        final ImageView rocket = parentView.findViewById(R.id.rocket_ship);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getParentActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float screenHeight = displayMetrics.heightPixels;
        ValueAnimator valueAnimator = android.animation.ValueAnimator.ofFloat(0f, -screenHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rocket.setTranslationY((float)animation.getAnimatedValue());
            }
        });
        valueAnimator.setInterpolator(new AccelerateInterpolator(1.5f));
        valueAnimator.setDuration(3000L);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

    }
}
