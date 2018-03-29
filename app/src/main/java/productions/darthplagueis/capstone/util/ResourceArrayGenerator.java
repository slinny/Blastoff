package productions.darthplagueis.capstone.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

/**
 * Generates a random material design color from within a res.values array
 * of colors with similar alphas. Generates random strings from the same res.values array xml.
 */
public class ResourceArrayGenerator {

    public static int getMaterialDesignColor(Context context, String typeColor) {
        int returnColor = Color.BLACK;
        int arrayId = context.getResources().getIdentifier("mdcolor_" + typeColor,
                "array", context.getPackageName());
        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.BLACK);
            colors.recycle();
        }
        return returnColor;
    }

    public static String getRandomText(Context context, String typeString) {
        String returnString = "";
        int arrayId = context.getResources().getIdentifier("stringtext_" + typeString,
                "array", context.getPackageName());
        if (arrayId != 0) {
            TypedArray strings = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * strings.length());
            returnString = strings.getString(index);
            strings.recycle();
        }
        return returnString;
    }
}
