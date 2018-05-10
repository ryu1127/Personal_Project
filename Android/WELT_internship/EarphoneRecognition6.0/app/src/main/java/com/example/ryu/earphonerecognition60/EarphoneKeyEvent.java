package com.example.ryu.earphonerecognition60;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

public class EarphoneKeyEvent extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        if(!intent.ACTION_MEDIA_BUTTON.equals(intentAction))
            return;
        KeyEvent event = (KeyEvent) intent.getParcelableExtra(intent.EXTRA_KEY_EVENT);

        if(event==null) return;

        int action = event.getAction();
        int keyCode = event.getKeyCode();

        if(action == KeyEvent.ACTION_UP && keyCode==KeyEvent.KEYCODE_HEADSETHOOK){
            Toast.makeText(context,"Earphone Pushed Up",Toast.LENGTH_SHORT);
        }

    }
}
