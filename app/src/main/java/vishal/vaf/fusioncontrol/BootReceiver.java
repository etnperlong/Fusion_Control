/*
 * Copyright (C) 2015 Vishal Dubey (vishal_android freak) (yzvishal.vd@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vishal.vaf.fusioncontrol;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Switch;

import vishal.vaf.fusioncontrol.checkutils.CheckUtils;
import vishal.vaf.fusioncontrol.checkutils.GesturePreferences;
import vishal.vaf.fusioncontrol.MainActivity;

/**
 * Created by vishal on 15/3/15.
 */
public class BootReceiver extends BroadcastReceiver {

    private String tag = "Fusion";

    GesturePreferences main = new GesturePreferences();
    MainActivity mainActivity = new MainActivity();
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
            if(main.isUnlockChecked(context))
            {
                Intent i = new Intent(context, DisableKeyguard.class);
                context.startService(i);
            }
        }
    }
}
