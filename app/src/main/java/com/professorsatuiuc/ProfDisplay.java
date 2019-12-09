package com.professorsatuiuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.commons.csv.CSVRecord;

public class ProfDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_display);
        //intent 拉name 改 textView
        String prof = getIntent().getStringExtra("prof");
        TextView profName = findViewById(R.id.profName);
        profName.setText(prof);//改名
        //load other data from intent
        //maybe use a List<T>
    }
}
