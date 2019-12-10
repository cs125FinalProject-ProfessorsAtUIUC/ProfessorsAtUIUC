package com.professorsatuiuc;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseDiaplay extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_display);
        Map<String, String> terms = new HashMap<>();
        LinearLayout disCourseList = findViewById(R.id.DisCourseList);
        String course = getIntent().getStringExtra("name");
        TextView courseText = findViewById(R.id.courseName);
        courseText.setText(course);
        ArrayList<String> datas = getIntent().getStringArrayListExtra("datas");

        for (String dataS : datas) {
            String[] data = dataS.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            String term = data[0] + " " + data[1];
            String toAdd = data[20] + "  A+:" + data[6] + " A:" + data[7] + " A-:" +
                    data[8] + " B+:" + data[9] + " B:" + data[10] + " B-:" + data[11] + " C+:" +
                    data[12] + " C:" + data[13] + " C-:" + data[14] + " D+:" + data[15] + " D:" +
                    data[16] + " D-:" + data[17] + " F:" + data[18] + " W:" + data[19] + "\n";
            if (terms.containsKey(term)) {
                terms.replace(term, terms.get(term) + toAdd);
            } else {
                terms.put(term, toAdd);
            }
       }

        for (Map.Entry<String,String> entry : terms.entrySet()) {
            View disCourseChunk = getLayoutInflater().inflate(R.layout.chunk_discourse, disCourseList, false);
            TextView term = disCourseChunk.findViewById(R.id.term);
            term.setText(entry.getKey());
            TextView data = disCourseChunk.findViewById(R.id.data);
            data.setText(entry.getValue());
            disCourseList.addView(disCourseChunk);
        }
    }
}
