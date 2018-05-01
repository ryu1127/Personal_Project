package com.example.ryu.welt_record_23;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private MediaRecorder mediaRecorder = null;
    private String mFileName = "/dev/null";
    private int check =0;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private static final int REQUEST_RECORD_AUDIO_PERMISSIONS=200;
    private int controlHandler =0;
    Handler mHandler;

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch(requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSIONS:
                permissionToRecordAccepted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(!permissionToRecordAccepted) finish();

    }
    public class EarphoneButtonReceiver extends BroadcastReceiver {

        public EarphoneButtonReceiver(){
            super();
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            abortBroadcast();
            String intentAction = intent.getAction();
            if(!Intent.ACTION_MEDIA_BUTTON.equals(intentAction)){
                return;
            }
            KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if(event==null){
                return;
            }
            abortBroadcast();
        }
    }
public void startRecording(){
        TextView result = (TextView) findViewById(R.id.result);
        if(check==0&&mediaRecorder==null){
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(mFileName);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            try{
                mediaRecorder.prepare();
                mediaRecorder.start();
                result.setText("데시벨 체크중...");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                check=1;
            }
        }
    }
    public void stopRecording(){
        TextView result = (TextView) findViewById(R.id.result);
            try{
                mediaRecorder.stop();
                mediaRecorder.reset();
                mediaRecorder.release();
                result.setText("데시벨 측정정지");
                mediaRecorder=null;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                check=0;
            }
    }
    static double REFERENCE = 0.00002;
    public int getAmplitude(){
        double db = 0;
        double pressure = 0;
        if(mediaRecorder!=null){
            pressure = mediaRecorder.getMaxAmplitude()/51805.5336;
            db = (20*Math.log10(pressure/REFERENCE));
            if(db>0) return (int)db;
        }
        return 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EarphoneButtonReceiver r = new EarphoneButtonReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY+1);
        registerReceiver(r, filter);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,permissions,REQUEST_RECORD_AUDIO_PERMISSIONS);
        mHandler = new Handler(){
            public void handleMessage(Message msg){
                TextView result = (TextView) findViewById(R.id.result);
                TextView checkValue = (TextView) findViewById(R.id.checkButton);
                int ab = getAmplitude();
                if ( ab > 80) {
                    checkValue.setTextColor(Color.RED);
                }
                else checkValue.setTextColor(Color.BLACK);
                String a = Integer.toString(ab);
                checkValue.setText(a);
                mHandler.sendEmptyMessageDelayed(0,150);
            }
        };
        mHandler.sendEmptyMessage(0);
        Button recordButton = (Button) findViewById(R.id.record);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check==0) {
                    startRecording();
                }else{
                    stopRecording();
                }
            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        TextView result = (TextView) findViewById(R.id.checkButton);
        if(event.getAction()==KeyEvent.ACTION_DOWN&&event.getKeyCode()==KeyEvent.KEYCODE_VOLUME_DOWN){
            Toast.makeText(this,"volume down is pushed",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(event.getAction()==KeyEvent.ACTION_DOWN&&event.getKeyCode()== KeyEvent.KEYCODE_HEADSETHOOK){
            Toast.makeText(this,"headset is pushed",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(event.getKeyCode()==KeyEvent.KEYCODE_VOLUME_UP){
            Toast.makeText(this,"volume up is pushed",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(this,"Cant recognize yet",Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
