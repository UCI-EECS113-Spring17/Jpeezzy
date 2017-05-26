package com.example.macbreaker.googlevoicetest3;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by macbreaker on 5/13/17.
 */


public class BoardView extends SurfaceView implements SurfaceHolder.Callback {
    public BoardView(Context context) {
        super(context); //use stuff from parent class
        getHolder().addCallback(this);
        setFocusable(true);
        setWillNotDraw(false); //we draw stuff
        t1 = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

    }

    TextToSpeech t1;
    EditText ed1;
    Button b1;
    Thread newThread = new Thread();

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    ;

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            System.out.println("it gets to onTouchEvent");
            try {
                voiceTest(t1); //had an unhandled exception. Check later what it was, probably interrupted
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP)
            return true;
        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void voiceTest(TextToSpeech t1) throws Exception {
        readFile(); //unhandled exception
        System.out.println("it gets to hello world?");
        String temperature = readFile();
        String toSpeak = "Hello Justin! Today is May 10. Todays weather will be" + temperature + " Degrees. try to wear something normal";
        //t1.getVoice();
        t1.speak(toSpeak, TextToSpeech.QUEUE_ADD, null, "id");
    }

    String SeparateString(String text, Integer num)
    {

        return text;
    }

    private String readFile() throws FileNotFoundException {
       //which returns the internal app files directory path

        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File("/storage/8C04-1A48/temperature.txt");
        System.out.println("file directory is " + sdcard.toString());
        //for galaxy s3 returns internal memory
        StringBuilder text = new StringBuilder();
        String line = "";
        FileReader textFile = new FileReader(file);
        try {
            System.out.println("gets to first line of trycatch");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String temp  = br.readLine();
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

//Find the view by its id
        TextView tv = (TextView) findViewById(R.id.text_view); //add xml extentssion
        //http://stackoverflow.com/questions/2902689/how-can-i-read-a-text-file-from-the-sd-card-in-android

//Set the text
       //tv.setText("hello world");

        return line;
    }


    String whatToWear(String Temperature){
        int temp = Integer.parseInt(Temperature);
        if(temp >=0 & temp < 60)
            return "Heavy clothes, a beanie, shoes, and a thick windbreaker";
        else if (temp>=60 & temp < 70)
            return "A windbreaker and beanie";
        else if(temp >= 70 & temp < 80)
            return "Weather is perfect, go outside with a light jacket, sandals, and shorts ";
        else if(temp >= 80 & temp >85 )
            return "It's a little warm outside. Wear shorts, a loose tee-shirt, and sandals.";
        else if(temp >= 85 & temp < 90 )
            return "It's warm. Wear loose shorts and shirt";
        else if(temp >= 90)
            return "It's hot right now. Make sure to wear sunblock, as well as shorts and shirt.";
        else
            return "Soryr, i cannot recognize this temperature, please try again.";
    }


}
