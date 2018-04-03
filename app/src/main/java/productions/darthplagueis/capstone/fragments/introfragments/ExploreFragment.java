package productions.darthplagueis.capstone.fragments.introfragments;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import productions.darthplagueis.capstone.InfoActivity;
import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractIntroFragment;

/**
 * Fragment created for on boarding. This fragment contains information related
 * to the exploration theme.
 */
public class ExploreFragment extends AbstractIntroFragment {

    private ImageView astronautImage;
    private TextView marsText;

    // Sets the layout for this fragment.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_explore;
    }

    @Override
    public void onCreateView() {
        astronautImage = parentView.findViewById(R.id.astronaut_image);
        marsText = parentView.findViewById(R.id.explore_frag_text);
    }

    // Creates the fragment's animation after checking if the fragment
    // is visible to the user.
    @Override
    public void setAnimations() {
            Animation scalingOnce = AnimationUtils.loadAnimation(getParentActivity(), R.anim.scaling_once);
            Animation scalingTwice = AnimationUtils.loadAnimation(getParentActivity(), R.anim.scaling_twice);
            astronautImage.startAnimation(scalingTwice);
            marsText.startAnimation(scalingOnce);
            marsText.setVisibility(View.VISIBLE);
            astronautImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void nextScreen() {
        getParentActivity().startActivity(new Intent(getParentActivity(), InfoActivity.class));
    }
}
