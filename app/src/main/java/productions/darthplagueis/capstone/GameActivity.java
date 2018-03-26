package productions.darthplagueis.capstone;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static productions.darthplagueis.capstone.util.Constants.FALCON_HEAVY_ROCKET;
import static productions.darthplagueis.capstone.util.Constants.LEFT_BOOSTER_IMAGE;
import static productions.darthplagueis.capstone.util.Constants.PAY_LOAD;
import static productions.darthplagueis.capstone.util.Constants.RIGHT_BOOSTER_IMAGE;
import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getMaterialDesignColor;
import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getRandomText;

public class GameActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {

    private final String TAG = GameActivity.class.getSimpleName();

    private ImageView leftBooster, rightBooster, payLoad, falconHeavyRocket;

    private boolean alertIsActive = false;
    private int eventCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        createGameLayout();

        setCloseButton();
    }

    @Override
    public boolean onLongClick(View v) {
        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);

        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            v.startDragAndDrop(data, shadowBuilder, v, 0);
        } else {
            v.startDrag(data, shadowBuilder, v, 0);
        }

        v.setVisibility(View.INVISIBLE);
        return true;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    v.getBackground().setColorFilter(getResources().getColor(R.color.alpha_white),
                            PorterDuff.Mode.SRC_IN);
                    v.invalidate();
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.getBackground().setColorFilter(getMaterialDesignColor(GameActivity.this, "800"),
                        PorterDuff.Mode.SRC_IN);
                v.invalidate();
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                v.getBackground().clearColorFilter();
                v.invalidate();
                return true;
            case DragEvent.ACTION_DROP:
                ClipData.Item item = event.getClipData().getItemAt(0);
                String dragData = item.getText().toString();
                // Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                v.getBackground().clearColorFilter();
                v.invalidate();

                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) v;
                container.addView(view);
                view.setVisibility(View.VISIBLE);
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                v.getBackground().clearColorFilter();
                v.invalidate();

                if (event.getResult()) {
                    if (eventCounter == 0 && !alertIsActive) {
                        alertIsActive = true;
                        makeAlert();
                    } else if (eventCounter != 0 && eventCounter < 6) {
                        Log.i(TAG, "onDrag: " + "Not enough events for an Alert.");
                    } else if (eventCounter >= 10 && !alertIsActive) {
                        eventCounter = 0;
                        alertIsActive = true;
                        makeAlert();
                    } else {
                        Log.d(TAG, "onDrag: " + "Alert is active.");
                    }
                } else {
                    Log.e(TAG, "onDrag: " + "Failed");
                }

                eventCounter++;
                return true;
            default:
                Log.e(TAG, "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }

    /**
     * The layout is created programmatically since when there is the ability to
     * make all the containers (linear layouts), which house the image views being dragged,
     * of a size proportionally to the screen size. Then onLongClick or onDrag listeners
     * are attached to the views being manipulated.
     */
    private void createGameLayout() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        int halfScreenWidth = (int) (screenWidth * 0.5);
        int thirdScreenWidth = screenWidth / 3;
        int quarterScreenWidth = (int) (halfScreenWidth * 0.5);
        int quarterScreenHeight = (int) (screenHeight * 0.25);
        int padding = (int) (quarterScreenHeight * 0.20);
        int halfPadding = (int) (padding * 0.5);

        GridLayout.Spec row1 = GridLayout.spec(0, 2);
        GridLayout.Spec row2 = GridLayout.spec(2, 4);
        GridLayout.Spec row3 = GridLayout.spec(6, 2);
        //GridLayout.Spec row4 = GridLayout.spec(8, 4);

        GridLayout.Spec col1 = GridLayout.spec(0);
        GridLayout.Spec col2 = GridLayout.spec(1);
        GridLayout.Spec col3 = GridLayout.spec(2);
        GridLayout.Spec col3Span = GridLayout.spec(0, 3);

        GridLayout gridLayout = findViewById(R.id.grid_layout);
        gridLayout.setColumnCount(3);
        gridLayout.setRowCount(8);

        LinearLayout topLayout = new LinearLayout(this);
        GridLayout.LayoutParams top = new GridLayout.LayoutParams(row1, col3Span);
        top.width = screenWidth;
        top.height = quarterScreenHeight;
        topLayout.setLayoutParams(top);
        topLayout.setOrientation(LinearLayout.VERTICAL);
        topLayout.setGravity(Gravity.CENTER);
        topLayout.setPadding(16, padding * 2, 16, halfPadding);
        topLayout.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        topLayout.setOnDragListener(this);
        gridLayout.addView(topLayout, top);

        LinearLayout colLayout1 = new LinearLayout(this);
        GridLayout.LayoutParams colLeft = new GridLayout.LayoutParams(row2, col1);
        colLeft.width = thirdScreenWidth;
        colLeft.height = quarterScreenHeight * 2;
        colLayout1.setLayoutParams(colLeft);
        colLayout1.setOrientation(LinearLayout.VERTICAL);
        colLayout1.setGravity(Gravity.END);
        colLayout1.setPadding(16, halfPadding, 0, halfPadding);
        colLayout1.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        colLayout1.setOnDragListener(this);
        gridLayout.addView(colLayout1, colLeft);

        LinearLayout colLayout2 = new LinearLayout(this);
        GridLayout.LayoutParams colMiddle = new GridLayout.LayoutParams(row2, col2);
        colMiddle.width = thirdScreenWidth;
        colMiddle.height = quarterScreenHeight * 2;
        colLayout2.setLayoutParams(colMiddle);
        colLayout2.setOrientation(LinearLayout.VERTICAL);
        colLayout2.setGravity(Gravity.CENTER);
        colLayout2.setPadding(16, halfPadding, 16, halfPadding);
        colLayout2.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        colLayout2.setOnDragListener(this);
        gridLayout.addView(colLayout2, colMiddle);

        LinearLayout colLayout3 = new LinearLayout(this);
        GridLayout.LayoutParams colRight = new GridLayout.LayoutParams(row2, col3);
        colRight.width = thirdScreenWidth;
        colRight.height = quarterScreenHeight * 2;
        colLayout3.setLayoutParams(colRight);
        colLayout3.setOrientation(LinearLayout.VERTICAL);
        colLayout3.setGravity(Gravity.START);
        colLayout3.setPadding(0, halfPadding, 16, halfPadding);
        colLayout3.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        colLayout3.setOnDragListener(this);
        gridLayout.addView(colLayout3, colRight);

        LinearLayout bottomLayout = new LinearLayout(this);
        GridLayout.LayoutParams bottom = new GridLayout.LayoutParams(row3, col3Span);
        bottom.width = screenWidth;
        bottom.height = quarterScreenHeight;
        bottomLayout.setLayoutParams(bottom);
        bottomLayout.setOrientation(LinearLayout.HORIZONTAL);
        bottomLayout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        bottomLayout.setPadding(0, padding, 16, padding * 2);
        bottomLayout.setBackgroundColor(getResources().getColor(R.color.alpha_BGC0));
        bottomLayout.setOnDragListener(this);

        leftBooster = new ImageView(this);
        leftBooster.setTag(LEFT_BOOSTER_IMAGE);
        leftBooster.setImageResource(R.drawable.falcon_heavy_booster_left);
        leftBooster.setOnLongClickListener(this);

        rightBooster = new ImageView(this);
        rightBooster.setTag(RIGHT_BOOSTER_IMAGE);
        rightBooster.setImageResource(R.drawable.falcon_heavy_booster_right);
        rightBooster.setOnLongClickListener(this);

        payLoad = new ImageView(this);
        payLoad.setTag(PAY_LOAD);
        payLoad.setImageResource(R.drawable.falcon_heavy_payload);
        payLoad.setOnLongClickListener(this);

        falconHeavyRocket = new ImageView(this);
        falconHeavyRocket.setTag(FALCON_HEAVY_ROCKET);
        falconHeavyRocket.setImageResource(R.drawable.falcon_heavy_rocket);
        falconHeavyRocket.setOnLongClickListener(this);

        bottomLayout.addView(leftBooster);
        bottomLayout.addView(rightBooster);
        bottomLayout.addView(payLoad);
        bottomLayout.addView(falconHeavyRocket);

        gridLayout.addView(bottomLayout, bottom);
    }

    private void setCloseButton() {
        findViewById(R.id.close_btn_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameActivity.this, InfoActivity.class));
                finish();
            }
        });
    }

    private void makeAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
        alertDialog.setTitle(getRandomText(GameActivity.this, "greetings"));
        alertDialog.setMessage("something");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alertIsActive = false;
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
