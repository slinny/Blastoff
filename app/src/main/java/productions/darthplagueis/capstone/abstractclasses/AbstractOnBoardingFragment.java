package productions.darthplagueis.capstone.abstractclasses;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import productions.darthplagueis.capstone.GameActivity;
import productions.darthplagueis.capstone.OnBoardingActivity;

/**
 * A template from which to create on boarding fragments in an
 * attempt to reduce boilerplate.
 */
public abstract class AbstractOnBoardingFragment extends Fragment {

    public View parentView;

    private OnBoardingActivity parentActivity;

    public AbstractOnBoardingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setParentActivity(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutId(), container, false);
        onCreateView();
        setDoubleTapToContinue();
        return parentView;
    }

    public abstract int getLayoutId();

    public abstract void onCreateView();

    @Override
    public void onStart() {
        super.onStart();
        setAnimations();
    }

    public abstract void setAnimations();

    public OnBoardingActivity getParentActivity() {
        return parentActivity;
    }

    private void setParentActivity(@NonNull Context context) {
        parentActivity = ((OnBoardingActivity) context);
    }

    /**
     * Detects double taps anywhere on the screen and sends the user
     * to the next screen.
     */
    private void setDoubleTapToContinue() {
        final GestureDetectorCompat gestureDetector = new GestureDetectorCompat(getParentActivity(), new GestureDetector.SimpleOnGestureListener());
        parentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
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
                nextScreen();
                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });
    }

    public abstract void nextScreen();
}
