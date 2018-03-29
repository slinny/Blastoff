package productions.darthplagueis.capstone;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import productions.darthplagueis.capstone.controller.FragmentAdapter;
import productions.darthplagueis.capstone.fragments.onboardingfragments.ExploreFragment;
import productions.darthplagueis.capstone.fragments.onboardingfragments.MarsFragment;
import productions.darthplagueis.capstone.fragments.onboardingfragments.RocketFragment;
import productions.darthplagueis.capstone.fragments.onboardingfragments.SplashScreenFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static productions.darthplagueis.capstone.util.Constants.FONT_PATH;
import static productions.darthplagueis.capstone.util.Constants.MARS_DELAY_ANIM_DURATION;

/**
 * Presents and controls the four on boarding fragments:
 * SplashScreen, Mars, Rocket, and Explore.
 */
public class OnBoardingActivity extends AppCompatActivity {

    private SplashScreenFragment splashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        setFonts();

        instantiateSplashScreen();

        setViewPagerViews();

        removeSplashScreen();
    }

    /**
     * Use for Custom Downloadable Font to inject to Context
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
    private void instantiateSplashScreen() {
        splashScreen = new SplashScreenFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, splashScreen)
                .commit();
    }

    private void setViewPagerViews() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setViewPager(viewPager);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.image_color_planet);
        tabLayout.getTabAt(1).setIcon(R.drawable.image_color_rocket1);
        tabLayout.getTabAt(2).setIcon(R.drawable.image_color_helmet);

        // Changes the color of the tabLayout icon when it is (100% opacity)
        // and isn't (50% opacity) selected.
        tabLayout.getTabAt(1).getIcon().setAlpha(128);
        tabLayout.getTabAt(2).getIcon().setAlpha(128);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabLayout.getTabAt(0).getIcon().setAlpha(255);
                        tabLayout.getTabAt(1).getIcon().setAlpha(128);
                        tabLayout.getTabAt(2).getIcon().setAlpha(128);
                        break;
                    case 1:
                        tabLayout.getTabAt(0).getIcon().setAlpha(128);
                        tabLayout.getTabAt(1).getIcon().setAlpha(255);
                        tabLayout.getTabAt(2).getIcon().setAlpha(128);
                        break;
                    case 2:
                        tabLayout.getTabAt(0).getIcon().setAlpha(128);
                        tabLayout.getTabAt(1).getIcon().setAlpha(128);
                        tabLayout.getTabAt(2).getIcon().setAlpha(255);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Presents the other three fragments together in a view pager.
     * Titles should be updated.
     * @param viewPager
     */
    private void setViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addOnBoardingFragments(new MarsFragment(), "mars");
        adapter.addOnBoardingFragments(new RocketFragment(), "rocket");
        adapter.addOnBoardingFragments(new ExploreFragment(), "explore");
        viewPager.setAdapter(adapter);
    }

    /**
     * Uses a three second timer and then removes the splash screen and presents the view
     * pager with the other three on boarding fragments.
     */
    private void removeSplashScreen() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .remove(splashScreen)
                        .commitAllowingStateLoss();
            }
        };
        handler.postDelayed(runnable, MARS_DELAY_ANIM_DURATION);
    }
}
