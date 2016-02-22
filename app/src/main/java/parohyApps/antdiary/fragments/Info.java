package parohyApps.antdiary.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import parohyApps.antdiary.R;

/**
 * Created by tomas on 2/16/2016.
 */
public class Info extends ParentFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment_layout, container, false);

        return view;
    }
}
