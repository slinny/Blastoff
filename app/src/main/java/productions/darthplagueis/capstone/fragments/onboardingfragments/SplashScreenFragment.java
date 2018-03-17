package productions.darthplagueis.capstone.fragments.onboardingfragments;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;

/**
 * Fragment created for on boarding. This is the very first screen the user
 * will see. It contains the app name and app logo.
 */
public class SplashScreenFragment extends AbstractOnBoardingFragment {

    // Sets the layout for this fragment.
    // Does not need to be changed.
    // Only the actual XML file needs to be updated.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash_screen;
    }

    @Override
    public void onCreateView() {

    }
}
