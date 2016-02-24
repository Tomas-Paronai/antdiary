package parohyApps.antdiary.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by tomas on 2/24/2016.
 */
public class BitmapIO {

    Bitmap handledBitmap;
    File parentDir;
    String id;

    public BitmapIO(File dir, String id){
        this.parentDir = dir;
        this.id = id+".dat";
    }

    public BitmapIO(File dir, String id, Bitmap bitmap){
        this(dir,id);
        handledBitmap = bitmap;
    }

    public void saveBitmap() throws IOException {
        if(parentDir != null && id != null && handledBitmap != null){
            File outputFile = new File(parentDir,id);
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            handledBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
            byte bitmapBytes[] = byteStream.toByteArray();
            objectOutputStream.write(bitmapBytes, 0, bitmapBytes.length);
        }
    }

    public Bitmap loadBitmap() throws IOException {
        if(parentDir != null && id != null){
            File inputFile = new File(parentDir,id);
            if(!inputFile.exists()){
                return null;
            }
            FileInputStream inputStream = new FileInputStream(inputFile);
            ObjectInputStream objectInputStreamam = new ObjectInputStream(inputStream);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int b;
            while((b = objectInputStreamam.read()) != -1)
                byteStream.write(b);
            byte bitmapBytes[] = byteStream.toByteArray();
            return BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
        }

        return null;
    }
}
