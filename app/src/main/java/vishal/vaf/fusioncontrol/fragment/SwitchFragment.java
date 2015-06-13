package vishal.vaf.fusioncontrol.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import vishal.vaf.fusioncontrol.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SwitchFragment extends Fragment {


    public SwitchFragment() {
        // Required empty public constructor
    }

    SharedPreferences setOnBootSettings;
    public static final String SOB_PREFS_NAME = "SetOnBoot";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_switch, container, false);

        Switch sw1 = (Switch) rootview.findViewById(R.id.switch1);
        Switch sw2 = (Switch) rootview.findViewById(R.id.switch2);
        Switch sw3 = (Switch) rootview.findViewById(R.id.switch3);
        Switch sw4 = (Switch) rootview.findViewById(R.id.switch4);
        Switch sw5 = (Switch) rootview.findViewById(R.id.switch5);
        Switch sw6 = (Switch) rootview.findViewById(R.id.switch6);
        Switch sw7 = (Switch) rootview.findViewById(R.id.switch7);
        Switch sw8 = (Switch) rootview.findViewById(R.id.switch8);
        Switch sw9 = (Switch) rootview.findViewById(R.id.switch9);
        Switch sw10 = (Switch) rootview.findViewById(R.id.switch10);
        Switch sw11 = (Switch) rootview.findViewById(R.id.switch11);

        setOnBootSettings = getActivity().getSharedPreferences(SOB_PREFS_NAME, Context.MODE_PRIVATE);

        if (setOnBootSettings != null) {
            sw1.setChecked(setOnBootSettings.getBoolean("double_click", false));
            sw2.setChecked(setOnBootSettings.getBoolean("up", false));
            sw3.setChecked(setOnBootSettings.getBoolean("down", false));
            sw4.setChecked(setOnBootSettings.getBoolean("right", false));
            sw5.setChecked(setOnBootSettings.getBoolean("left", false));
            sw6.setChecked(setOnBootSettings.getBoolean("e", false));
            sw7.setChecked(setOnBootSettings.getBoolean("o", false));
            sw8.setChecked(setOnBootSettings.getBoolean("w", false));
            sw9.setChecked(setOnBootSettings.getBoolean("m", false));
            sw10.setChecked(setOnBootSettings.getBoolean("c", false));
            sw11.setChecked(setOnBootSettings.getBoolean("checked", false));
        }

        return rootview;
    }

}
