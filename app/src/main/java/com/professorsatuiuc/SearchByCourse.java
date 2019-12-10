package com.professorsatuiuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textservice.SpellCheckerSession;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchByCourse extends AppCompatActivity {

    SpellCheckerSession spellChecker;
    Button suggested_button;
    Button searchCourse_button;
    EditText searchCourse_text;
    LinearLayout courseList;
    private Map<String, ArrayList<String>>  courses = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_course);
        suggested_button = findViewById(R.id.suggested_button);
        searchCourse_button = findViewById(R.id.searchCourse_button);
        searchCourse_text = findViewById(R.id.searchCourse_text);
        courseList = findViewById(R.id.courseList);
        searchCourse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchingCourse();
            }
        });
    }

//    private void suggestingCourse() {
//        courseList.removeAllViews();
//        suggested_button.setOnClickListener(new View.OnClickListener() {
//            String keyword = searchCourse_text.getText().toString();
//            @Override
//            public void onClick(View view) {
//                searchCourse(keyword);
//            }
//        });
//    }

    private void searchingCourse() {
        courseList.removeAllViews();
        searchCourse_button.setOnClickListener(new View.OnClickListener() {
            String keyword = searchCourse_text.getText().toString();
            @Override
            public void onClick(View view) {
                searchCourse(keyword);
            }
        });
    }

    public void searchCourse(final String name) {
        LinearLayout courseList = findViewById(R.id.courseList);
        courseList.removeAllViews();
        final InputStream is = getResources().openRawResource(R.raw.gpa);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                final String de1 = rowData[3] + " " + rowData[4];
                final String de2 = rowData[3] + rowData[4];
                if (de1.toLowerCase().contains(name.toLowerCase())
                        || de2.toLowerCase().contains(name.toLowerCase())) {
                    try {
                        courses.get(de1).add(line);
                    } catch (Exception e) {
                        ArrayList<String> toPut = new ArrayList<>();
                        toPut.add(line);
                        courses.put(de1, toPut);
                        View courseChunk = getLayoutInflater().inflate(R.layout.chunk_course, courseList, false);
                        Button aCourse = courseChunk.findViewById(R.id.aCourse);
                        aCourse.setText(de1);
                        courseList.addView(courseChunk);
                        aCourse.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(SearchByCourse.this, CourseDisplay.class);
                                intent.putExtra("name", de1);
                                intent.putExtra("datas", courses.get(de1));
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
