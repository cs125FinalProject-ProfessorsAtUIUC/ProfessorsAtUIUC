package com.professorsatuiuc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Professor {
    private String name;
    private String courseNum;
    private String courseTitle;
    private String yearTerm;
    private String grade;
    public Professor(String name, String courseNum, String courseTitle, String yearTerm, String grade) {
        this.name = name;
        this.courseNum = courseNum;
        this.courseTitle = courseTitle;
        this.yearTerm = yearTerm;
        this.grade = grade;
    }
    public static void sortProfessor() {
        String fileName = "uiuc-gpa-dataset.csv";
        File file = new File(fileName);
        List<Professor> professorList= new ArrayList<Professor>();
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split(",");
                String courseNum =  values[3] + " " + values[4];
                String grade = "A+: " + values[6] + ", A: " + values[7] + ", A-: " + values[8] + ", B+: "
                        + values[9] + ", B: " + values[10] + ", B-: " + values[11] + ", C+: " + values[12]
                        + ",  C: " + values[13] + ", C-: " + values[14] + ", D+: " + values[15] + ", D: "
                        + values[16] + ", D-: " + values[17] + ", F: " + values[18] + ", W: " + values[19];
                professorList.add(new Professor(values[20], courseNum, values[5], values[2], grade));
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
