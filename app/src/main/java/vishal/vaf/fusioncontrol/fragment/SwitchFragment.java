package vishal.vaf.fusioncontrol.fragment;


import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
    SharedPreferences sharedPreferences;

    public SwitchFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.gesture_switches);

        double_tap = findPreference("double_tap");
        double_tap.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(double_tap, "double_click", "double_click_package");
                return false;
            }
        });
        swipe_up = findPreference("swipe_up");
        swipe_up.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(swipe_up, "up", "up_package");
                return false;
            }
        });
        swipe_down = findPreference("swipe_down");
        swipe_down.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(swipe_down, "down", "down_package");
                return false;
            }
        });
        swipe_right = findPreference("swipe_right");
        swipe_right.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(swipe_right, "right", "right_package");
                return false;
            }
        });
        swipe_left = findPreference("swipe_left");
        swipe_left.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(swipe_left, "left", "left_package");
                return false;
            }
        });
        draw_e = findPreference("draw_e");
        draw_e.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(draw_e, "e", "e_package");
                return false;
            }
        });
        draw_o = findPreference("draw_o");
        draw_o.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(draw_o, "o", "o_package");
                return false;
            }
        });
        draw_m = findPreference("draw_m");
        draw_m.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(draw_m, "m", "m_package");
                return false;
            }
        });
        draw_c = findPreference("draw_c");
        draw_c.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(draw_c, "c", "c_package");
                return false;
            }
        });
        draw_w = findPreference("draw_w");
        draw_w.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onCreateDialog(draw_w, "w", "w_package");
                return false;
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        double_tap.setSummary(sharedPreferences.getString("double_click_package", null));
        swipe_up.setSummary(sharedPreferences.getString("up_package",null));
        swipe_down.setSummary(sharedPreferences.getString("down_package",null));
        swipe_right.setSummary(sharedPreferences.getString("right_package",null));
        swipe_left.setSummary(sharedPreferences.getString("left_package",null));
        draw_e.setSummary(sharedPreferences.getString("e_package",null));
        draw_o.setSummary(sharedPreferences.getString("o_package",null));
        draw_m.setSummary(sharedPreferences.getString("m_package",null));
        draw_c.setSummary(sharedPreferences.getString("c_package",null));
        draw_w.setSummary(sharedPreferences.getString("w_package",null));

        mPackageManager = getActivity().getPackageManager();
        mPackageAdapter = new PackageListAdapter(getActivity());
    }

    public void onCreateDialog(final Preference preference, final String preferenceString, final String packageName) {
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

                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.remove(preferenceString);
                        edit.remove(packageName);
                        edit.putString(preferenceString, info.packageName).apply();
                        edit.putString(packageName, info.title.toString());
                        edit.apply();
                        checkUtils = new CheckUtils();
                        checkUtils.setGesture(preferenceString, true);
                        unlockScreen();
                        preference.setSummary(info.title);
                        Log.d("Fusion", info.title.toString());
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
