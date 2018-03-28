package productions.darthplagueis.capstone.util;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import static productions.darthplagueis.capstone.util.ResourceArrayGenerator.getMaterialDesignColor;

/**
 *
 */
public class ColorChanger extends AsyncTask<Void, Integer, Void> {

    private WeakReference<Context> contextRef;

    private TaskStatusCallBack taskStatusCallBack = null;

    public ColorChanger(Context context) {
        contextRef = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            int x = 1;
            while (x >= 0) {
                if (contextRef.get() != null) {
                    if (x == 7) {
                        x = 1;
                    }
                    Thread.sleep(350L);
                    publishProgress(getMaterialDesignColor(contextRef.get(), "400"), x);
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

    public void setTaskStatusCallBack(TaskStatusCallBack taskStatusCallBack) {
        this.taskStatusCallBack = taskStatusCallBack;
    }

    public interface TaskStatusCallBack {
        void onProgressUpdate(int color, int colorSwitch);
    }
}