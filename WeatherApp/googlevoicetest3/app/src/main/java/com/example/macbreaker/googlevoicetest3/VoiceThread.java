package com.example.macbreaker.googlevoicetest3;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;

import java.util.Locale;

/**
 * Created by macbreaker on 5/13/17.
 */

public class VoiceThread extends MainActivity implements Runnable{
    public VoiceThread(){};
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void run()//overrides thread class run()
    {
      //  t1.setLanguage(Locale.US);
        System.out.println("it gets to hello world?");
        String toSpeak = "Hello world!";
        //t1.getVoice();
        //t1.speak(toSpeak, TextToSpeech.QUEUE_ADD, null, "id");

    }

}
