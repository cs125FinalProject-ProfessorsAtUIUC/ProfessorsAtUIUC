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
import java.util.ArrayList;
import java.util.Map;

public class SearchByProfessor extends AppCompatActivity {

    private Map<String, ArrayList<String>>  professors = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_professor);
        //点击search button将开始searchingProf程序
        Button searchProf_button = findViewById(R.id.searchProf_button);
        searchProf_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchingProf();
            }
        });
    }

    /**
     * click search will start this. search through cvs and load buttons in searching page.
     * will also load and store profs' data in a data structure.
     * display要不换个程序再写
     */
    private void searchingProf() {
        //get the text & store in #keyword
        final EditText searchProf_text = findViewById(R.id.searchProf_text);

        LinearLayout profList = findViewById(R.id.profList);//List in searching page.
        profList.removeAllViews();

        Button searchProfButton = findViewById(R.id.searchProf_button);
        searchProfButton.setOnClickListener(new View.OnClickListener() {
            String keyword = searchProf_text.getText().toString();
            @Override
            public void onClick(View view) {
                searchProfessor(keyword);
            }
        });
    }

    /**
     * after clicked search.
     * really searching.
     * @param name name of the prof.
     */
    public void searchProfessor(final String name) {
        LinearLayout profList = findViewById(R.id.profList);//List in searching page.
        profList.removeAllViews();
        final InputStream is = getResources().openRawResource(R.raw.gpa);
        try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                final String prof = rowData[20];
                if (prof.toLowerCase().contains(name.toLowerCase())) {
                    try {
                        professors.get(prof).add(line);
                    } catch (Exception e) {
                        ArrayList<String> toPut = new ArrayList<>();
                        toPut.add(line);
                        professors.put(prof, toPut);
                        View profChunk = getLayoutInflater().inflate(R.layout.chunk_prof, profList, false);//the chunk layout
                        Button aProf = profChunk.findViewById(R.id.aProf);//the button of prof
                        aProf.setText(prof);
                        profList.addView(profChunk);
                        aProf.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(SearchByProfessor.this, ProfDisplay.class);
                                intent.putExtra("name", prof);
                                intent.putExtra("datas", professors.get(prof));
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
