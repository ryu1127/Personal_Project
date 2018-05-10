package com.example.ryu.earphone_test_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

import static android.content.Intent.ACTION_HEADSET_PLUG;
import static android.content.Intent.ACTION_MEDIA_BUTTON;

/**
 * Created by ryu on 2017. 2. 3..
 */

public class EarphoneReceiver extends BroadcastReceiver{



    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Receive intent",Toast.LENGTH_LONG).show();


        if(intent.getAction().equals(ACTION_HEADSET_PLUG)){
            //이벤트 처리
            //KeyEvent ke = (KeyEvent)intent.getExtra(Intent.EXTRA_KEY_EVENT);
            KeyEvent ke = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if(ke.getKeyCode()==KeyEvent.ACTION_DOWN){
                Toast.makeText(context,"VOLUME_DOWN_CLICKED",Toast.LENGTH_SHORT).show();
            }
            else if(ke.getKeyCode()==KeyEvent.ACTION_UP){
                Toast.makeText(context,"VOLUME_UP_CLICKED",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,"CONNECT WITH EARPHONE BUT NOT CLICKED",Toast.LENGTH_LONG).show();
            }
        }
        else if(intent.getAction().equals(ACTION_MEDIA_BUTTON)){
            Toast.makeText(context,"MEDIA BUTTON IS STILL ON",Toast.LENGTH_LONG).show();
        }
    }

}
