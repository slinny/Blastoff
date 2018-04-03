package productions.darthplagueis.capstone;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import productions.darthplagueis.capstone.fragments.infofragments.RoverPhotosFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

import static productions.darthplagueis.capstone.util.Constants.FONT_PATH;
import static productions.darthplagueis.capstone.util.Constants.MDCOLOR_ARRAY;
import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getMaterialDesignColor;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = InfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_relative_layout_01:
                showRoverPhotosFragment("opportunity");
                Log.d(TAG, "onClick: ");
                break;
            case R.id.card_relative_layout_02:
                showRoverPhotosFragment("spirit");
                Log.d(TAG, "onClick: ");
                break;
            case R.id.card_relative_layout_03:
                showRoverPhotosFragment("curiosity");
                Log.d(TAG, "onClick: ");
                break;
            default:
                break;
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

        TextView exploreText = findViewById(R.id.layout_textview_01);
        CalligraphyUtils.applyFontToTextView(this, exploreText, FONT_PATH);
        findViewById(R.id.card_relative_layout_01).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_02).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_03).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_04).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_05).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_06).setBackgroundColor(getMaterialDesignColor(this, MDCOLOR_ARRAY));
        findViewById(R.id.card_relative_layout_01).setOnClickListener(this);
        findViewById(R.id.card_relative_layout_02).setOnClickListener(this);
        findViewById(R.id.card_relative_layout_03).setOnClickListener(this);
        TextView oppText = findViewById(R.id.card_textview_01);
        CalligraphyUtils.applyFontToTextView(this, oppText, FONT_PATH);
        TextView spiritText = findViewById(R.id.card_textview_02);
        CalligraphyUtils.applyFontToTextView(this, spiritText, FONT_PATH);
        TextView curText = findViewById(R.id.card_textview_03);
        CalligraphyUtils.applyFontToTextView(this, curText, FONT_PATH);

    }

    private void showRoverPhotosFragment(String roverName) {
        RoverPhotosFragment roverPhotosFragment = new RoverPhotosFragment();
        roverPhotosFragment.setRoverName(roverName);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, roverPhotosFragment)
                .addToBackStack(null)
                .commit();
    }
}
