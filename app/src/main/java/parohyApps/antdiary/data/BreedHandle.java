package parohyApps.antdiary.data;

import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import parohyApps.antdiary.gui.BreedListAdapter;

/**
 * Created by tomas on 2/16/2016.
 */
public class BreedHandle {

    private ArrayList<Breed> loadedBreed;
    private File dir;

    public BreedHandle(File dir){
        loadBreeds(dir);
        this.dir = dir;
    }

    private void loadBreeds(File dir){
        File inputFile = new File(dir, "breed.dat");
        if(inputFile.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(inputFile);
                loadedBreed = new ArrayList<>();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                while (true) {
                    loadedBreed.add((Breed) objectInputStream.readObject());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertBreed(Breed breed){
        if(loadedBreed == null){
            loadedBreed = new ArrayList<>();
        }
        loadedBreed.add(breed);
        saveBreeds();
    }

    private void saveBreeds(){
        if(loadedBreed != null && dir != null){
            File outputFile = new File(dir, "breed.dat");
            try {
                if(!outputFile.exists()){
                    outputFile.createNewFile();
                }
                else{
                    outputFile.delete();
                    outputFile.createNewFile();
                    loadBreeds(outputFile);
                }
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                for(Breed tmpBreed : loadedBreed){
                    objectOutputStream.writeObject(tmpBreed);
                }
                objectOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Breed> getBreedList(){
        return loadedBreed;
    }

    public void deleteBreed(int index){
        loadedBreed.remove(index);
        saveBreeds();
        loadBreeds(dir);
    }

}
