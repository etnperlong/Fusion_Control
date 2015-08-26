package vishal.vaf.fusioncontrol.services;

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

import vishal.vaf.fusioncontrol.checkutils.CheckUtils;

public class ScreenCheckService extends Service {

    BroadcastReceiver mReceiver;
    CheckUtils checkUtils = new CheckUtils();
    PackageManager packageManager;

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
                    String yo = sharedPreferences.getString("double_click", null);
                    Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                    context.startActivity(intent2);
                } else if (resp.contains("Slide(0xAA)"))
                {
                    String yo = sharedPreferences.getString("right", null);
                    Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                    context.startActivity(intent2);
                } else if (resp.contains("Slide(0xBB"))
                {
                    String yo = sharedPreferences.getString("left", null);
                    Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                    context.startActivity(intent2);
                } else if (resp.contains("Slide(0xBA)"))
                {
                    String yo = sharedPreferences.getString("up", null);
                    Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                    context.startActivity(intent2);
                } else if (resp.contains("Slide(0x77)"))
                {
                    String yo = sharedPreferences.getString("w", null);
                    Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                    context.startActivity(intent2);
                } else if (resp.contains("Slide(0xAB)"))
                {
                    String yo = sharedPreferences.getString("down", null);
                    Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                    context.startActivity(intent2);
                } else if (resp.contains("Slide(0x63)"))
                {
                    String yo = sharedPreferences.getString("c", null);
                    Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                    context.startActivity(intent2);
                } else if (resp.contains("Slide(0x65)"))
                {
                    String yo = sharedPreferences.getString("e", null);
                    Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                    context.startActivity(intent2);
                } else if (resp.contains("Slide(0x6D)"))
                {
                    String yo = sharedPreferences.getString("m", null);
                    Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                    context.startActivity(intent2);
                } else if (resp.contains("Slide(0x6F)"))
                {
                    String yo = sharedPreferences.getString("o", null);
                    Intent intent2 = packageManager.getLaunchIntentForPackage(yo);
                    context.startActivity(intent2);
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
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
