package productions.darthplagueis.capstone.fragments.onboardingfragments;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;

/**
 * Fragment created for on boarding. This is the very first screen the user
 * will see. It contains the app name and app logo.
 */
public class SplashScreenFragment extends AbstractOnBoardingFragment {

    // Sets the layout for this fragment.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash_screen;
    }

    @Override
    public void onCreateView() {
        // The views are all created in the layout XML.
    }

    @Override
    public void setAnimations() {
        // No animations in splash screen.
    }
}
