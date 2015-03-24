package vishal.vaf.fusioncontrol.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vishal.vaf.fusioncontrol.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SwitchFragment extends Fragment {


    public SwitchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_switch, container, false);
    }


}
