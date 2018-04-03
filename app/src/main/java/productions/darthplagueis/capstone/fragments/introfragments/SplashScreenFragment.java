package productions.darthplagueis.capstone.fragments.introfragments;

import productions.darthplagueis.capstone.R;
import productions.darthplagueis.capstone.abstractclasses.AbstractIntroFragment;

/**
 * Fragment created for on boarding. This is the very first screen the user
 * will see. It contains the app name and app logo.
 */
public class SplashScreenFragment extends AbstractIntroFragment {

    // Sets the layout for this fragment.
    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash_screen;
    }

    @Override
    public void onCreateView() {
        // View is created entirely in XML.
    }

    @Override
    public void setAnimations() {
        // No animations in the splash screen.
    }

    /**
     * Sets an onDoubleTapListener to the entire screen and skips to the next
     * fragment upon user tap.
     */
    @Override
    public void nextScreen() {
        getParentActivity().showJourneyFragment();
    }
}
