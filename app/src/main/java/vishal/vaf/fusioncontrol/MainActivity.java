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
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import vishal.vaf.fusioncontrol.checkutils.CheckUtils;
import vishal.vaf.fusioncontrol.fragment.AboutFragment;
import vishal.vaf.fusioncontrol.fragment.SwitchFragment;


public class MainActivity extends AppCompatActivity {

    CheckUtils check = new CheckUtils();
    SwitchFragment switchFragment;
    AboutFragment aboutFragment;
    FragmentManager fragmentManager;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ActionBar actionBar;

    private String[] navDrawList;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        populateCardView();

        setActionBarOptions();

        populateNavDrawList();

        if(!check.isDeviceSupported())
        {
            AlertDialog.Builder support = new AlertDialog.Builder(this);
            support.setTitle("不支持的设备！ ");
            support.setMessage("此应用只适用于 酷派大神F2 全高清/全网通");
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
                noRootAlert.setTitle("无法获取ROOT权限");
                noRootAlert.setMessage("请确保你已成功获取ROOT权限");
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
    }

    public void populateCardView()
    {
        fragmentManager = getFragmentManager();
        switchFragment = new SwitchFragment();
        fragmentManager.beginTransaction()
                .add(R.id.switch_card_view, switchFragment)
                .commit();
        setTitle("智能手势");
        mTitle = "智能手势";
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
                setTitle("智能手势");
                mTitle = "智能手势";
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
}
