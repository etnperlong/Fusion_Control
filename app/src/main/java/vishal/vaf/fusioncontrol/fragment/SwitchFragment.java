package vishal.vaf.fusioncontrol.fragment;


import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import vishal.vaf.fusioncontrol.R;
import vishal.vaf.fusioncontrol.adapters.PackageListAdapter;
import vishal.vaf.fusioncontrol.checkutils.CheckUtils;


public class SwitchFragment extends PreferenceFragment {

    private Preference double_tap;
    private Preference swipe_up;
    private Preference swipe_down;
    private Preference swipe_right;
    private Preference swipe_left;
    private Preference draw_e;
    private Preference draw_o;
    private Preference draw_m;
    private Preference draw_c;
    private Preference draw_w;

    private PackageListAdapter mPackageAdapter;
    private PackageManager mPackageManager;

    KeyguardManager manager;
    KeyguardManager.KeyguardLock lock;

    CheckUtils checkUtils;

    public SwitchFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.gesture_switches);

        PreferenceScreen prefSet = getPreferenceScreen();

        double_tap = findPreference("double_tap");
        double_tap.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(double_tap, "double_click");
                return false;
            }
        });
        swipe_up = findPreference("swipe_up");
        swipe_up.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(swipe_up, "up");
                return false;
            }
        });
        swipe_down = findPreference("swipe_down");
        swipe_down.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(swipe_down, "down");
                return false;
            }
        });
        swipe_right = findPreference("swipe_right");
        swipe_right.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(swipe_right, "right");
                return false;
            }
        });
        swipe_left = findPreference("swipe_left");
        swipe_left.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(swipe_left, "left");
                return false;
            }
        });
        draw_e = findPreference("draw_e");
        draw_e.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(draw_e, "e");
                return false;
            }
        });
        draw_o = findPreference("draw_o");
        draw_o.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(draw_o, "o");
                return false;
            }
        });
        draw_m = findPreference("draw_m");
        draw_m.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(draw_m, "m");
                return false;
            }
        });
        draw_c = findPreference("draw_c");
        draw_c.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(draw_c, "c");
                return false;
            }
        });
        draw_w = findPreference("draw_w");
        draw_w.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(draw_w, "w");
                return false;
            }
        });

        // Get launch-able applications

        mPackageManager = getActivity().getPackageManager();
        mPackageAdapter = new PackageListAdapter(getActivity());
    }

    public void onCreateDialog(final Preference preference, final String preferenceString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final Dialog dialog;
                final ListView list = new ListView(getActivity());
                list.setAdapter(mPackageAdapter);

                builder.setTitle("Choose App");
                builder.setView(list);
                dialog = builder.create();

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Add empty application definition, the user will be able to edit it later
                        PackageListAdapter.PackageItem info = (PackageListAdapter.PackageItem) parent.getItemAtPosition(position);
                        //addCustomApplicationPref(info.packageName);
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.remove(preferenceString);
                        edit.putString(preferenceString, info.packageName).apply();
                        edit.apply();
                        checkUtils = new CheckUtils();
                        checkUtils.setGesture(preferenceString, true);
                        unlockScreen();
                        preference.setSummary(info.title);
                        preference.setIcon(info.icon);
                        dialog.cancel();
                    }
                });
        dialog.show();
    }

    public void unlockScreen() {
        manager = (KeyguardManager) getActivity().getSystemService(Context.KEYGUARD_SERVICE);
        lock = manager.newKeyguardLock("One");
        lock.disableKeyguard();
    }
}
