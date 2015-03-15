package vishal.vaf.fusioncontrol.checkutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by vishal on 15/3/15.
 */
public class GesturePreferences extends ActionBarActivity{

    public static final String SOB_PREFS_NAME = "SetOnBoot";

    public SharedPreferences setOnBootSettings;

    public boolean isDoubleTapEnabled(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("double_click", false);
    }

    public boolean isSwipeUpEnabled(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("up", false);
    }

    public boolean isSwipeDownEnabled(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("down", false);
    }

    public boolean isSwipeRightEnabled(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("right", false);
    }

    public boolean isSwipeLeftEnabled(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("left", false);
    }

    public boolean isDraw_eEnabled(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("e", false);
    }

    public boolean isDraw_oEnabled(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("o", false);
    }

    public boolean isDraw_mEnabled(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("m", false);
    }

    public boolean isDraw_wEnabled(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("w", false);
    }

    public boolean isDraw_cEnabled(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("c", false);
    }
}
