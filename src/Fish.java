import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Fish {

    ArrayList<Place> placeList = new ArrayList<>();

    public void readFile(){
        try {
            tryreadFile();
        } catch (Exception e) {
            System.err.println("Hiba!");
            System.err.println(e.getMessage());
        }
    }
    public void tryreadFile() throws FileNotFoundException{
        FileReader filereader = new FileReader("database.json");
        BufferedReader bufferedreader = new BufferedReader(filereader);
        Gson gson = new Gson();

        Places spots = gson.fromJson(bufferedreader, Places.class);  

        for(Place place : spots.fishingspots)
            placeList.add(place);   
    }
    public void feladat01(){
        for(Place place: placeList){
            System.out.println(place.name);
        }
    }

    public void feladat02(){
        ArrayList<Place> sokList = new ArrayList<>();
        for(Place place: placeList){
            if (place.rods > 3) {
                sokList.add(place);
            }     
        }
        writerSok(sokList);
    }
    public void writerSok(ArrayList<Place> sokList){
        try {
            trywriteSok(sokList);
        } catch (Exception e) {
            System.err.println("Hiba! Sikeretlene file írás!");
            System.err.println(e.getMessage());
        }
    }
    public void trywriteSok(ArrayList<Place> sokList) throws IOException{
        FileWriter filewriter = new FileWriter("sok.csv");
        for(Place place: sokList){
            filewriter.write(place.id+",");
            filewriter.write(place.name+",");
            filewriter.write(place.spot+",");
            filewriter.write(place.date+",");
            filewriter.write(place.rods+",");
            filewriter.write(place.paid+"\n" );
        }
        filewriter.close();
    }
}
