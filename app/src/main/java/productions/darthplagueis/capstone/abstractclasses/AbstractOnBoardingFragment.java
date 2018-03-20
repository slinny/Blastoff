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
        return parentView;
    }

    public abstract int getLayoutId();

    public abstract void onCreateView();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            setAnimations();
        }
    }

    public abstract void setAnimations();

    public OnBoardingActivity getParentActivity() {
        return parentActivity;
    }

    private void setParentActivity(@NonNull Context context) {
        parentActivity = ((OnBoardingActivity) context);
    }
}
