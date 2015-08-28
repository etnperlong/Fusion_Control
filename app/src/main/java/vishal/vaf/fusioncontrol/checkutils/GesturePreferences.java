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

package vishal.vaf.fusioncontrol.checkutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by vishal on 15/3/15.
 */
public class GesturePreferences extends ActionBarActivity{

    public SharedPreferences setOnBootSettings;

    public boolean isDoubleTapEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("double_click_boot", false);
    }

    public boolean isSwipeUpEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("up_boot", false);
    }

    public boolean isSwipeDownEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("down_boot", false);
    }

    public boolean isSwipeRightEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("right_boot", false);
    }

    public boolean isSwipeLeftEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("left_boot", false);
    }

    public boolean isDraw_eEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("e_boot", false);
    }

    public boolean isDraw_oEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("o_boot", false);
    }

    public boolean isDraw_mEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("m_boot", false);
    }

    public boolean isDraw_wEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("w_boot", false);
    }

    public boolean isDraw_cEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("c_boot", false);
    }

    public boolean isGestureEnabled(Context context) {
        setOnBootSettings = PreferenceManager.getDefaultSharedPreferences(context);
        return setOnBootSettings.getBoolean("gesture_enable", false);
    }
}
