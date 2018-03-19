package productions.darthplagueis.capstone.fragments.onboardingfragments;

import android.animation.ValueAnimator;
import android.widget.ImageView;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;

/**
 * Fragment created for on boarding. This fragment contains information related
 * to the Mars theme.
 */
public class MarsFragment extends AbstractOnBoardingFragment {

    // Sets the layout for this fragment.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mars;
    }

    @Override
    public void onCreateView() {
        final ImageView marsImage = parentView.findViewById(R.id.mars_image);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 360f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                marsImage.setRotation((float) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(15000L);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }
}
