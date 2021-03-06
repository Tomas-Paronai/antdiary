package parohyApps.antdiary.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import parohyApps.antdiary.R;
import parohyApps.antdiary.data.Breed;
import parohyApps.antdiary.data.BreedHandle;
import parohyApps.antdiary.gui.BreedListAdapter;
import parohyApps.antdiary.gui.ItemReaction;
import parohyApps.antdiary.gui.Updateable;

/**
 * Created by tomas on 2/16/2016.
 */
public class ViewAll extends ParentFragment implements ItemReaction,Updateable{

    private BreedHandle breedHandler;
    private BreedListAdapter adapter;
    private ArrayList<Breed> breeds;
    private ArrayList<Bitmap> breedPics;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.viewall_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        breedHandler = new BreedHandle(Environment.getExternalStorageDirectory());
        breeds = breedHandler.getBreedList();
        breedPics = breedHandler.getBitmapList();
        if(breeds != null){
            Log.d("VIEW_ALL", ""+breeds.size());
        }


        adapter = new BreedListAdapter(this,R.layout.breed_list_item,breeds,breedPics);
        ListView breedList = (ListView) view.findViewById(R.id.breed_list_view);

        breedList.setAdapter(adapter);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState){
        adapter.notifyDataSetChanged();
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void setState(boolean state) {
        super.setState(state);
    }

    @Override
    public void reactOnButtonClick(int position, int buttonId) {
        if(buttonId == view.findViewById(R.id.item_delete_button).getId()){
            Toast.makeText(this.getContext(),"delete breed "+position,Toast.LENGTH_SHORT).show();
        }
        else if(buttonId == view.findViewById(R.id.item_edit_button).getId()){
            Toast.makeText(this.getContext(),"edit breed "+position,Toast.LENGTH_SHORT).show();
        }
        else if(buttonId == view.findViewById(R.id.item_view_button).getId()){
            Toast.makeText(this.getContext(),"view breed "+position,Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void updateInfo() {
        adapter.notifyDataSetChanged();
    }
}
