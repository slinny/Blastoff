package productions.darthplagueis.capstone;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import productions.darthplagueis.capstone.abstractclasses.AbstractIntroFragment;
import productions.darthplagueis.capstone.fragments.introfragments.ExploreFragment;
import productions.darthplagueis.capstone.fragments.introfragments.JourneyFragment;
import productions.darthplagueis.capstone.fragments.introfragments.MarsFragment;
import productions.darthplagueis.capstone.fragments.introfragments.RocketFragment;
import productions.darthplagueis.capstone.fragments.introfragments.SplashScreenFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static productions.darthplagueis.capstone.util.Constants.EXPLORE_FRAGMENT;
import static productions.darthplagueis.capstone.util.Constants.FONT_PATH;
import static productions.darthplagueis.capstone.util.Constants.DELAY_ANIM_DURATION;
import static productions.darthplagueis.capstone.util.Constants.MARS_FRAGMENT;
import static productions.darthplagueis.capstone.util.Constants.ROCKET_FRAGMENT;
import static productions.darthplagueis.capstone.util.Constants.INFO_FRAGMENT;

/**
 * Presents and controls the four on boarding fragments:
 * SplashScreen, Mars, Rocket, and Explore.
 */
public class IntroActivity extends AppCompatActivity {

    private final String TAG = IntroActivity.class.getSimpleName();

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private JourneyFragment journeyFragment = new JourneyFragment();
    private RocketFragment rocketFragment = new RocketFragment();
    private MarsFragment marsFragment = new MarsFragment();
    private ExploreFragment exploreFragment = new ExploreFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        Intent extras = getIntent();

        setFonts();

        if (extras.getExtras() != null) {
            String infoFragment = extras.getStringExtra(INFO_FRAGMENT);
            introFragmentSwitcher(infoFragment);
        } else {
            showSplashScreen();
        }
    }

    /**
     * Use for Custom Downloadable Font to inject to Context
     *
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void showJourneyFragment() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(0, R.anim.fscv_fade_out)
                .add(R.id.container, journeyFragment)
                .commitAllowingStateLoss();
    }

    public void introFragmentSwitcher(String introFragment) {
        switch (introFragment) {
            case MARS_FRAGMENT:
                addIntroFragment(marsFragment);
                break;
            case EXPLORE_FRAGMENT:
                addIntroFragment(exploreFragment);
                break;
            case ROCKET_FRAGMENT:
                addIntroFragment(rocketFragment);
                break;
            default:
                if (!journeyFragment.isAdded() && !isFinishing()) {
                    showJourneyFragment();
                }
                break;
        }
    }

    /**
     * Uses the Calligraphy builder to set the font.
     */
    private void setFonts() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(FONT_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void addIntroFragment(AbstractIntroFragment fragment) {
        if (!fragment.isAdded() && !isFinishing()) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(0, R.anim.fscv_fade_out)
                    .add(R.id.container, fragment)
                    .addToBackStack("introFragment")
                    .commit();
            Log.d(TAG, "addIntroFragment: " + "Added.");
        } else {
            fragmentManager.beginTransaction().show(fragment).commit();
            Log.d(TAG, "addIntroFragment: " + "Shown.");
        }
    }

    /**
     * Presents the splash screen by itself. The splash screen fragment is
     * added into the activity's parent layout labeled R.id.container.
     */
    private void showSplashScreen() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fscv_fade_in, 0)
                .add(R.id.container, new SplashScreenFragment())
                .commit();

        removeSplashScreen();
    }

    /**
     * Uses a three second timer and then removes the splash screen and presents the
     * MarsFragment if it is not already visible due to user skipping through the
     * SplashScreen.
     */
    private void removeSplashScreen() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!journeyFragment.isAdded() && !isFinishing()) {
                    showJourneyFragment();
                } else {
                    Log.d(TAG, "handler: " + "JourneyFragment already added.");
                }
            }
        };
        handler.postDelayed(runnable, DELAY_ANIM_DURATION);
    }
}
