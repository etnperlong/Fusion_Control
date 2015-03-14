package vishal.vaf.fusioncontrol.checkutils;

import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileReader;

/**
 * Created by vishal on 13/3/15.
 */
public class CheckUtils {

    private String tag = "Fusion";

    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public Boolean hasRoot() {
        Process root;
        DataOutputStream os;
        try {
            root = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(root.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            return ((root.waitFor()) == 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isDeviceSupported()
    {
        if (Build.MODEL.equals("AO5510") | Build.DEVICE.equals("YUREKA") || Build.PRODUCT.equals("YUREKA"))
        {
            return true;
        }

        return false;
    }

    public void setGesture(String gestureNameUsed, boolean status)
    {
        Log.d(tag, "Let's set the gestures, yeah?");


        Process p;
        DataOutputStream os;
        InputStream is;
        String res = "";
        //the format command
        String[] command = new String[]{ "echo " + gestureNameUsed + "=" + status + " >> /sys/devices/virtual/touchscreen/touchscreen_dev/gesture_ctrl"};

        //Calling su, because nothing happens, if you aren't, the SuperUser. Hahahaha!
        try {
            //create a new root shell
            p = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(p.getOutputStream());
            is = p.getErrorStream();
            //add the commands to the process
            for (String cmd : command) {
                Log.d(tag, cmd);
                os.writeBytes(cmd + "\n");
            }
            //flush out the process
            os.writeBytes("exit\n");
            byte[] buff = new byte[512];
            is.read(buff);
            res += new String(buff);
            os.flush();
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResponse()
    {
        Log.d(tag, "Copy gesture_ctrl to sdcard. We don't want to mess up being in root");


        Process p;
        DataOutputStream os;
        InputStream is;

        String res = "";
        //the format command
        String[] command = new String[]{ "cp /sys/devices/virtual/touchscreen/touchscreen_dev/gesture_ctrl /sdcard/gesture.txt"};

        //Calling su, because nothing happens, if you aren't, the SuperUser. Hahahaha!
        try {
            //create a new root shell
            p = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(p.getOutputStream());
            is = p.getErrorStream();
            //add the commands to the process
            for (String cmd : command) {
                Log.d(tag, cmd);
                os.writeBytes(cmd + "\n");
            }
            //flush out the process
            os.writeBytes("exit\n");
            byte[] buff = new byte[512];
            is.read(buff);
            res += new String(buff);
            os.flush();
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File f = new File ("/sdcard/gesture.txt");

        StringBuilder text = new StringBuilder();
        String line= "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }
}
