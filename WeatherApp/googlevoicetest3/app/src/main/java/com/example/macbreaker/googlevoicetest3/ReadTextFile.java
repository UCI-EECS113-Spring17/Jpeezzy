package com.example.macbreaker.googlevoicetest3;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Created by Justin on 5/30/2017.
 */

public class ReadTextFile {
    public static String readFile() throws FileNotFoundException //reads txt file and grabs temperature
    {
        //which returns the internal app files directory path
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File("/storage/8C04-1A48/DropboxSync/temperature.txt");
        System.out.println("file directory is " + sdcard.toString());
        //for galaxy s3 returns internal memory
        StringBuilder text = new StringBuilder();
        String line = "";
        java.io.FileReader textFile = new java.io.FileReader(file);
        try {
            System.out.println("gets to first line of trycatch");
            BufferedReader br = new BufferedReader(new java.io.FileReader(file));
            String temp = br.readLine();
            System.out.println("readline is " + temp);
            line = temp;
            //System.out.println("it goes into br statement. line = " + line);
            text.append(line);
            br.close();
        } catch (IOException e) {
            System.out.println("gets to exeception. try to avoid this");
            //You'll need to add proper error handling here
        }
        System.out.println("text is " + text.toString());
        return line;
    }

    public static String whatToWear(String Temperature){
        int temp = Integer.parseInt(Temperature);
        if(temp >=0 & temp < 60)
            return "Heavy clothes, a beanie, shoes, and a thick windbreaker";
        else if (temp>=60 & temp < 70)
            return "A windbreaker and beanie";
        else if(temp >= 70 & temp < 80)
            return "Weather is perfect. Go outside with a light jacket, jeans, and your favorite shoes";
        else if(temp >= 80 & temp >85 )
            return "It's a little warm outside. Wear shorts, a loose tee-shirt, and sandals.";
        else if(temp >= 85 & temp < 90 )
            return "It's warm. Wear loose shorts, a shirt, and sandals";
        else if(temp >= 90)
            return "It's hot right now. Make sure to wear sunblock, as well as shorts and linen, or a light cotton shirt.";
        else
            return "Sorry, i cannot recognize this temperature, please try again.";
    }
}

