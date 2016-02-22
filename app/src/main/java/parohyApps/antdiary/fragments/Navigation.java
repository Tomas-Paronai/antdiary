package parohyApps.antdiary.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import parohyApps.antdiary.R;
import parohyApps.antdiary.gui.Communicator;

/**
 * Created by tomas on 2/6/2016.
 */
public class Navigation extends Fragment implements View.OnClickListener {

    private ImageButton addButton;
    private ImageButton viewButton;
    private ImageButton albumButton;
    private ImageButton infoButton;

    private Communicator comm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation_fragment_layout, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addButton = (ImageButton) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(this);
        viewButton = (ImageButton) view.findViewById(R.id.view_button);
        viewButton.setOnClickListener(this);
        albumButton = (ImageButton) view.findViewById(R.id.album_button);
        albumButton.setOnClickListener(this);
        infoButton = (ImageButton) view.findViewById(R.id.info_button);
        infoButton.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        comm = (Communicator) getActivity();
        }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        comm.onFragmentTouch(v.getId());
    }
}
