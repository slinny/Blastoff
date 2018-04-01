package productions.darthplagueis.capstone.util;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import static productions.darthplagueis.capstone.util.Constants.MDCOLOR_ARRAY;
import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getMaterialDesignColor;

/**
 * Uses an AsyncTask to create a 3, 2, 1 flashing pattern on the screen
 * in GameActivity's grid layout. Uses an interface to communicate with the
 * activity.
 */
public class FlashPattern extends AsyncTask<Void, Integer, Void> {

    private WeakReference<Context> contextRef;

    private TaskStatusCallBack taskStatusCallBack = null;

    public FlashPattern(Context context) {
        contextRef = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            int x = 1;
            while (x < 11) {
                if (contextRef.get() != null) {
                    if (x % 2 == 0) {
                        Thread.sleep(1000L);
                    } else {
                        Thread.sleep(500L);
                    }
                    publishProgress(getMaterialDesignColor(contextRef.get(), MDCOLOR_ARRAY), x);
                    x++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        taskStatusCallBack.onProgressUpdate(values[0], values[1]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        taskStatusCallBack.onPostExecute();
    }

    public void setTaskStatusCallBack(TaskStatusCallBack taskStatusCallBack) {
        this.taskStatusCallBack = taskStatusCallBack;
    }

    public interface TaskStatusCallBack {
        void onProgressUpdate(int color, int colorSwitch);

        void onPostExecute();
    }
}