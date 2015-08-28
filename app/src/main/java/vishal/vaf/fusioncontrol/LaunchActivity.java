package vishal.vaf.fusioncontrol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        if (resp.contains("double click")) {
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
        }
    }
}
