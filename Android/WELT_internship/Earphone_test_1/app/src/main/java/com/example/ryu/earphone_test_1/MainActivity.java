package com.example.ryu.earphone_test_1;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    EarphoneReceiver earphoneReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earphoneReceiver = new EarphoneReceiver();
        IntentFilter mediaFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        mediaFilter.setPriority(1000);

        registerReceiver(earphoneReceiver,new IntentFilter(Intent.ACTION_HEADSET_PLUG));
        registerReceiver(earphoneReceiver,mediaFilter);


    }

    @Override
    public void onKeyDown(){

    }
}
