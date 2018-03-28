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

import java.util.Random;

import productions.darthplagueis.capstone.util.ColorChanger;

import static productions.darthplagueis.capstone.util.Constants.FALCON_HEAVY_ROCKET;
import static productions.darthplagueis.capstone.util.Constants.LEFT_BOOSTER_IMAGE;
import static productions.darthplagueis.capstone.util.Constants.PAY_LOAD;
import static productions.darthplagueis.capstone.util.Constants.RIGHT_BOOSTER_IMAGE;
import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getMaterialDesignColor;
import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getRandomText;

public class GameActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {

    private final String TAG = GameActivity.class.getSimpleName();

    private ImageView leftBooster, rightBooster, payLoad, falconHeavyRocket;
    private LinearLayout sixthLayout01, sixthLayout03;

    private boolean alertIsActive = false;
    private int eventCounter = 0;
    private int savedRandomNum01 = 1;
    private int savedRandomNum02 = 1;
    private int savedRandomNum03 = 1;
    private int savedRandomNum04 = 1;

    private int baseColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        baseColor = getResources().getColor(R.color.alpha_black02);
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
                    v.getBackground().setColorFilter(getResources().getColor(R.color.alpha_white02),
                            PorterDuff.Mode.SRC_IN);
                    v.invalidate();
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.getBackground().setColorFilter(getMaterialDesignColor(GameActivity.this, "400"),
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
                        //makeAlert();
                    } else if (eventCounter != 0 && eventCounter < 6) {
                        Log.i(TAG, "onDrag: " + "Not enough events for an Alert.");
                    } else if (eventCounter >= 10 && !alertIsActive) {
                        eventCounter = 0;
                        alertIsActive = true;
                        //makeAlert();
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
        int oneHalfQuarterScreenHeight = (int) (quarterScreenHeight * 1.5);
        int padding = (int) (quarterScreenHeight * 0.20);
        int halfPadding = (int) (padding * 0.5);

        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (8 * scale + 0.5f);

        GridLayout.Spec row1 = GridLayout.spec(0, 1);
        GridLayout.Spec row2 = GridLayout.spec(1, 1);
        GridLayout.Spec row3 = GridLayout.spec(2, 1);
        GridLayout.Spec row4 = GridLayout.spec(3, 1);
        GridLayout.Spec row5 = GridLayout.spec(4, 1);
        GridLayout.Spec row6 = GridLayout.spec(5, 1);
        GridLayout.Spec row7 = GridLayout.spec(6, 2);

        GridLayout.Spec col1 = GridLayout.spec(0);
        GridLayout.Spec col2 = GridLayout.spec(1);
        GridLayout.Spec col3 = GridLayout.spec(2);
        GridLayout.Spec col3Span = GridLayout.spec(0, 3);

        final GridLayout gridLayout = findViewById(R.id.grid_layout);
        gridLayout.setColumnCount(3);
        gridLayout.setRowCount(8);

        /*
        TOP MOST ROW
         */
        LinearLayout topLayout = new LinearLayout(this);
        GridLayout.LayoutParams top = new GridLayout.LayoutParams(row1, col3Span);
        top.width = screenWidth;
        top.height = quarterScreenHeight / 2;
        topLayout.setLayoutParams(top);
        topLayout.setOrientation(LinearLayout.VERTICAL);
        topLayout.setGravity(Gravity.CENTER);
        topLayout.setBackgroundColor(getResources().getColor(R.color.alpha_BGC0));
        gridLayout.addView(topLayout, top);

        /*
        SECOND ROW OF CONTAINERS
         */
        LinearLayout secondLayout01 = new LinearLayout(this);
        GridLayout.LayoutParams secondLeft = new GridLayout.LayoutParams(row2, col1);
        secondLeft.width = thirdScreenWidth;
        secondLeft.height = quarterScreenHeight / 2;
        secondLayout01.setLayoutParams(secondLeft);
        secondLayout01.setOrientation(LinearLayout.VERTICAL);
        secondLayout01.setGravity(Gravity.END);
        //secondLayout01.setPadding(16, halfPadding, 0, halfPadding);
        secondLayout01.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        secondLayout01.setOnDragListener(this);
        gridLayout.addView(secondLayout01, secondLeft);

        LinearLayout secondLayout02 = new LinearLayout(this);
        GridLayout.LayoutParams secondMiddle = new GridLayout.LayoutParams(row2, col2);
        secondMiddle.width = thirdScreenWidth;
        secondMiddle.height = quarterScreenHeight / 2;
        secondLayout02.setLayoutParams(secondMiddle);
        secondLayout02.setOrientation(LinearLayout.VERTICAL);
        secondLayout02.setGravity(Gravity.CENTER);
        //secondLayout02.setPadding(16, halfPadding, 16, halfPadding);
        secondLayout02.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        secondLayout02.setOnDragListener(this);
        gridLayout.addView(secondLayout02, secondMiddle);

        LinearLayout secondLayout03 = new LinearLayout(this);
        GridLayout.LayoutParams secondRight = new GridLayout.LayoutParams(row2, col3);
        secondRight.width = thirdScreenWidth;
        secondRight.height = quarterScreenHeight / 2;
        secondLayout03.setLayoutParams(secondRight);
        secondLayout03.setOrientation(LinearLayout.VERTICAL);
        secondLayout03.setGravity(Gravity.START);
        //secondLayout03.setPadding(0, halfPadding, 16, halfPadding);
        secondLayout03.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        secondLayout03.setOnDragListener(this);
        gridLayout.addView(secondLayout03, secondRight);

        /*
        THIRD ROW OF CONTAINERS
         */
        LinearLayout thirdLayout01 = new LinearLayout(this);
        GridLayout.LayoutParams thirdLeft = new GridLayout.LayoutParams(row3, col1);
        thirdLeft.width = thirdScreenWidth;
        thirdLeft.height = quarterScreenHeight / 2;
        thirdLayout01.setLayoutParams(thirdLeft);
        thirdLayout01.setOrientation(LinearLayout.VERTICAL);
        thirdLayout01.setGravity(Gravity.END);
        //thirdLayout01.setPadding(16, halfPadding, 0, halfPadding);
        thirdLayout01.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        thirdLayout01.setOnDragListener(this);
        gridLayout.addView(thirdLayout01, thirdLeft);

        LinearLayout thirdLayout02 = new LinearLayout(this);
        GridLayout.LayoutParams thirdMiddle = new GridLayout.LayoutParams(row3, col2);
        thirdMiddle.width = thirdScreenWidth;
        thirdMiddle.height = quarterScreenHeight / 2;
        thirdLayout02.setLayoutParams(thirdMiddle);
        thirdLayout02.setOrientation(LinearLayout.VERTICAL);
        thirdLayout02.setGravity(Gravity.CENTER);
        //thirdLayout02.setPadding(16, halfPadding, 16, halfPadding);
        thirdLayout02.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        thirdLayout02.setOnDragListener(this);
        gridLayout.addView(thirdLayout02, thirdMiddle);

        LinearLayout thirdLayout03 = new LinearLayout(this);
        GridLayout.LayoutParams thirdRight = new GridLayout.LayoutParams(row3, col3);
        thirdRight.width = thirdScreenWidth;
        thirdRight.height = quarterScreenHeight / 2;
        thirdLayout03.setLayoutParams(thirdRight);
        thirdLayout03.setOrientation(LinearLayout.VERTICAL);
        thirdLayout03.setGravity(Gravity.START);
        //thirdLayout03.setPadding(0, halfPadding, 16, halfPadding);
        thirdLayout03.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        thirdLayout03.setOnDragListener(this);
        gridLayout.addView(thirdLayout03, thirdRight);

        /*
        FOURTH ROW OF CONTAINERS
         */
        LinearLayout fourthLayout01 = new LinearLayout(this);
        GridLayout.LayoutParams fourthLeft = new GridLayout.LayoutParams(row4, col1);
        fourthLeft.width = thirdScreenWidth;
        fourthLeft.height = quarterScreenHeight / 2;
        fourthLayout01.setLayoutParams(fourthLeft);
        fourthLayout01.setOrientation(LinearLayout.VERTICAL);
        fourthLayout01.setGravity(Gravity.END);
        //fourthLayout01.setPadding(16, halfPadding, 0, halfPadding);
        fourthLayout01.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        fourthLayout01.setOnDragListener(this);
        gridLayout.addView(fourthLayout01, fourthLeft);

        LinearLayout fourthLayout02 = new LinearLayout(this);
        GridLayout.LayoutParams fourthMiddle = new GridLayout.LayoutParams(row4, col2);
        fourthMiddle.width = thirdScreenWidth;
        fourthMiddle.height = quarterScreenHeight / 2;
        fourthLayout02.setLayoutParams(fourthMiddle);
        fourthLayout02.setOrientation(LinearLayout.VERTICAL);
        fourthLayout02.setGravity(Gravity.CENTER);
        //fourthLayout02.setPadding(16, halfPadding, 16, halfPadding);
        fourthLayout02.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        fourthLayout02.setOnDragListener(this);
        gridLayout.addView(fourthLayout02, fourthMiddle);

        LinearLayout fourthLayout03 = new LinearLayout(this);
        GridLayout.LayoutParams fourthRight = new GridLayout.LayoutParams(row4, col3);
        fourthRight.width = thirdScreenWidth;
        fourthRight.height = quarterScreenHeight / 2;
        fourthLayout03.setLayoutParams(fourthRight);
        fourthLayout03.setOrientation(LinearLayout.VERTICAL);
        fourthLayout03.setGravity(Gravity.START);
        //fourthLayout03.setPadding(0, halfPadding, 16, halfPadding);
        fourthLayout03.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        fourthLayout03.setOnDragListener(this);
        gridLayout.addView(fourthLayout03, fourthRight);

        /*
        FIFTH ROW OF CONTAINERS
         */
        LinearLayout fifthLayout01 = new LinearLayout(this);
        GridLayout.LayoutParams fifthLeft = new GridLayout.LayoutParams(row5, col1);
        fifthLeft.width = thirdScreenWidth;
        fifthLeft.height = quarterScreenHeight / 2;
        fifthLayout01.setLayoutParams(fifthLeft);
        fifthLayout01.setOrientation(LinearLayout.VERTICAL);
        fifthLayout01.setGravity(Gravity.END);
        //fifthLayout01.setPadding(16, halfPadding, 0, halfPadding);
        fifthLayout01.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        fifthLayout01.setOnDragListener(this);
        gridLayout.addView(fifthLayout01, fifthLeft);

        LinearLayout fifthLayout02 = new LinearLayout(this);
        GridLayout.LayoutParams fifthMiddle = new GridLayout.LayoutParams(row5, col2);
        fifthMiddle.width = thirdScreenWidth;
        fifthMiddle.height = quarterScreenHeight / 2;
        fifthLayout02.setLayoutParams(fifthMiddle);
        fifthLayout02.setOrientation(LinearLayout.VERTICAL);
        fifthLayout02.setGravity(Gravity.CENTER);
        //fifthLayout02.setPadding(16, halfPadding, 16, halfPadding);
        fifthLayout02.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        fifthLayout02.setOnDragListener(this);
        gridLayout.addView(fifthLayout02, fifthMiddle);

        LinearLayout fifthLayout03 = new LinearLayout(this);
        GridLayout.LayoutParams fifthRight = new GridLayout.LayoutParams(row5, col3);
        fifthRight.width = thirdScreenWidth;
        fifthRight.height = quarterScreenHeight / 2;
        fifthLayout03.setLayoutParams(fifthRight);
        fifthLayout03.setOrientation(LinearLayout.VERTICAL);
        fifthLayout03.setGravity(Gravity.START);
        //fifthLayout03.setPadding(0, halfPadding, 16, halfPadding);
        fifthLayout03.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        fifthLayout03.setOnDragListener(this);
        gridLayout.addView(fifthLayout03, fifthRight);

        /*
        SIXTH ROW OF CONTAINERS
         */
        sixthLayout01 = new LinearLayout(this);
        GridLayout.LayoutParams sixthLeft = new GridLayout.LayoutParams(row6, col1);
        sixthLeft.width = thirdScreenWidth;
        sixthLeft.height = quarterScreenHeight / 2;
        sixthLayout01.setLayoutParams(sixthLeft);
        sixthLayout01.setOrientation(LinearLayout.VERTICAL);
        sixthLayout01.setGravity(Gravity.CENTER);
        //sixthLayout01.setPadding(16, padding * 2, 0, halfPadding);
        sixthLayout01.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        sixthLayout01.setOnDragListener(this);
        gridLayout.addView(sixthLayout01, sixthLeft);

        LinearLayout sixthLayout02 = new LinearLayout(this);
        GridLayout.LayoutParams sixthMiddle = new GridLayout.LayoutParams(row6, col2);
        sixthMiddle.width = thirdScreenWidth;
        sixthMiddle.height = quarterScreenHeight / 2;
        sixthLayout02.setLayoutParams(sixthMiddle);
        sixthLayout02.setOrientation(LinearLayout.VERTICAL);
        sixthLayout02.setGravity(Gravity.CENTER);
        //sixthLayout02.setPadding(16, padding * 2, 0, halfPadding);
        sixthLayout02.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        sixthLayout02.setOnDragListener(this);
        gridLayout.addView(sixthLayout02, sixthMiddle);

        sixthLayout03 = new LinearLayout(this);
        GridLayout.LayoutParams sixthRight = new GridLayout.LayoutParams(row6, col3);
        sixthRight.width = thirdScreenWidth;
        sixthRight.height = quarterScreenHeight / 2;
        sixthLayout03.setLayoutParams(sixthRight);
        sixthLayout03.setOrientation(LinearLayout.VERTICAL);
        sixthLayout03.setGravity(Gravity.CENTER);
        //sixthLayout03.setPadding(0, padding * 2, 16, halfPadding);
        sixthLayout03.setBackgroundColor(getResources().getColor(R.color.alpha_black02));
        sixthLayout03.setOnDragListener(this);
        gridLayout.addView(sixthLayout03, sixthRight);

        /*
        BOTTOM MOST ROW CONTAINER WHICH INITIALLY HOLDS THE ROCKET IMAGES.
         */
        LinearLayout bottomLayout = new LinearLayout(this);
        GridLayout.LayoutParams bottom = new GridLayout.LayoutParams(row7, col3Span);
        bottom.width = screenWidth;
        bottom.height = quarterScreenHeight;
        bottomLayout.setLayoutParams(bottom);
        bottomLayout.setOrientation(LinearLayout.HORIZONTAL);
        bottomLayout.setGravity(Gravity.CENTER);
        bottomLayout.setPadding(pixels, pixels, pixels, padding);
        bottomLayout.setBackgroundColor(getResources().getColor(R.color.alpha_BGC2));
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

        ColorChanger colorChanger = new ColorChanger(this);
        colorChanger.setTaskStatusCallBack(new ColorChanger.TaskStatusCallBack() {
            @Override
            public void onProgressUpdate(int color, int colorSwitch) {
                if (colorSwitch % 2 == 0) {
                    if (colorSwitch == 2) {
                        gridLayout.getChildAt(1).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(4).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(10).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(13).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(3).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(6).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(12).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(15).setBackgroundColor(baseColor);
                    } else if (colorSwitch == 4) {
                        gridLayout.getChildAt(2).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(7).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(9).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(14).setBackgroundColor(baseColor);
                    } else if (colorSwitch == 6) {
                        //gridLayout.getChildAt(5).setBackgroundColor(baseColor);
                        gridLayout.getChildAt(8).setBackgroundColor(baseColor);
                        //gridLayout.getChildAt(11).setBackgroundColor(baseColor);
                    }
                } else {
                    if (colorSwitch == 1) {
                        gridLayout.getChildAt(1).setBackgroundColor(color);
                        gridLayout.getChildAt(4).setBackgroundColor(color);
                        gridLayout.getChildAt(10).setBackgroundColor(color);
                        gridLayout.getChildAt(13).setBackgroundColor(color);
                        gridLayout.getChildAt(3).setBackgroundColor(color);
                        gridLayout.getChildAt(6).setBackgroundColor(color);
                        gridLayout.getChildAt(12).setBackgroundColor(color);
                        gridLayout.getChildAt(15).setBackgroundColor(color);
                    } else if (colorSwitch == 3) {
                        gridLayout.getChildAt(2).setBackgroundColor(color);
                        gridLayout.getChildAt(7).setBackgroundColor(color);
                        gridLayout.getChildAt(9).setBackgroundColor(color);
                        gridLayout.getChildAt(14).setBackgroundColor(color);
                    } else if (colorSwitch == 5) {
                        //gridLayout.getChildAt(5).setBackgroundColor(color);
                        gridLayout.getChildAt(8).setBackgroundColor(color);
                        //gridLayout.getChildAt(11).setBackgroundColor(color);
                    }
                }
            }
        });
        colorChanger.execute();
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

    public void makeAlert() {
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
