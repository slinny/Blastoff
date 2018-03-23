package productions.darthplagueis.capstone;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Build;
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
import android.widget.Toast;

/**
 * Experimental game activity that can drag and drop through
 * out a grid layout. Drag and drop is functional.
 */
public class TestGameActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {

    private final String TAG = TestGameActivity.class.getSimpleName();

    private ImageView imageView1;
    private ImageView imageView2;

    private final String IMAGE_VIEW_1 = "IMAGE_VIEW1";
    private final String IMAGE_VIEW_2 = "IMAGE_VIEW2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_game);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        int halfScreenWidth = (int)(screenWidth * 0.5);
        int thirdScreenWidth = getResources().getDisplayMetrics().widthPixels / 3;
        int quarterScreenWidth = (int)(halfScreenWidth * 0.5);

        GridLayout.Spec row1 = GridLayout.spec(0, 2);
        GridLayout.Spec row2 = GridLayout.spec(2, 4);
        GridLayout.Spec row3 = GridLayout.spec(6, 2);
        GridLayout.Spec row4 = GridLayout.spec(8, 4);

        GridLayout.Spec col1 = GridLayout.spec(0);
        GridLayout.Spec col2 = GridLayout.spec(1);
        GridLayout.Spec col3 = GridLayout.spec(2);
        GridLayout.Spec col3Span = GridLayout.spec(0, 3);

        GridLayout gridLayout = findViewById(R.id.grid_layout);
        gridLayout.setColumnCount(3);
        gridLayout.setRowCount(20);

        LinearLayout topLayout = new LinearLayout(this);
        GridLayout.LayoutParams top = new GridLayout.LayoutParams(row1, col3Span);
        top.width = screenWidth;
        top.height = quarterScreenWidth * 2;
        topLayout.setLayoutParams(top);
        topLayout.setOrientation(LinearLayout.HORIZONTAL);
        topLayout.setGravity(Gravity.CENTER);
        topLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        topLayout.setOnDragListener(this);
        gridLayout.addView(topLayout, top);

        LinearLayout colLayout1 = new LinearLayout(this);
        GridLayout.LayoutParams colLeft = new GridLayout.LayoutParams(row2, col1);
        colLeft.width = thirdScreenWidth;
        colLeft.height = quarterScreenWidth;
        colLayout1.setLayoutParams(colLeft);
        colLayout1.setOrientation(LinearLayout.VERTICAL);
        //colLayout1.setGravity(Gravity.CENTER);
        colLayout1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        colLayout1.setOnDragListener(this);
        gridLayout.addView(colLayout1, colLeft);

        LinearLayout colLayout2 = new LinearLayout(this);
        GridLayout.LayoutParams colMiddle = new GridLayout.LayoutParams(row2, col2);
        colMiddle.width = thirdScreenWidth;
        colMiddle.height = quarterScreenWidth;
        colLayout2.setLayoutParams(colMiddle);
        colLayout2.setOrientation(LinearLayout.VERTICAL);
        //colLayout2.setGravity(Gravity.CENTER);
        colLayout2.setBackgroundColor(getResources().getColor(R.color.colorBGC0));
        colLayout2.setOnDragListener(this);
        gridLayout.addView(colLayout2, colMiddle);

        LinearLayout colLayout3 = new LinearLayout(this);
        GridLayout.LayoutParams colRight = new GridLayout.LayoutParams(row2, col3);
        colRight.width = thirdScreenWidth;
        colRight.height = quarterScreenWidth;
        colLayout3.setLayoutParams(colRight);
        colLayout3.setOrientation(LinearLayout.VERTICAL);
        //colLayout3.setGravity(Gravity.CENTER);
        colLayout3.setBackgroundColor(getResources().getColor(R.color.colorBGC1));
        colLayout3.setOnDragListener(this);
        gridLayout.addView(colLayout3, colRight);

        LinearLayout middleLayout = new LinearLayout(this);
        GridLayout.LayoutParams middle = new GridLayout.LayoutParams(row3, col3Span);
        middle.width = screenWidth;
        middle.height = quarterScreenWidth * 2;
        middleLayout.setLayoutParams(middle);
        middleLayout.setOrientation(LinearLayout.HORIZONTAL);
        middleLayout.setGravity(Gravity.CENTER);
        middleLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        middleLayout.setOnDragListener(this);
        gridLayout.addView(middleLayout, middle);

        LinearLayout bottomLayout = new LinearLayout(this);
        GridLayout.LayoutParams bottom = new GridLayout.LayoutParams(row4, col3Span);
        bottom.width = screenWidth;
        bottom.height = quarterScreenWidth * 2;
        bottomLayout.setLayoutParams(bottom);
        bottomLayout.setOrientation(LinearLayout.HORIZONTAL);
        bottomLayout.setGravity(Gravity.CENTER);
        bottomLayout.setBackgroundColor(getResources().getColor(R.color.colorBGC2));
        bottomLayout.setOnDragListener(this);

        imageView1 = new ImageView(this);
        imageView1.setTag(IMAGE_VIEW_1);
        imageView1.setImageResource(R.drawable.rocket1);
        imageView1.setOnLongClickListener(this);

        imageView2 = new ImageView(this);
        imageView2.setTag(IMAGE_VIEW_2);
        imageView2.setImageResource(R.drawable.helmet);
        imageView2.setOnLongClickListener(this);

        bottomLayout.addView(imageView1);
        bottomLayout.addView(imageView2);

        gridLayout.addView(bottomLayout, bottom);
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
                    v.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
                    v.invalidate();
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
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
                Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                Log.e(TAG, "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }
}
