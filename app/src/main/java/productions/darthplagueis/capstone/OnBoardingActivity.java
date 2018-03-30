package productions.darthplagueis.capstone;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;
import productions.darthplagueis.capstone.fragments.onboardingfragments.ExploreFragment;
import productions.darthplagueis.capstone.fragments.onboardingfragments.MarsFragment;
import productions.darthplagueis.capstone.fragments.onboardingfragments.RocketFragment;
import productions.darthplagueis.capstone.fragments.onboardingfragments.SplashScreenFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static productions.darthplagueis.capstone.util.Constants.EXPLORE_FRAGMENT;
import static productions.darthplagueis.capstone.util.Constants.FONT_PATH;
import static productions.darthplagueis.capstone.util.Constants.MARS_DELAY_ANIM_DURATION;
import static productions.darthplagueis.capstone.util.Constants.MARS_FRAGMENT;
import static productions.darthplagueis.capstone.util.Constants.ROCKET_FRAGMENT;
import static productions.darthplagueis.capstone.util.Constants.TYPE_FRAGMENT;

/**
 * Presents and controls the four on boarding fragments:
 * SplashScreen, Mars, Rocket, and Explore.
 */
public class OnBoardingActivity extends AppCompatActivity {

    private final String TAG = OnBoardingActivity.class.getSimpleName();

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private RocketFragment rocketFragment = new RocketFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        Intent extras = getIntent();

        setFonts();

        if (extras.getExtras() != null) {
            String typeFragment = extras.getStringExtra(TYPE_FRAGMENT);
            switch (typeFragment) {
                case MARS_FRAGMENT:
                    replaceFragment(new MarsFragment());
                    break;
                case EXPLORE_FRAGMENT:
                    replaceFragment(new ExploreFragment());
                    break;
                case ROCKET_FRAGMENT:
                    showRocketFragment();
                    break;
                default:
                    break;
            }
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

    public void showRocketFragment() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(0, R.anim.fscv_fade_out)
                .replace(R.id.container, rocketFragment)
                .commit();
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

    private void replaceFragment(AbstractOnBoardingFragment fragment) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(0, R.anim.fscv_fade_out)
                .replace(R.id.container, fragment)
                .commit();
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
                if (!rocketFragment.isAdded() && !isFinishing()) {
                    showRocketFragment();
                }
                Log.d(TAG, "handler: " + "MarsFragment already added.");
            }
        };
        handler.postDelayed(runnable, MARS_DELAY_ANIM_DURATION);
    }
}
