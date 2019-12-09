package com.professorsatuiuc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class ProfDisplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_display);
        Map<String, String> courses = new HashMap<>();
        LinearLayout disProfList = findViewById(R.id.DisProfList);
        //intent 拉name 改 textView
        String prof = getIntent().getStringExtra("prof");
        TextView profName = findViewById(R.id.profName);
        profName.setText(prof);//改名
        int lineNumber = getIntent().getIntExtra("lines", 1);
        for (int i = 0; i < lineNumber; i++) {
            String[] data = getIntent().getStringArrayExtra(Integer.toString(i));
            String courseName = data[3] + data[4] + " " + data[5];
            String toAdd = data[0] + " " + data[1] + "  A+:" + data[6] + " A:" + data[7] + " A-:" +
                    data[8] + " B+:" + data[9] + " B:" + data[10] + " B-:" + data[11] + " C+:" +
                    data[12] + " C:" + data[13] + " C-:" + data[14] + " D+:" + data[15] + " D:" +
                    data[16] + " D-:" + data[17] + " F:" + data[18] + " W:" + data[19] + "\n";
            try {
                courses.replace(courseName, courses.get(courseName) + toAdd);
            } catch (Exception e) {
                courses.put(courseName,toAdd);
            }
        }
        for (Map.Entry<String,String> entry : courses.entrySet()) {
            View disProfChunk = getLayoutInflater().inflate(R.layout.chunk_disprof, disProfList, false);
            TextView course = disProfChunk.findViewById(R.id.course);
            course.setText(entry.getKey());
            TextView data = disProfChunk.findViewById(R.id.data);
            data.setText(entry.getValue());
            disProfList.addView(disProfChunk);
        }
    }
}
