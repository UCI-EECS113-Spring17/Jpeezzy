package com.example.macbreaker.googlevoicetest3;
import java.util.Locale;

import android.Manifest;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
    ImageButton imageButton;
    TextToSpeech t1;
    EditText ed1;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestPermission();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        setContentView(R.layout.activity_main);
        imageButton = new ImageButton(this);
        //   addListenerOnButton();
        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        } );

    }

    private void requestPermission() { //allows me to access files

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void buttonHit(View view) //whenh button is hit.
    {
        System.out.println("Gets to onClick");
        try {
            voiceTest(t1); //had an unhandled exception. Check later what it was, probably interrupted
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void voiceTest(TextToSpeech t1) throws Exception {
        ReadTextFile.readFile(); //unhandled exception
        System.out.println("it gets to hello world?");
        String temperature = ReadTextFile.readFile();
        String toSpeak = "Hello Justin! Today is May 10. Todays weather will be" + temperature + " Degrees Farenheit." + " Wear" + ReadTextFile.whatToWear(temperature);
        //t1.getVoice();
        t1.speak(toSpeak, TextToSpeech.QUEUE_ADD, null, "id");
    }


}