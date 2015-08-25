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

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import java.util.HashMap;
import java.util.Map;

import vishal.vaf.fusioncontrol.adapters.PackageListAdapter;
import vishal.vaf.fusioncontrol.checkutils.CheckUtils;
import vishal.vaf.fusioncontrol.fragment.AboutFragment;
import vishal.vaf.fusioncontrol.fragment.SwitchFragment;
import vishal.vaf.fusioncontrol.services.ScreenCheckService;


public class MainActivity extends ActionBarActivity {

    CheckUtils check = new CheckUtils();
    SwitchFragment switchFragment;
    AboutFragment aboutFragment;
    FragmentManager fragmentManager;
    KeyguardManager manager;
    KeyguardManager.KeyguardLock lock;
    SharedPreferences setOnBootSettings;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ActionBar actionBar;
    ProgressDialog progress;

    private String[] navDrawList;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mTitle;
    public static final String SOB_PREFS_NAME = "SetOnBoot";
    private PackageManager mPackageManager;
    private PackageListAdapter mPackageAdapter;
    private String mPackageList;
    private Map<String, Package> mPackages;

    private static final int DIALOG_APPS = 0;
    private static String CONTROL_PATH = "/sys/devices/virtual/touchscreen/touchscreen_dev/gesture_ctrl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateCardView();

        setActionBarOptions();

        populateNavDrawList();

        startService(this);

        if(!check.isDeviceSupported())
        {
            AlertDialog.Builder support = new AlertDialog.Builder(this);
            support.setTitle("Unsupported Device !! ");
            support.setMessage("This app is supported only on YU YUREKA!");
            support.setCancelable(false);
            support.setNegativeButton(
                    "Exit",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }
            );
            support.show();
        }

            if(!check.hasRoot())
            {
                AlertDialog.Builder noRootAlert = new AlertDialog.Builder(this);
                noRootAlert.setTitle("NO ROOT !! ");
                noRootAlert.setMessage("Your phone is not rooted or you failed to grant Super User access to the app");
                noRootAlert.setCancelable(false);
                noRootAlert.setNegativeButton(
                        "Exit",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }
                );
                noRootAlert.show();
            }

        mPackageManager = getPackageManager();
        mPackageAdapter = new PackageListAdapter(this);

        mPackages = new HashMap<String, Package>();
    }

    public void populateCardView()
    {
        fragmentManager = getFragmentManager();
        switchFragment = new SwitchFragment();
        fragmentManager.beginTransaction()
                .add(R.id.switch_card_view, switchFragment)
                .commit();
    }

    public void startService(Context context)
    {
        Log.d("Fusion", "Started service");
        Intent i = new Intent(context, ScreenCheckService.class);
        context.startService(i);
    }

    public void populateNavDrawList()
    {
        navDrawList = getResources().getStringArray(R.array.nav_draw_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                null,
                R.string.app_name,
                R.string.app_name) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Fusion Control");
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list, navDrawList));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        switch (position)
        {
            case 0:
            {
                fragmentManager = getFragmentManager();
                switchFragment = new SwitchFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.switch_card_view, switchFragment)
                        .commit();
                mDrawerList.setItemChecked(position, true);
                setTitle("Fusion Control");
                mTitle = "Fusion Control";
                mDrawerLayout.closeDrawer(mDrawerList);
            }
            break;
            case 1:
            {
                fragmentManager = getFragmentManager();
                aboutFragment = new AboutFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.switch_card_view, aboutFragment)
                        .commit();
                mDrawerList.setItemChecked(position, true);
                setTitle(navDrawList[position]);
                mTitle = navDrawList[position];
                mDrawerLayout.closeDrawer(mDrawerList);
            }
            break;
        }

    }

        @Override
    protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private void setActionBarOptions() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    public void unlockScreen() {
        manager = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
        lock = manager.newKeyguardLock("One");
        lock.disableKeyguard();
    }

    public void onClickDouble(View view)
    {
        boolean state = ((Switch) view).isChecked();
        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, 0);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state)
        {
            check.setGesture("double_click", state);
            editor.putBoolean("double_click", true);
            editor.apply();

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    final ListView list = new ListView(this);
                    list.setAdapter(mPackageAdapter);

                    builder.setTitle("Choose apps");
                    builder.setView(list);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Add empty application definition, the user will be able to edit it later
                            PackageListAdapter.PackageItem info = (PackageListAdapter.PackageItem) parent.getItemAtPosition(position);
                            //addCustomApplicationPref(info.packageName);
                            Log.d("Fusion", info.packageName);
                        }
                    });

                    builder.show();
        }
        else
        {
            check.setGesture("double_click", state);
            editor.putBoolean("double_click", false);
            editor.apply();
        }
    }

    public void onClickSwipeUp(View view)
    {
        boolean state = ((Switch) view).isChecked();

        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, 0);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state)
        {
            check.setGesture("up", state);
            editor.putBoolean("up", true);
            editor.apply();
        }
        else
        {
            check.setGesture("up", state);
            editor.putBoolean("up", false);
            editor.apply();
        }
    }

    public void onClickSwipeDown(View view)
    {
        boolean state = ((Switch) view).isChecked();

        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, 0);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state)
        {
            check.setGesture("down", state);
            editor.putBoolean("down", true);
            editor.apply();
        }
        else
        {
            check.setGesture("down", state);
            editor.putBoolean("down", false);
            editor.apply();
        }
    }

    public void onClickSwipeRight(View view)
    {
        boolean state = ((Switch) view).isChecked();

        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, 0);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state)
        {
            check.setGesture("right", state);
            editor.putBoolean("right", true);
            editor.apply();
        }
        else
        {
            check.setGesture("right", state);
            editor.putBoolean("right", false);
            editor.apply();
        }
    }

    public void onClickSwipeLeft(View view)
    {
        boolean state = ((Switch) view).isChecked();

        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, 0);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state)
        {
            check.setGesture("left", state);
            editor.putBoolean("left", true);
            editor.apply();
        }
        else
        {
            check.setGesture("left", state);
            editor.putBoolean("left", false);
            editor.apply();
        }
    }

    public void onClickDraw_E(View view)
    {
        boolean state = ((Switch) view).isChecked();

        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, 0);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state)
        {
            check.setGesture("e", state);
            editor.putBoolean("e", true);
            editor.apply();
        }
        else
        {
            check.setGesture("e", state);
            editor.putBoolean("e", false);
            editor.apply();
        }
    }

    public void onClickDraw_O(View view)
    {
        boolean state = ((Switch) view).isChecked();

        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, 0);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state)
        {
            check.setGesture("o", state);
            editor.putBoolean("o", true);
            editor.apply();
        }
        else
        {
            check.setGesture("o", state);
            editor.putBoolean("o", false);
            editor.apply();
        }
    }

    public void onClickDraw_W(View view)
    {
        boolean state = ((Switch) view).isChecked();

        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, 0);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state)
        {
            check.setGesture("w", state);
            editor.putBoolean("w", true);
            editor.apply();
        }
        else
        {
            check.setGesture("w", state);
            editor.putBoolean("w", false);
            editor.apply();
        }
    }

    public void onClickDraw_M(View view)
    {
        boolean state = ((Switch) view).isChecked();

        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, 0);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state)
        {
            check.setGesture("m", state);
            editor.putBoolean("m", true);
            editor.apply();
        }
        else
        {
            check.setGesture("m", state);
            editor.putBoolean("m", false);
            editor.apply();
        }
    }

    public void onClickDraw_C(View view) {
        boolean state = ((Switch) view).isChecked();

        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, 0);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state) {
            check.setGesture("c", state);
            editor.putBoolean("c", true);
            editor.apply();
        } else {
            check.setGesture("c", state);
            editor.putBoolean("c", false);
            editor.apply();
        }
    }

    public void onChecked(final View view)
    {
        boolean state = ((Switch) view).isChecked();
        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = setOnBootSettings.edit();

        if (state)
        {
            AlertDialog.Builder support = new AlertDialog.Builder(this);
            support.setTitle("Warning !! ");
            support.setMessage("Switching on Direct Unlock disables pattern or password lock as well.");
            support.setCancelable(false);
            support.setNegativeButton(
                    "Exit",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editor.putBoolean("checked", false);
                            editor.apply();
                            ((Switch) view).setChecked(false);
                        }
                    }
            );
            support.setPositiveButton(
                    "Go Ahead",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unlockScreen();
                            editor.putBoolean("checked", true);
                            editor.apply();
                        }
                    }
            );
            support.create();
            support.show();
        }
        else
        {
            AlertDialog.Builder support = new AlertDialog.Builder(this);
            support.setTitle("Reboot Required !! ");
            support.setMessage("Your device needs to be rebooted to re-enable lockscreen");
            support.setCancelable(false);
            support.setNegativeButton(
                    "Reboot later",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editor.putBoolean("checked", false);
                            editor.apply();
                        }
                    }
            );
            support.setPositiveButton(
                    "Reboot now",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progress = ProgressDialog.show(MainActivity.this, "",
                                    "Rebooting",false);
                            editor.putBoolean("checked", false);
                            editor.apply();
                            check.reboot();
                        }
                    }
            );
            support.create();
            support.show();
        }
    }
}
