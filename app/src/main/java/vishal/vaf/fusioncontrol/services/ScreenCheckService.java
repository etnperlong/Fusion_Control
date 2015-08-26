package vishal.vaf.fusioncontrol.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.IBinder;
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

            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
            {
                String resp = checkUtils.getResponse();
                packageManager = context.getPackageManager();
                if (resp.contains("double click"))
                {
                    Intent intent2 = packageManager.getLaunchIntentForPackage("org.cyanogenmod.audiofx");
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
