package parohyApps.antdiary.data;


import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;


/**
 * Created by tomas on 2/16/2016.
 */
public class BreedHandle {

    private ArrayList<Breed> loadedBreed;
    private ArrayList<Bitmap> loadedBitmap;
    private File dir;

    public BreedHandle(File dir){
        loadBreeds(dir);
        this.dir = dir;
    }

    private void loadBreeds(File dir){
        File inputFile = new File(dir, "breed.dat");
        if(inputFile.exists()) {
            try {
                Log.d("BreedHandle","Loading breeds");
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
            loadBitmaps(dir);
        }
    }

    private void loadBitmaps(File dir){
        if(loadedBreed != null){
            loadedBitmap = new ArrayList<>();
            for(Breed tmpBreed : loadedBreed){
                String name = tmpBreed.getName() + tmpBreed.getRace();
                BitmapIO bitmapLoader = new BitmapIO(dir,name);
                try {
                    loadedBitmap.add(bitmapLoader.loadBitmap());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    private void saveBitmap(){
        if(loadedBreed != null && loadedBitmap != null && dir != null){
            for(Breed tmpBreed : loadedBreed){

            }
            //BitmapIO bitmapSaver = new BitmapIO(dir,)
        }
    }

    public ArrayList<Breed> getBreedList(){
        if(loadedBreed == null){
            loadedBreed = new ArrayList<>();
        }
        return loadedBreed;
    }

    public ArrayList<Bitmap> getBitmapList(){
        return loadedBitmap;
    }

    public void deleteBreed(int index){
        loadedBreed.remove(index);
        saveBreeds();
        loadBreeds(dir);
    }

}
