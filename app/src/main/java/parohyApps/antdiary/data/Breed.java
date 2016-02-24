package parohyApps.antdiary.data;

import java.io.Serializable;

/**
 * Created by tomas on 2/16/2016.
 */
public class Breed implements Serializable{

    private String name;
    private String race;
    private int age;



    public Breed(String name, String race, String age){
        this.name = name;
        this.race = race;
        this.age = Integer.parseInt(age);
    }

    public String getName(){
        return name;
    }

    public String getRace(){
        return race;
    }

    public String getAge(){
        return String.valueOf(age);
    }

}
