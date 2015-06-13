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

    public boolean isUnlockChecked(Context context) {
        setOnBootSettings = context.getSharedPreferences(SOB_PREFS_NAME, 0);
        return setOnBootSettings.getBoolean("checked", false);
    }
}
