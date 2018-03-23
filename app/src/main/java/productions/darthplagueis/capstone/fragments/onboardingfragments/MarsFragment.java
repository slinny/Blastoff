package productions.darthplagueis.capstone.fragments.onboardingfragments;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import me.toptas.fancyshowcase.FancyShowCaseView;
import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;

import static productions.darthplagueis.capstone.util.Constants.MARS_ANIM_DURATION;
import static productions.darthplagueis.capstone.util.Constants.MARS_DELAY_ANIM_DURATION;

/**
 * Fragment created for on boarding. This fragment contains information related
 * to the Mars theme.
 */
public class MarsFragment extends AbstractOnBoardingFragment {

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
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (marsImage != null) {
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
            }
        };
        handler.postDelayed(runnable, MARS_DELAY_ANIM_DURATION);
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
                .title("Get ready to BLASTOFF \nStart here and then swipe \nleft to explore a final \nfrontier \nTap Mars and STRAP IN")
                .titleGravity(Gravity.BOTTOM | Gravity.CENTER)
                // If showOnce is enabled then the showcase view will only be seen once.
                //.showOnce("fancy1")
                .build();
    }
}
