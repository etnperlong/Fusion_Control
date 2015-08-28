package vishal.vaf.fusioncontrol.services;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;

import vishal.vaf.fusioncontrol.LaunchActivity;
import vishal.vaf.fusioncontrol.checkutils.CheckUtils;

public class ScreenCheckService extends Service {

    BroadcastReceiver mReceiver;
    CheckUtils checkUtils = new CheckUtils();
    PackageManager packageManager;
    String yo;

    public ScreenCheckService() {
    }

    public class ScreenReceiver extends BroadcastReceiver {

        public ScreenReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("Fusion", "Screen is on");

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
            {
                String resp = checkUtils.getResponse();
                packageManager = context.getPackageManager();

                if (resp.contains("double click"))
                {
                    yo = sharedPreferences.getString("double_click", null);
                    if (yo != null ) {
                        launchActivity(context);
                    }
                } else if (resp.contains("Slide(0xAA)"))
                {
                    yo = sharedPreferences.getString("right", null);
                    if (yo != null ) {
                        launchActivity(context);
                    }
                } else if (resp.contains("Slide(0xBB"))
                {
                    yo = sharedPreferences.getString("left", null);
                    if (yo != null ) {
                        launchActivity(context);
                    }
                } else if (resp.contains("Slide(0xBA)"))
                {
                    yo = sharedPreferences.getString("up", null);
                    if (yo != null ) {
                        launchActivity(context);
                    }
                } else if (resp.contains("Slide(0x77)"))
                {
                    yo = sharedPreferences.getString("w", null);
                    if (yo != null ) {
                        launchActivity(context);
                    }
                } else if (resp.contains("Slide(0xAB)"))
                {
                    yo = sharedPreferences.getString("down", null);
                    if (yo != null ) {
                        launchActivity(context);
                    }
                } else if (resp.contains("Slide(0x63)"))
                {
                    yo = sharedPreferences.getString("c", null);
                    if (yo != null ) {
                        launchActivity(context);
                    }
                } else if (resp.contains("Slide(0x65)"))
                {
                    yo = sharedPreferences.getString("e", null);
                    if (yo != null ) {
                        launchActivity(context);
                    }
                } else if (resp.contains("Slide(0x6D)"))
                {
                    yo = sharedPreferences.getString("m", null);
                    if (yo != null ) {
                        launchActivity(context);
                    }
                } else if (resp.contains("Slide(0x6F)"))
                {
                    yo = sharedPreferences.getString("o", null);
                    if (yo != null ) {
                        launchActivity(context);
                    }
                }
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    public void launchActivity(Context context) {
        Intent intent2 = new Intent(context, LaunchActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent2);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
