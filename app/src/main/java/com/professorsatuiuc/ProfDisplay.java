package com.professorsatuiuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_display);
        //intent 拉数据

        //textview 改 prof name
        TextView profName = findViewById(R.id.profName);
        profName.setText("Geoff Challen");//改名

        //load data

    }
}
