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

public class SearchByProfessor extends AppCompatActivity {

    private Map<String, LinkedList<String[]>>  professors = new HashMap<>();
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

        //gonna read csv by line here.Better do with a loop to delete String[] inside.
        //1. decide if the keyword is its subString
        //2. split and see if the keyword is in prof's name. If no then next line. If yes:
        //3. store the line somewhere, maybe in a map.
        //4. all prof's name will be needed to create button on searching page -> populate the chunk
        //5. the selected prof's data will be needed on displaying page, thus format it. -> show

    }
    public void searchProfessor(final String name) {
        LinearLayout profList = findViewById(R.id.profList);//List in searching page.
        profList.removeAllViews();
        final InputStream is = getResources().openRawResource(R.raw.gpa);


        try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String de = rowData[20];
                if (de.contains(name)) {
                    try {
                        professors.get(de).addFirst(rowData);
                    } catch (Exception e) {
                        LinkedList<String[]> toPut = new LinkedList<>();
                        toPut.add(rowData);
                        professors.put(de, toPut);
                    }
                    View profChunk = getLayoutInflater().inflate(R.layout.chunk_prof, profList, false);//the chunk layout
                    Button aProf = profChunk.findViewById(R.id.aProf);//the button of prof
                    //add onclick here for aProf
                    aProf.setText(de);//set the button's text
                    profList.addView(profChunk);
                    aProf.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(SearchByProfessor.this, ProfDisplay.class);
                            intent.putExtra("name", name);
                            LinkedList<String[]> datas = professors.get(name);
                            int i = 0;
                            while (datas != null) {
                                intent.putExtra(Integer.toString(i), datas.removeFirst());
                                i++;
                            }
                            intent.putExtra("lines", i);
                            startActivity(intent);
                        }
                    });
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
