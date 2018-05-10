package com.example.ryu.earphonerecognition60;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        ComponentName componentName = new ComponentName(this, MediaButtonReceiver.class);
        audioManager.registerMediaButtonEventReceiver(componentName);

    }
}


