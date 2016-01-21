package polytech.pfe_ndar.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public final class ScreenTools {
    private static int screenDpWidth;
    private static int screenDpHeight;

    public static void init(Activity activity){
        Context context = activity.getApplicationContext();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        screenDpHeight = (int)(displayMetrics.heightPixels / displayMetrics.density);
        screenDpWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);

    }


    public static int getScreenDpWidth(){
        return screenDpWidth;
    }
    public static int getScreenDpHeight(){
        return screenDpHeight;
    }

}
