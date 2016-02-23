package parohyApps.antdiary;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import parohyApps.antdiary.fragments.Album;
import parohyApps.antdiary.fragments.Info;
import parohyApps.antdiary.fragments.Navigation;
import parohyApps.antdiary.fragments.NewData;
import parohyApps.antdiary.fragments.ViewAll;
import parohyApps.antdiary.gui.Communicator;

public class WellcomeActivity extends AppCompatActivity implements Communicator {

    private Fragment navigationFragment;

    private NewData newDataFragment;
    private ViewAll viewAllFragment;
    private Album albumFragment;
    private Info infoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        android.support.v4.app.FragmentManager fmng = this.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ftra = fmng.beginTransaction();

        navigationFragment = new Navigation();
        ftra.add(R.id.navigation_view, navigationFragment, "navigationFragment");

        if(savedInstanceState != null){
            viewAllFragment = (ViewAll) getSupportFragmentManager().getFragment(savedInstanceState,"viewAllFragment");
            ftra.replace(R.id.view_all_holder,viewAllFragment,"viewAllFragment");
            if(viewAllFragment.isActive()){
                ftra.show(viewAllFragment);
                Log.d("LoadInstance","ViewAll is active!");
            }
            else{
                ftra.hide(viewAllFragment);
                Log.d("LoadInstance", "ViewAll is inactive!");
            }

            newDataFragment = (NewData) getSupportFragmentManager().getFragment(savedInstanceState,"newDataFragment");
            ftra.replace(R.id.new_data_holder,newDataFragment,"newDataFragment");
            if(newDataFragment.isActive()){
                ftra.show(newDataFragment);
                Log.d("LoadInstance", "NewData is active!");
            }
            else{
                ftra.hide(newDataFragment);
                Log.d("LoadInstance", "NewData is inactive!");
            }

            albumFragment = (Album) getSupportFragmentManager().getFragment(savedInstanceState,"albumFragment");
            ftra.replace(R.id.album_holder,albumFragment,"albumFragment");
            if(albumFragment.isActive()){
                ftra.show(albumFragment);
                Log.d("LoadInstance", "Album is active!");
            }
            else{
                ftra.hide(albumFragment);
                Log.d("LoadInstance", "Album is inactive!");
            }

            infoFragment = (Info) getSupportFragmentManager().getFragment(savedInstanceState,"infoFragment");
            ftra.replace(R.id.info_holder,infoFragment,"infoFragment");
            if(infoFragment.isActive()){
                ftra.show(infoFragment);
                Log.d("LoadInstance", "Info is active!");
            }
            else{
                ftra.hide(infoFragment);
                Log.d("LoadInstance", "Info is inactive!");
            }

        }
        else{
            Log.d("LoadInstance", "First load!");
            viewAllFragment = new ViewAll();
            ftra.add(R.id.view_all_holder, viewAllFragment, "viewAllFragment");
            ftra.show(viewAllFragment);
            //viewAllFragment.setState(true);

            newDataFragment = new NewData();
            ftra.add(R.id.new_data_holder, newDataFragment, "newDataFragment");
            ftra.hide(newDataFragment);

            albumFragment = new Album();
            ftra.add(R.id.album_holder, albumFragment, "albumFragment");
            ftra.hide(albumFragment);

            infoFragment = new Info();
            ftra.add(R.id.info_holder, infoFragment, "infoFragment");
            ftra.hide(infoFragment);
        }

        ftra.commit();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState,"viewAllFragment",viewAllFragment);
        getSupportFragmentManager().putFragment(outState,"newDataFragment",newDataFragment);
        getSupportFragmentManager().putFragment(outState,"albumFragment",albumFragment);
        getSupportFragmentManager().putFragment(outState,"infoFragment",infoFragment);
    }

    @Override
    public void onFragmentTouch(int fragmentId) {
        android.support.v4.app.FragmentManager fmng = this.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ftra = fmng.beginTransaction();


        if(fragmentId == findViewById(R.id.add_button).getId()){
            ftra.show(newDataFragment);
            newDataFragment.setState(true);

            ftra.hide(viewAllFragment); viewAllFragment.setState(false);
            ftra.hide(albumFragment); albumFragment.setState(false);
            ftra.hide(infoFragment); infoFragment.setState(false);
        }

        else if(fragmentId == findViewById(R.id.view_button).getId()){
            ftra.show(viewAllFragment);
            viewAllFragment.setState(true);

            ftra.hide(newDataFragment); newDataFragment.setState(false);
            ftra.hide(albumFragment);albumFragment.setState(false);
            ftra.hide(infoFragment); infoFragment.setState(false);
        }

        else if(fragmentId == findViewById(R.id.album_button).getId()){
            ftra.show(albumFragment);
            albumFragment.setState(true);

            ftra.hide(newDataFragment); newDataFragment.setState(false);
            ftra.hide(viewAllFragment); viewAllFragment.setState(false);
            ftra.hide(infoFragment); infoFragment.setState(false);
        }

        else if(fragmentId == findViewById(R.id.info_button).getId()){
            ftra.show(infoFragment);
            infoFragment.setState(true);

            ftra.hide(newDataFragment); newDataFragment.setState(false);
            ftra.hide(albumFragment); albumFragment.setState(false);
            ftra.hide(viewAllFragment);  viewAllFragment.setState(false);
        }

        ftra.commit();
    }

    public void viewContent(View v){
        Toast.makeText(this, "view breed ", Toast.LENGTH_SHORT).show();
    }

    public void editContent(View v){
        Toast.makeText(this, "edit breed ", Toast.LENGTH_SHORT).show();
    }

    public void deleteContent(View v){
        Toast.makeText(this, "delete breed ", Toast.LENGTH_SHORT).show();
    }
}
