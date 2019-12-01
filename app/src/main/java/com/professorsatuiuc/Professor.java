package com.professorsatuiuc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Professor {
    private String name;
    private String subject;
    private String courseNum;
    private String courseTitle;
    private String yearTerm;
    private String grade;
    public Professor(String name, String subject, String courseNum, String courseTitle, String yearTerm, String grade) {
        this.name = name;
        this.subject = subject;
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
                String grade = values[6] + ", " + values[7] + ", " + values[8] + ", " + values[9] + ", "
                        + values[10] + ", " + values[11] + ", " + values[12] + ",  " + values[13] + ", "
                        + values[14] + ", " + values[15] + ", " + values[16] + ",  " + values[17] + ", "
                        + values[18] + ", " + values[19];
                professorList.add(new Professor(values[20], values[3], values[4],
                        values[5], values[2], grade));
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
