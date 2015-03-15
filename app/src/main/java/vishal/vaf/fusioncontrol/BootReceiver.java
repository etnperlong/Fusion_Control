package vishal.vaf.fusioncontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import vishal.vaf.fusioncontrol.checkutils.CheckUtils;
import vishal.vaf.fusioncontrol.checkutils.GesturePreferences;

/**
 * Created by vishal on 15/3/15.
 */
public class BootReceiver extends BroadcastReceiver {

    private String tag = "Fusion";

    GesturePreferences main = new GesturePreferences();
    CheckUtils cu = new CheckUtils();

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
        {

            cu.hasRoot();

            Log.d(tag, "Boot complete. Let's setup where we left");

            if(main.isDoubleTapEnabled(context))
            {
                cu.setGesture("double_click", true);
            }
            if(main.isSwipeDownEnabled(context))
            {
                cu.setGesture("down", true);
            }
            if(main.isSwipeUpEnabled(context))
            {
                cu.setGesture("up", true);
            }
            if(main.isSwipeRightEnabled(context))
            {
                cu.setGesture("right", true);
            }
            if(main.isSwipeLeftEnabled(context))
            {
                cu.setGesture("left", true);
            }
            if(main.isDraw_eEnabled(context))
            {
                cu.setGesture("e", true);
            }
            if(main.isDraw_oEnabled(context))
            {
                cu.setGesture("o", true);
            }
            if(main.isDraw_mEnabled(context))
            {
                cu.setGesture("m", true);
            }
            if(main.isDraw_wEnabled(context))
            {
                cu.setGesture("w", true);
            }
            if(main.isDraw_cEnabled(context))
            {
                cu.setGesture("c", true);
            }
        }
    }
}
