package com.professorsatuiuc;

import androidx.appcompat.app.AppCompatActivity;

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

public class SearchByProfessor extends AppCompatActivity {

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
        EditText searchProf_text = findViewById(R.id.searchProf_text);
        String keyword = searchProf_text.getText().toString();
        LinearLayout profList = findViewById(R.id.profList);//List in searching page.
        profList.removeAllViews();
        //gonna read csv by line here.Better do with a loop to delete String[] inside.
        //1. decide if the keyword is its subString
        //2. split and see if the keyword is in prof's name. If no then next line. If yes:
        //3. store the line somewhere, maybe in a map.
        //4. all prof's name will be needed to create button on searching page -> populate the chunk
        //5. the selected prof's data will be needed on displaying page, thus format it. -> show

        //start loop before this
        View profChunk = getLayoutInflater().inflate(R.layout.chunk_prof, profList, false);//the chunk layout
        Button aProf = profChunk.findViewById(R.id.aProf);//the button of prof
        aProf.setText("prof's name");//set the button's text
        profList.addView(profChunk);
        //end loop after this
    }
    public void searchProfessor(String name) {
        InputStream is = getResources().openRawResource(R.raw.gpa);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                String[] nameSplit = rowData[20].split(" ");
                if ()
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
