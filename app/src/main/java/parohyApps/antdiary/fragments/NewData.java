package parohyApps.antdiary.fragments;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import parohyApps.antdiary.R;
import parohyApps.antdiary.data.Breed;
import parohyApps.antdiary.data.BreedHandle;

/**
 * Created by tomas on 2/6/2016.
 */
public class NewData extends ParentFragment implements View.OnClickListener{

    private int saveButtId;
    private BreedHandle breedHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_data_layout, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button saveButton = (Button) view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
        saveButtId = R.id.save_button;

        breedHandler = new BreedHandle(Environment.getExternalStorageDirectory());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            EditText name = (EditText) getActivity().findViewById(R.id.et_name);
            EditText race = (EditText) getActivity().findViewById(R.id.et_race);
            EditText age = (EditText) getActivity().findViewById(R.id.et_age);

            name.setText(savedInstanceState.getString("nameString", "NO STRING"));
            race.setText(savedInstanceState.getString("raceString", "NO STRING"));
            age.setText(savedInstanceState.getString("ageString", "NO STRING"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText name = (EditText) getActivity().findViewById(R.id.et_name);
        EditText race = (EditText) getActivity().findViewById(R.id.et_race);
        EditText age = (EditText) getActivity().findViewById(R.id.et_age);

        outState.putString("nameString",name.getText().toString());
        outState.putString("raceString",race.getText().toString());
        outState.putString("ageString", age.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == saveButtId){
            EditText name = (EditText) getActivity().findViewById(R.id.et_name);
            EditText race = (EditText) getActivity().findViewById(R.id.et_race);
            EditText age = (EditText) getActivity().findViewById(R.id.et_age);

            Breed tmpBreed = new Breed(name.getText().toString(),race.getText().toString(),age.getText().toString());
            breedHandler.insertBreed(tmpBreed);

            reset();
        }
    }

    private void reset(){
        EditText name = (EditText) getActivity().findViewById(R.id.et_name);
        EditText race = (EditText) getActivity().findViewById(R.id.et_race);
        EditText age = (EditText) getActivity().findViewById(R.id.et_age);

        name.setText("");
        race.setText("");
        age.setText("");

        name.clearFocus();
        race.clearFocus();
        age.clearFocus();
    }
}
