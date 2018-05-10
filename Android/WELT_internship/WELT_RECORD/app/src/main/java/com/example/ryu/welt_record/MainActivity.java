package com.example.ryu.welt_record;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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
    double x =0;
    double db= 0;



    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
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
            mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);    //예민한건 MIC가 훨씬 예민 너무 최고치를 찍어서 변경
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//            mRecorder.setOutputFile(mFileName);
            mRecorder.setOutputFile("/dev/null");
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "failed prepare()");
            }
            mRecorder.start();
            check=1;
            TextView Result = (TextView) findViewById(R.id.result);
//            Result.setText("Recording Now...");


        }
        else{
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            check=0;
            TextView Result = (TextView) findViewById(R.id.result);
            Result.setText("Finish");
        }
r

    public double getAmplitude(){
        if(mRecorder != null){

            x = mRecorder.getMaxAmplitude() / 51805.3665;
            db = (20*Math.log10(x/0.00002));
            return db;
        }



        else return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/record_test_3.3gp";
        setContentView(R.layout.activity_main);


        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        final TextView Result = (TextView) findViewById(R.id.result);
        mHandler = new android.os.Handler() {
            public void handleMessage(Message msg) {
//                String a = Integer.toString(getAmplitude());
//                Result.setText(a);
//                mHandler.sendEmptyMessageDelayed(0,100);
                String a = Double.toString(getAmplitude());
                Result.setText(a);
                mHandler.sendEmptyMessageDelayed(0, 100);

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