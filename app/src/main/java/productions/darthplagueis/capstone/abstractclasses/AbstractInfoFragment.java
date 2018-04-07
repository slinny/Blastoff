package productions.darthplagueis.capstone.abstractclasses;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import productions.darthplagueis.capstone.InfoActivity;
import productions.darthplagueis.capstone.R;

import static productions.darthplagueis.capstone.util.NetworkConnectivity.isConnected;

/**
 * A template from which to create INFO fragments in order
 * to reduce boilerplate.
 */
public abstract class AbstractInfoFragment extends Fragment {

    public View parentView;

    public ProgressBar progressBar;

    private InfoActivity parentActivity;

    public AbstractInfoFragment() {
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
        parentView = inflater.inflate(R.layout.fragment_info, container, false);
        setSharedViews();
        onCreateView();
        checkConnectivity();
        return parentView;
    }

    public abstract void onCreateView();

    public InfoActivity getParentActivity() {
        return parentActivity;
    }

    public void showFragmentSnackbar(String message) {
        Snackbar.make(parentView.findViewById(R.id.info_fragment_layout), message, Snackbar.LENGTH_SHORT).show();
    }

    private void setParentActivity(Context context) {
        parentActivity = ((InfoActivity) context);
    }

    private void setSharedViews() {
        AnimationDrawable animationDrawable = (AnimationDrawable)
                parentView.findViewById(R.id.info_fragment_layout).getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

        progressBar = parentView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        parentView.findViewById(R.id.close_btn_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFromView();
            }
        });
    }

    private void checkConnectivity() {
        if (isConnected(parentActivity)) {
            createApiCall();
        } else {
            parentActivity.showSnackbar("No network connectivity.");
            removeFromView();
        }
    }

    public abstract void createApiCall();

    private void removeFromView() {
        parentActivity.getSupportFragmentManager().beginTransaction()
                .remove(AbstractInfoFragment.this)
                .commitAllowingStateLoss();
        if (parentActivity.getSupportFragmentManager().getBackStackEntryCount() > 0) {
            parentActivity.getSupportFragmentManager().popBackStack();
        }
    }
}
