package parohyApps.antdiary.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by tomas on 2/18/2016.
 */
public class ParentFragment extends Fragment {

    private boolean active = false;

    public boolean isActive(){
        return active;
    }

    public void setState(boolean state){
        active = state;
    }
}
