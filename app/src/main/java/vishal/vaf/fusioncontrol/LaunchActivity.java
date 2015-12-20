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

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import vishal.vaf.fusioncontrol.checkutils.CheckUtils;

public class LaunchActivity extends AppCompatActivity {

    CheckUtils checkUtils;
    PackageManager packageManager;
    String yo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        checkUtils = new CheckUtils();
        packageManager = this.getPackageManager();

        String resp = checkUtils.getResponse();

        if (resp.contains("Slide(0xcc)")) {
            yo = sharedPreferences.getString("double_click", null);
            if (yo != null) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                        startActivity(intent2);
                        finish();
                    }
                }, 30L);
            }
        } else if (resp.contains("Slide(0xaa)"))
        {
            yo = sharedPreferences.getString("right", null);
            if (yo != null ) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                        startActivity(intent2);
                        finish();
                    }
                }, 30L);
            }
        } else if (resp.contains("Slide(0xbb"))
        {
            yo = sharedPreferences.getString("left", null);
            if (yo != null ) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                        startActivity(intent2);
                        finish();
                    }
                }, 30L);
            }
        } else if (resp.contains("Slide(0xba)"))
        {
            yo = sharedPreferences.getString("up", null);
            if (yo != null ) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                        startActivity(intent2);
                        finish();
                    }
                }, 30L);
            }
        } else if (resp.contains("Slide(0x77)"))
        {
            yo = sharedPreferences.getString("w", null);
            if (yo != null ) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                        startActivity(intent2);
                        finish();
                    }
                }, 30L);
            }
        } else if (resp.contains("Slide(0xab)"))
        {
            yo = sharedPreferences.getString("down", null);
            if (yo != null ) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                        startActivity(intent2);
                        finish();
                    }
                }, 30L);
            }
        } else if (resp.contains("Slide(0x63)"))
        {
            yo = sharedPreferences.getString("c", null);
            if (yo != null ) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                        startActivity(intent2);
                        finish();
                    }
                }, 30L);
            }
        } else if (resp.contains("Slide(0x65)"))
        {
            yo = sharedPreferences.getString("e", null);
            if (yo != null ) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                        startActivity(intent2);
                        finish();
                    }
                }, 30L);
            }
        } else if (resp.contains("Slide(0x6d)"))
        {
            yo = sharedPreferences.getString("m", null);
            if (yo != null ) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                        startActivity(intent2);
                        finish();
                    }
                }, 30L);
            }
        } else if (resp.contains("Slide(0x6f)"))
        {
            yo = sharedPreferences.getString("o", null);
            if (yo != null ) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                        startActivity(intent2);
                        finish();
                    }
                }, 30L);
            }
        }
    }
}
