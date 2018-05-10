package com.example.ryu.accountjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] accounts = {"123-456-7890","123-123-1234","111-111-1111"};

        ListView list = (ListView) findViewById(R.id.account_list);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,accounts);

        list.setAdapter(adapter);


    }
}
