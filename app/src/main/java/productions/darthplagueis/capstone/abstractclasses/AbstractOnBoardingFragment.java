package productions.darthplagueis.capstone.abstractclasses;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import productions.darthplagueis.capstone.OnBoardingActivity;

/**
 * A template from which to create on boarding fragments in an
 * attempt to reduce boilerplate.
 */
public abstract class AbstractOnBoardingFragment extends Fragment {

    private OnBoardingActivity parentActivity;

    private TapToSkipListener tapToSkipListener;

    public View parentView;

    public AbstractOnBoardingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setParentActivity(context);
        setListener(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutId(), container, false);
        onCreateView();
        skipOnBoardingByTapping();
        return parentView;
    }

    public abstract int getLayoutId();

    public abstract void onCreateView();

    public OnBoardingActivity getParentActivity() {
        return parentActivity;
    }

    private void setParentActivity(@NonNull Context context) {
        parentActivity = ((OnBoardingActivity) context);
    }

    private void setListener(@NonNull Context context) {
        if (context instanceof TapToSkipListener) {
            tapToSkipListener = (TapToSkipListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement TapToSkipListener");
        }
    }

    private void skipOnBoardingByTapping() {
        parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapToSkipListener.onTapCallBack();
            }
        });
    }

    public interface TapToSkipListener {
        void onTapCallBack();
    }
}
