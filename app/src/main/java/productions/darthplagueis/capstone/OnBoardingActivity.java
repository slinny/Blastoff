package productions.darthplagueis.capstone;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import productions.darthplagueis.capstone.abstractclasses.AbstractOnBoardingFragment;
import productions.darthplagueis.capstone.controller.FragmentAdapter;
import productions.darthplagueis.capstone.fragments.onboardingfragments.ExploreFragment;
import productions.darthplagueis.capstone.fragments.onboardingfragments.MarsFragment;
import productions.darthplagueis.capstone.fragments.onboardingfragments.RocketFragment;
import productions.darthplagueis.capstone.fragments.onboardingfragments.SplashScreenFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Presents and controls the four on boarding fragments.
 */
public class OnBoardingActivity extends AppCompatActivity implements AbstractOnBoardingFragment.TapToSkipListener {

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

    // Use for Custom Downloadable Font to inject to Context
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    // Receives callbacks from the four onboarding fragments whenever the screen is
    // tapped so that the user can skip to next activity.
    @Override
    public void onTapCallBack() {
        startActivity(new Intent(OnBoardingActivity.this, MainActivity.class));
        finish();
    }

    // Uses the Calligraphy builder to set the font.
    private void setFonts() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/app_name_font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    // Presents the splash screen by itself. The splash screen fragment is
    // added into the activity's parent layout labeled R.id.container.
    private void instantiateSplashScreen() {
        splashScreen = new SplashScreenFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, splashScreen).commit();
    }

    private void setViewPagerViews() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        if (tabLayout.getTabAt(0) != null) {
            tabLayout.getTabAt(0).setIcon(R.drawable.image_color_planet);
        }
        if (tabLayout.getTabAt(1) != null) {
            tabLayout.getTabAt(1).setIcon(R.drawable.image_color_rocket1);
        }
        if (tabLayout.getTabAt(2) != null) {
            tabLayout.getTabAt(2).setIcon(R.drawable.image_color_helmet);
        }
    }

    // Presents the other three fragments together in a view pager.
    // Titles should be updated.
    private void setViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addOnBoardingFragments(new MarsFragment(), "mars");
        adapter.addOnBoardingFragments(new RocketFragment(), "rocket");
        adapter.addOnBoardingFragments(new ExploreFragment(), "explore");
        viewPager.setAdapter(adapter);
    }

    // Uses a three second timer and then removes the splash screen and presents the view
    // pager with the other three on boarding fragments.
    private void removeSplashScreen() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Commit allowing state loss is used because the runnable may have not completed
                // yet and the user tapped skip and moved onto the next activity.
                getSupportFragmentManager().beginTransaction().remove(splashScreen).commitAllowingStateLoss();
            }
        };
        handler.postDelayed(runnable, 3000);
    }
}
