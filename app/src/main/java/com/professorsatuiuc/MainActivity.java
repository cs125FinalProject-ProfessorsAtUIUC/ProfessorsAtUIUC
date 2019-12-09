package com.professorsatuiuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

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
//        try (3){
//            CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(new InputStreamReader(is));
//            for (CSVRecord record: csvParser) {
//                String prof = record.get("Primary Instructor");
//                System.out.println(prof);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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


//        try (1){
//            Reader reader = new InputStreamReader(is);
//            CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
//            for (CSVRecord record: parser) {
//                String prof = record.get("Primary Instructor");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        Reader in = null;
//        try (2){
//            in = new FileReader("https://raw.githubusercontent.com/wadefagen/datasets/master/gpa/uiuc-gpa-dataset.csv");
//            Iterable<CSVRecord> records = null;
//            try {
//                records = CSVFormat.EXCEL.parse(in);
//                for (CSVRecord record : records) {
//                    String prof = record.get("Primary Instructor");
//                    System.out.println(prof);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
