package com.professorsatuiuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SearchByCourse extends AppCompatActivity {

    private Map<String, LinkedList<String[]>>  courses = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_course);
        Button searchCourse_button = findViewById(R.id.searchCourse_button);
        searchCourse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchingCourse();
            }
        });
    }

    private void searchingCourse() {
        final EditText searchCourse_text = findViewById(R.id.searchCourse_text);
        LinearLayout courseList = findViewById(R.id.courseList);//List in searching page.
        courseList.removeAllViews();
        Button searchCourseButton = findViewById(R.id.searchCourse_button);
        searchCourseButton.setOnClickListener(new View.OnClickListener() {
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
                String de1 = rowData[3] + " " + rowData[4];
                String de2 = rowData[3] + rowData[4];
                if (de1.contains(name) || de2.contains(name)) {
                    try {
                        courses.get(de1).addFirst(rowData);
                    } catch (Exception e) {
                        LinkedList<String[]> toPut = new LinkedList<>();
                        toPut.add(rowData);
                        courses.put(de1, toPut);
                    }
                    View courseChunk = getLayoutInflater().inflate(R.layout.chunk_course, courseList, false);
                    Button aCourse = courseChunk.findViewById(R.id.aCourse);
                    aCourse.setText(de1);
                    courseList.addView(courseChunk);
//                    aProf.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent = new Intent(SearchByProfessor.this, ProfDisplay.class);
//                            intent.putExtra("name", name);
//                            LinkedList<String[]> datas = professors.get(name);
//                            int i = 0;
//                            while (datas != null) {
//                                intent.putExtra(Integer.toString(i), datas.removeFirst());
//                                i++;
//                            }
//                            intent.putExtra("lines", i);
//                            startActivity(intent);
//                        }
//                    });
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
