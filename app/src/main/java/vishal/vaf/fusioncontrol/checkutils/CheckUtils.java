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

package vishal.vaf.fusioncontrol.checkutils;

import android.os.Build;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import android.app.*;

/**
 * Created by vishal on 13/3/15.
 */
public class CheckUtils {

    private String tag = "Fusion";

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
        if ( Build.DEVICE.equals("8675-FHD") || Build.PRODUCT.equals("8675-FHD"))
        {
            Log.d(tag, "supported");
            return true;
        }

        Log.d(tag, "not supported");
        return false;
    }

    public void setGesture(final String gestureNameUsed, final boolean status)
    {
        new Thread()
        {
            @Override
            public void run()
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
        }.start();
    }

    public void reboot()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Process p;
                DataOutputStream os;
                InputStream is;
                String res = "";
                //the format command
                String[] command = new String[]{"reboot"};

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
        }, 5000);
    }

    public String getResponse()
    {
        Log.d(tag, "Copy gesture_ctrl to sdcard. We don't want to mess up being in root");

        Process p;
        DataOutputStream os;
        InputStream is;

        String res = "";
        //the format command
        String[] command = new String[]{ "dmesg | grep \"<<-GTP-INFO->>\" | tail -6 > /sdcard/dmesg.txt"};

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

        File f = new File("/sdcard/dmesg.txt");

        StringBuilder text = new StringBuilder();
        String line= "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            while ((line = br.readLine()) != null) {
                text.append(line);
				//mod
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }
}
