package vishal.vaf.fusioncontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import vishal.vaf.fusioncontrol.checkutils.CheckUtils;


public class MainActivity extends ActionBarActivity {

    CheckUtils check = new CheckUtils();

    private static String CONTROL_PATH = "/sys/devices/virtual/touchscreen/touchscreen_dev/gesture_ctrl";

    public static int TW_SUPPORT_NONE_SLIDE_WAKEUP = 0x0;
    public static int TW_SUPPORT_UP_SLIDE_WAKEUP = 0x1;
    public static int TW_SUPPORT_DOWN_SLIDE_WAKEUP = 0x2;
    public static int TW_SUPPORT_LEFT_SLIDE_WAKEUP = 0x4;
    public static int TW_SUPPORT_RIGHT_SLIDE_WAKEUP = 0x8;
    public static int TW_SUPPORT_E_SLIDE_WAKEUP = 0x10;
    public static int TW_SUPPORT_O_SLIDE_WAKEUP = 0x20;
    public static int TW_SUPPORT_W_SLIDE_WAKEUP = 0x40;
    public static int TW_SUPPORT_C_SLIDE_WAKEUP = 0x80;
    public static int TW_SUPPORT_M_SLIDE_WAKEUP = 0x100;
    public static int TW_SUPPORT_DOUBLE_CLICK_WAKEUP = 0x200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        else{

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
        }

        long response = Long.decode(check.getResponse());

        final boolean hasDoubleTapEnabled = (response & TW_SUPPORT_DOUBLE_CLICK_WAKEUP) != 0;
        final boolean hasSwipeUpEnabled = (response & TW_SUPPORT_UP_SLIDE_WAKEUP) != 0;
        final boolean hasSwipeDownEnabled = (response & TW_SUPPORT_DOWN_SLIDE_WAKEUP) != 0;
        final boolean hasSwipeLeftEnabled = (response & TW_SUPPORT_LEFT_SLIDE_WAKEUP) != 0;
        final boolean hasSwipeRightEnabled = (response & TW_SUPPORT_RIGHT_SLIDE_WAKEUP) != 0;
        final boolean hasDraw_eEnabled = (response & TW_SUPPORT_E_SLIDE_WAKEUP) != 0;
        final boolean hasDraw_oEnabled = (response & TW_SUPPORT_O_SLIDE_WAKEUP) != 0;
        final boolean hasDraw_wEnabled = (response & TW_SUPPORT_W_SLIDE_WAKEUP) != 0;
        final boolean hasDraw_mEnabled = (response & TW_SUPPORT_M_SLIDE_WAKEUP) != 0;
        final boolean hasDraw_cEnabled = (response & TW_SUPPORT_C_SLIDE_WAKEUP) != 0;

        if(hasDoubleTapEnabled)
        {
            sw1.setChecked(true);
        }
        if(hasSwipeUpEnabled)
        {
            sw2.setChecked(true);
        }
        if(hasSwipeDownEnabled)
        {
            sw3.setChecked(true);
        }
        if(hasSwipeRightEnabled)
        {
            sw4.setChecked(true);
        }
        if(hasSwipeLeftEnabled)
        {
            sw5.setChecked(true);
        }
        if(hasDraw_eEnabled)
        {
            sw6.setChecked(true);
        }
        if(hasDraw_oEnabled)
        {
            sw7.setChecked(true);
        }
        if(hasDraw_wEnabled)
        {
            sw8.setChecked(true);
        }
        if(hasDraw_mEnabled)
        {
            sw9.setChecked(true);
        }
        if(hasDraw_cEnabled)
        {
            sw10.setChecked(true);
        }
    }

    public void onClickDouble(View view)
    {
        boolean state = ((Switch) view).isChecked();

        if (state)
        {
            check.setGesture("double_click", state);
        }
        else
        {
            check.setGesture("double_click", state);
        }
    }

    public void onClickSwipeUp(View view)
    {
        boolean state = ((Switch) view).isChecked();

        if (state)
        {
            check.setGesture("up", state);
        }
        else
        {
            check.setGesture("up", state);
        }
    }

    public void onClickSwipeDown(View view)
    {
        boolean state = ((Switch) view).isChecked();

        if (state)
        {
            check.setGesture("down", state);
        }
        else
        {
            check.setGesture("down", state);
        }
    }

    public void onClickSwipeRight(View view)
    {
        boolean state = ((Switch) view).isChecked();

        if (state)
        {
            check.setGesture("right", state);
        }
        else
        {
            check.setGesture("right", state);
        }
    }

    public void onClickSwipeLeft(View view)
    {
        boolean state = ((Switch) view).isChecked();

        if (state)
        {
            check.setGesture("left", state);
        }
        else
        {
            check.setGesture("left", state);
        }
    }

    public void onClickDraw_E(View view)
    {
        boolean state = ((Switch) view).isChecked();

        if (state)
        {
            check.setGesture("e", state);
        }
        else
        {
            check.setGesture("e", state);
        }
    }

    public void onClickDraw_O(View view)
    {
        boolean state = ((Switch) view).isChecked();

        if (state)
        {
            check.setGesture("o", state);
        }
        else
        {
            check.setGesture("o", state);
        }
    }

    public void onClickDraw_W(View view)
    {
        boolean state = ((Switch) view).isChecked();

        if (state)
        {
            check.setGesture("w", state);
        }
        else
        {
            check.setGesture("w", state);
        }
    }

    public void onClickDraw_M(View view)
    {
        boolean state = ((Switch) view).isChecked();

        if (state)
        {
            check.setGesture("m", state);
        }
        else
        {
            check.setGesture("m", state);
        }
    }

    public void onClickDraw_C(View view)
    {
        boolean state = ((Switch) view).isChecked();

        if (state)
        {
            check.setGesture("c", state);
        }
        else
        {
            check.setGesture("c", state);
        }
    }

    @Override
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
    }
}
