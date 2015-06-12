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
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.PowerManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import vishal.vaf.fusioncontrol.checkutils.CheckUtils;
import vishal.vaf.fusioncontrol.fragment.SwitchFragment;


public class MainActivity extends ActionBarActivity {

    CheckUtils check = new CheckUtils();
    SwitchFragment switchFragment;
    FragmentManager fragmentManager;
    SharedPreferences setOnBootSettings;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ActionBar actionBar;

    private String[] navDrawList;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public static final String SOB_PREFS_NAME = "SetOnBoot";

    private static String CONTROL_PATH = "/sys/devices/virtual/touchscreen/touchscreen_dev/gesture_ctrl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateCardView();

        setActionBarOptions();

        populateNavDrawList();

        Switch sw1 = (Switch) findViewById(R.id.switch1);
        Switch sw2 = (Switch) findViewById(R.id.switch2);
        Switch sw3 = (Switch) findViewById(R.id.switch3);
        Switch sw4 = (Switch) findViewById(R.id.switch4);
        Switch sw5 = (Switch) findViewById(R.id.switch5);
        Switch sw6 = (Switch) findViewById(R.id.switch6);
        Switch sw7 = (Switch) findViewById(R.id.switch7);
        Switch sw8 = (Switch) findViewById(R.id.switch8);
        Switch sw9 = (Switch) findViewById(R.id.switch9);
        Switch sw10 = (Switch) findViewById(R.id.switch10);

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

        setOnBootSettings = getSharedPreferences(SOB_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setOnBootSettings.edit();

    }

    public void populateCardView()
    {
        fragmentManager = getFragmentManager();
        switchFragment = new SwitchFragment();
        fragmentManager.beginTransaction()
                .add(R.id.switch_card_view, switchFragment)
                .commit();
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
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
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
        startActivity(new Intent(this, AboutActivity.class));
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


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }*/
}
