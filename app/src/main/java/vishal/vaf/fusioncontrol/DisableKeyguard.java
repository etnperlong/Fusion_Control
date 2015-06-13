package vishal.vaf.fusioncontrol;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class DisableKeyguard extends Service {

    BroadcastReceiver mReceiver = null;

    KeyguardManager keyguardManager;
    @SuppressWarnings("deprecation")
    KeyguardManager.KeyguardLock keylock;

    public DisableKeyguard() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BOOT_COMPLETED);
        mReceiver = new BootReceiver();
        registerReceiver(mReceiver, filter);
    }

    @SuppressWarnings("deprecation")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            keylock = keyguardManager.newKeyguardLock("myLock");
            keylock.disableKeyguard();

        return 1;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onDestroy() {

        if(mReceiver!=null) {
            unregisterReceiver(mReceiver);
        }
    }
}
