package com.example.ryu.welt_record_23;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder = null;
    private String mFileName = "/dev/null";
    private int check =0;

    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private static final int REQUEST_RECORD_AUDIO_PERMISSIONS=200;
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

    public void Recording(){
        if(check==0&&mediaRecorder==null){
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(mFileName);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try{
                mediaRecorder.prepare();
                mediaRecorder.start();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                check=1;
            }
        }
        else{
            try{
                mediaRecorder.stop();
                mediaRecorder.reset();
                mediaRecorder.release();
                mediaRecorder=null;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                check=0;
            }
            TextView result = (TextView) findViewById(R.id.result);
            result.setText("Finish");
        }
    }

    public int getAmplitude(){
        if(mediaRecorder!=null){
            return mediaRecorder.getMaxAmplitude();
        }
        else return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,permissions,REQUEST_RECORD_AUDIO_PERMISSIONS);
        mHandler = new Handler(){
            public void handleMessage(Message msg){
                String a = Integer.toString(getAmplitude());
                TextView result = (TextView) findViewById(R.id.result);
                result.setText(a);
                mHandler.sendEmptyMessageDelayed(0,100);
            }
        };
        mHandler.sendEmptyMessage(0);

        Button recordButton = (Button) findViewById(R.id.record);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recording();
            }
        });


    }

}
