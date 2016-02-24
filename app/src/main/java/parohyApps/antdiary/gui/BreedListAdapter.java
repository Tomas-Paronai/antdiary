package parohyApps.antdiary.gui;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import parohyApps.antdiary.R;
import parohyApps.antdiary.data.Breed;
import parohyApps.antdiary.fragments.ViewAll;

/**
 * Created by tomas on 2/16/2016.
 */
public class BreedListAdapter extends ArrayAdapter<Breed> implements View.OnClickListener{

    private ArrayList<Breed> array;
    private ArrayList<Bitmap> pics;
    private ItemReaction reaction;


    public BreedListAdapter(ViewAll context, int resource, ArrayList<Breed> items, ArrayList<Bitmap> pics) {
        super(context.getContext(), resource, items);
        reaction = (ItemReaction) context;
        array = items;
        this.pics = pics;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.breed_list_item, null);
        }
        if(array != null){
            Breed currentBreed = array.get(position);
            TextView name = (TextView) view.findViewById(R.id.item_name);
            TextView race = (TextView) view.findViewById(R.id.item_race);
            name.setText(currentBreed.getName());
            race.setText(currentBreed.getRace());

            ImageView avatar = (ImageView) view.findViewById(R.id.item_image);
            if(pics != null && pics.get(position) != null){
                avatar.setImageBitmap(pics.get(position));
            }

            ImageButton show = (ImageButton) view.findViewById(R.id.item_view_button);
            ImageButton edit = (ImageButton) view.findViewById(R.id.item_edit_button);
            ImageButton delete = (ImageButton) view.findViewById(R.id.item_delete_button);

            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reaction.reactOnButtonClick(position, v.getId());
                }
            });

            edit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    reaction.reactOnButtonClick(position, v.getId());
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reaction.reactOnButtonClick(position, v.getId());
                }
            });
        }


        return view;
    }

    @Override
    public void onClick(View v) {

    }


}
