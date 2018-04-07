package productions.darthplagueis.capstone.fragments;


import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import productions.darthplagueis.capstone.R;

import static productions.darthplagueis.capstone.util.Constants.DELAY_ANIM_DURATION;

/**
 *
 */
public class BadgeFragment extends Fragment {

    private View rootView;

    private ImageView badgeImage;

    private String badgeText;

    public BadgeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_badge, container, false);
        setViews();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setAnimations();
    }

    public void setBadgeText(String badgeText) {
        this.badgeText = badgeText;
    }

    private void setViews() {
        TextView badgeTextView = rootView.findViewById(R.id.badge_textview);
        badgeTextView.setText(badgeText);
        badgeImage = rootView.findViewById(R.id.badge_image);
    }

    private void setAnimations() {
        ValueAnimator rotationAnimator = ValueAnimator.ofFloat(0f, 360f);
        rotationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                badgeImage.setRotation((float) animation.getAnimatedValue());
            }
        });

        ValueAnimator scalingAnimator = ValueAnimator.ofFloat(0f, 1f);
        scalingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                badgeImage.setScaleX((float) animation.getAnimatedValue());
                badgeImage.setScaleY((float) animation.getAnimatedValue());
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotationAnimator).with(scalingAnimator);
        animatorSet.setDuration(DELAY_ANIM_DURATION);
        animatorSet.start();

        setTimer();
    }

    private void setTimer() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .remove(BadgeFragment.this)
                            .commitAllowingStateLoss();
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        };
        handler.postDelayed(runnable, 5000L);
    }
}
