package com.professorsatuiuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class

MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //跳到search by professor页
        Button toSearchProf = findViewById(R.id.toSearchProf);
        searchProfessor();
        toSearchProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchByProfessor.class));
            }
        });
    }

    public void searchProfessor() {
        InputStream is = getResources().openRawResource(R.raw.letmesee);
        try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        Reader in = new FileReader();
//        Iterable<CSVRecord> records = null;
//        try {
//            records = CSVFormat.EXCEL.parse(in);
//            for (CSVRecord record : records) {
//                String prof = record.get("Primary Instructor");
//                System.out.println(prof);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                System.out.println(rowData[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
