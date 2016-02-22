package parohyApps.antdiary.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import parohyApps.antdiary.R;
import parohyApps.antdiary.data.Breed;
import parohyApps.antdiary.fragments.ViewAll;

/**
 * Created by tomas on 2/16/2016.
 */
public class BreedListAdapter extends ArrayAdapter<Breed> implements View.OnClickListener {

    private ArrayList<Breed> array;
    private ItemReaction reaction;


    public BreedListAdapter(ViewAll context, int resource, ArrayList<Breed> items) {
        super(context.getContext(), resource, items);
        reaction = (ItemReaction) context;
        array = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.breed_list_item, null);
        }

        Breed currentBreed = array.get(position);
        TextView name = (TextView) view.findViewById(R.id.item_name);
        TextView race = (TextView) view.findViewById(R.id.item_race);
        name.setText(currentBreed.getName());
        race.setText(currentBreed.getRace());

        ImageButton show = (ImageButton) view.findViewById(R.id.item_view_button);
        ImageButton edit = (ImageButton) view.findViewById(R.id.item_edit_button);
        ImageButton delete = (ImageButton) view.findViewById(R.id.item_delete_button);
        show.setOnClickListener(this);
        edit.setOnClickListener(this);
        delete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(reaction != null){
            reaction.reactOnButtonClick(v.getId());
        }
    }
}
