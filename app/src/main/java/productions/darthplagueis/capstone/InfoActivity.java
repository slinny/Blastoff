package productions.darthplagueis.capstone;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import productions.darthplagueis.capstone.fragments.BadgeFragment;
import productions.darthplagueis.capstone.fragments.infofragments.RoverPhotosFragment;
import productions.darthplagueis.capstone.fragments.infofragments.SpaceXLaunchesFragment;
import productions.darthplagueis.capstone.fragments.infofragments.SpaceXRocketsFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

import static productions.darthplagueis.capstone.util.BadgeGranter.grantBadge;
import static productions.darthplagueis.capstone.util.Constants.ALL_LAUNCHES;
import static productions.darthplagueis.capstone.util.Constants.BADGE_COUNTER;
import static productions.darthplagueis.capstone.util.Constants.FALCON_ROCKETS;
import static productions.darthplagueis.capstone.util.Constants.FONT_PATH;
import static productions.darthplagueis.capstone.util.Constants.MDCOLOR_ARRAY;
import static productions.darthplagueis.capstone.util.Constants.ROVER_PHOTOS;
import static productions.darthplagueis.capstone.util.Constants.SHARED_PREFERENCES;
import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getMaterialDesignColor;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = InfoActivity.class.getSimpleName();

    private SharedPreferences sharedPrefs;

    private DrawerLayout drawerLayout;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private int badgeCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        sharedPrefs = this.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        badgeCounter = sharedPrefs.getInt(BADGE_COUNTER, 1);

        setViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_relative_layout_01:
                showRoverPhotosFragment("opportunity");
                break;
            case R.id.card_relative_layout_02:
                showRoverPhotosFragment("spirit");
                break;
            case R.id.card_relative_layout_03:
                showRoverPhotosFragment("curiosity");
                break;
            case R.id.card_relative_layout_04:
                showSpaceXLaunchesFragment("");
                break;
            case R.id.card_relative_layout_05:
                showSpaceXRocketFragment();
                break;
            case R.id.card_relative_layout_06:
                showSpaceXLaunchesFragment("upcoming");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.coord_layout), message, Snackbar.LENGTH_LONG).show();
    }

    private void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.app_name));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        setNavDrawerListener(navigationView);

        TextView exploreText = findViewById(R.id.layout_textview_01);
        CalligraphyUtils.applyFontToTextView(this, exploreText, FONT_PATH);
        TextView launchesText = findViewById(R.id.layout_textview_02);
        CalligraphyUtils.applyFontToTextView(this, launchesText, FONT_PATH);
        TextView oppText = findViewById(R.id.card_textview_01);
        CalligraphyUtils.applyFontToTextView(this, oppText, FONT_PATH);
        TextView spiritText = findViewById(R.id.card_textview_02);
        CalligraphyUtils.applyFontToTextView(this, spiritText, FONT_PATH);
        TextView curText = findViewById(R.id.card_textview_03);
        CalligraphyUtils.applyFontToTextView(this, curText, FONT_PATH);
        TextView pastText = findViewById(R.id.card_textview_04);
        CalligraphyUtils.applyFontToTextView(this, pastText, FONT_PATH);
        TextView upcomingText = findViewById(R.id.card_textview_05);
        CalligraphyUtils.applyFontToTextView(this, upcomingText, FONT_PATH);
        TextView tempText = findViewById(R.id.card_textview_06);
        CalligraphyUtils.applyFontToTextView(this, tempText, FONT_PATH);

        findViewById(R.id.card_relative_layout_01).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_02).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_03).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_04).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_05).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_06).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));

        findViewById(R.id.card_relative_layout_01).setOnClickListener(this);
        findViewById(R.id.card_relative_layout_02).setOnClickListener(this);
        findViewById(R.id.card_relative_layout_03).setOnClickListener(this);
        findViewById(R.id.card_relative_layout_04).setOnClickListener(this);
        findViewById(R.id.card_relative_layout_05).setOnClickListener(this);
        findViewById(R.id.card_relative_layout_06).setOnClickListener(this);
    }

    private void setNavDrawerListener(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.nav_intro:
                                break;
                            default:
                                break;
                        }
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void showRoverPhotosFragment(String roverName) {
        RoverPhotosFragment roverPhotosFragment = new RoverPhotosFragment();
        roverPhotosFragment.setRoverName(roverName);
        addInfoFragment(roverPhotosFragment);
        boolean badgeGivenRoverPhotos = sharedPrefs.getBoolean(ROVER_PHOTOS, false);
        if (!badgeGivenRoverPhotos) {
            showBadgeFragment(ROVER_PHOTOS);
            sharedPrefs.edit().putBoolean(ROVER_PHOTOS, true).apply();
        }
    }

    private void showSpaceXLaunchesFragment(String typeOfLaunch) {
        SpaceXLaunchesFragment spaceXLaunchesFragment = new SpaceXLaunchesFragment();
        spaceXLaunchesFragment.setTypeOfLaunch(typeOfLaunch);
        addInfoFragment(spaceXLaunchesFragment);
        boolean badgeGivenAllLaunches = sharedPrefs.getBoolean(ALL_LAUNCHES, false);
        if (!badgeGivenAllLaunches) {
            showBadgeFragment(ALL_LAUNCHES);
            sharedPrefs.edit().putBoolean(ALL_LAUNCHES, true).apply();
        }
    }

    private void showSpaceXRocketFragment() {
        SpaceXRocketsFragment spaceXRocketsFragment = new SpaceXRocketsFragment();
        addInfoFragment(spaceXRocketsFragment);
        boolean badgeGivenFalconRockets = sharedPrefs.getBoolean(FALCON_ROCKETS, false);
        if (!badgeGivenFalconRockets) {
            showBadgeFragment(FALCON_ROCKETS);
            sharedPrefs.edit().putBoolean(FALCON_ROCKETS, true).apply();
        }
    }

    public void showBadgeFragment(String currentActivity) {
        BadgeFragment badgeFragment = new BadgeFragment();
        badgeFragment.setBadgeText(grantBadge(currentActivity, badgeCounter));
        addInfoFragment(badgeFragment);
        badgeCounter++;
        sharedPrefs.edit().putInt(BADGE_COUNTER, badgeCounter).apply();
    }

    private void addInfoFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
