package com.example.ryu.earphonerecognition;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.EventListener;

class EarphoneKeyEvent extends BroadcastReceiver{
    int check=0;

    public EarphoneKeyEvent(){
        super();
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        if (!Intent.ACTION_MEDIA_BUTTON.equals(intentAction)) {
            return;     // 미디어액션버튼이면 받고 아니면 종료
        }
        KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        if (event == null) {
            return;
        }
        int action = event.getAction();
        int keycode = event.getKeyCode();

        // 여기서부터 if 문으로 액션 확인후 동작을 결정한다.
        if (action == KeyEvent.ACTION_DOWN) {
            check=1;
            Log.e("KEY","ACTIVE");
            Toast.makeText(context, "이어폰버튼눌림", Toast.LENGTH_SHORT).show();
        } else if(action == KeyEvent.KEYCODE_HEADSETHOOK)
        {
            Log.e("KEY","HOOKED");
        }
        else if(action == KeyEvent.ACTION_UP) {
            check = 0;
            Log.e("KEY","ACTIVE_UP");
            Toast.makeText(context, "이어폰버튼눌림", Toast.LENGTH_SHORT).show();

        }
        abortBroadcast();// 수신한 Broadcast를 지움으로써 다른 앱에서 인텐트 또 받아서 수행되는거 방지
    }

}



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView earphoneState = (TextView) findViewById(R.id.earphoneState);

        EarphoneKeyEvent mMediaButtonReceiver = new EarphoneKeyEvent();
        IntentFilter mediaFilter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
        // 기본 미디어 플레이어보다 높은 우선순위를 지정해 줍니다.
        mediaFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY + 1);
        registerReceiver(mMediaButtonReceiver, mediaFilter);

        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        ComponentName componentName = new ComponentName(this, EarphoneKeyEvent.class);
        audioManager.registerMediaButtonEventReceiver(componentName);

    }

}

