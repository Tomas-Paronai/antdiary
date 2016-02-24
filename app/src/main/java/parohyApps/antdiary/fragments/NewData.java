package parohyApps.antdiary.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import parohyApps.antdiary.R;
import parohyApps.antdiary.data.BitmapIO;
import parohyApps.antdiary.data.Breed;
import parohyApps.antdiary.data.BreedHandle;

/**
 * Created by tomas on 2/6/2016.
 */
public class NewData extends ParentFragment implements View.OnClickListener{

    private int saveButtId, avatarButtId;
    private boolean avatarSet;
    private final int PICK_AVATAR = 0;
    private BreedHandle breedHandler;
    private ImageButton avatarButt;
    private Button removeImageButt;

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
        removeImageButt = (Button) view.findViewById(R.id.remove_image);
        removeImageButt.setOnClickListener(this);
        avatarButt = (ImageButton) view.findViewById(R.id.breed_avatar);
        avatarButt.setOnClickListener(this);

        saveButtId = R.id.save_button;
        avatarButtId = R.id.breed_avatar;


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
        if(v.getId() == R.id.breed_avatar){
            Log.d("NEW BREED","Pick Image");
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_AVATAR);
        }
        else if(v.getId() == R.id.remove_image){
            avatarButt.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_box_black_36dp));
            v.setVisibility(View.GONE);
            avatarButt.setAlpha(0.4f);
            avatarSet = false;
        }
        else if(v.getId() == saveButtId){
            Log.d("NEW BREED","Save data");
            boolean canSave = false;
            EditText name = (EditText) getActivity().findViewById(R.id.et_name);
            EditText race = (EditText) getActivity().findViewById(R.id.et_race);
            EditText age = (EditText) getActivity().findViewById(R.id.et_age);

            String breedName = "";
            String breedRace = "";
            String breedAge = "0";

            if(name.getText().toString().length() > 0){
                breedName = name.getText().toString();
                canSave = true;
            }
            if(race.getText().toString().length() > 0){
                breedRace = race.getText().toString();
                canSave = true;
            }
            if(age.getText().toString().length() > 0){
                breedAge = age.getText().toString();
            }

            if(canSave){
                Breed tmpBreed = new Breed(breedName,breedRace,breedAge);
                Bitmap pickedImage = ((BitmapDrawable)avatarButt.getDrawable()).getBitmap();
                if(pickedImage != null && avatarSet){
                    String id = tmpBreed.getName()+tmpBreed.getRace();
                    BitmapIO bitmapSaver = new BitmapIO(Environment.getExternalStorageDirectory(),id,pickedImage);
                    try {
                        bitmapSaver.saveBitmap();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                breedHandler.insertBreed(tmpBreed);
                reset();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            try {
                //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
                InputStream inputStream = this.getContext().getContentResolver().openInputStream(data.getData());
                Bitmap pickedImage = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                avatarButt.setImageBitmap(pickedImage);
                avatarButt.setScaleType(ImageView.ScaleType.FIT_XY);
                avatarButt.setAlpha(1f);
                avatarSet = true;

                removeImageButt.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

        avatarButt.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_box_black_36dp));
        avatarButt.setAlpha(0.4f);
        removeImageButt.setVisibility(View.GONE);
        avatarSet = false;
    }
}
