package com.example.ryu.record_test_3;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.util.LogWriter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import android.os.*;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mRecorder = null;
    private String mFileName = null;
    private String LOG_TAG = "record_test_3";
    private int check = 0;

    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private Timer mTimer;
    private TimerTask mTask;
    android.os.Handler mHandler;
    double db=0;
    double x =0;



    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(srequestCode,permissions,grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(!permissionToRecordAccepted) finish();
    }

    public void startRecording(){
        if(check==0&&mRecorder==null) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//            mRecorder.setOutputFile(mFileName);
            mRecorder.setOutputFile("/dev/null");
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "failed prepare()");
            }
            try {
                mRecorder.start();
            }catch(Exception e){
                Log.e(LOG_TAG, String.valueOf(e));
            }finally {
                check = 1;
            }
            TextView Result = (TextView) findViewById(R.id.result);
//            Result.setText("Recording Now...");


        }
        else{
            try {
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
            }catch(Exception e){
                Log.e(LOG_TAG, String.valueOf(e));
            }finally {
                check = 0;
            }
            TextView Result = (TextView) findViewById(R.id.result);
            Result.setText("Finish");
        }
    }

    public double getAmplitude(){
        if(mRecorder != null) {
//            x = mRecorder.getMaxAmplitude() / 51805.5336;
//            db = (20*Math.log10(x/0.00002));
//            return db;
            return mRecorder.getMaxAmplitude();
        }
        else return 0;
    }




//    public boolean onKeyDown(int KeyCode, KeyEvent event ){
//        TextView volume = (TextView) findViewById(R.id.volume);
//        switch (KeyCode){
//            case KeyEvent.KEYCODE_VOLUME_DOWN:
//                volume.setText("Volume Down Select!");
//                return true;
//
//            case KeyEvent.KEYCODE_VOLUME_UP:
//                volume.setText("Volume UP Select!");
//                return true;
//
//
//        }
//        return false;
//    }

    public class EarphoneKeyEvent extends BroadcastReceiver{
        public EarphoneKeyEvent(){
            super();
        }

        @Override
        public void onReceive(Context context, Intent intent) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setTitle("Headset State");

            TextView volume = (TextView) findViewById(R.id.volume);

            String intentAction = intent.getAction();
            if(!Intent.ACTION_MEDIA_BUTTON.equals(intentAction)){
                return;
            }
            if(intent.getIntExtra("state",0)==0){
                Toast.makeText(context,"Earphones OFF",Toast.LENGTH_SHORT).show();
            }
            if(intent.getIntExtra("state",0)==1){
                Toast.makeText(context,"Earphones ON",Toast.LENGTH_SHORT).show();
            }

            KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if(event==null) return;


            int action = event.getAction();

            if(event.getKeyCode()==KeyEvent.ACTION_DOWN){

//                builder.setMessage("Volume Down Clicked!");
//                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder.create().show();
                volume.setText("Volume Down Select!");

            }
            else if(event.getKeyCode()==KeyEvent.ACTION_UP){

//                builder.setMessage("Volume Up Clicked!");
//                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder.create().show();
                volume.setText("Volume Up Select!");
            }
            else{
                abortBroadcast();
            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/record_test_3.3gp";
        setContentView(R.layout.activity_main);

        EarphoneKeyEvent mMediaButtonReceiver = new EarphoneKeyEvent();
        IntentFilter mediaFilter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);

        //기본 미디어 플레이어보다 시스템적 순위를 높여준다.
        mediaFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY+1);
        registerReceiver(mMediaButtonReceiver,mediaFilter);

        ComponentName componentName = new ComponentName(this,EarphoneKeyEvent.class);
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.registerMediaButtonEventReceiver(componentName);


        ActivityCompat.requestPermissions(this,permissions,REQUEST_RECORD_AUDIO_PERMISSION);

        final TextView Result = (TextView) findViewById(R.id.result);
        mHandler = new android.os.Handler(){
            public void handleMessage(Message msg){

                String a = Double.toString(getAmplitude());
                Result.setText(a);
                mHandler.sendEmptyMessageDelayed(0, 100);
ar
            }
        };
        mHandler.sendEmptyMessage(0);



        Button recordButton = (Button) findViewById(R.id.record);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });







    }

}

