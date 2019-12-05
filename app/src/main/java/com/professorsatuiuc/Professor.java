package com.professorsatuiuc;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.Reader;

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
    public static void searchProfessor() {
        Reader in = null;
        try {
            in = new FileReader("raw.githubusercontent.com/wadefagen/datasets/master/gpa/uiuc-gpa-dataset.csv");
            Iterable<CSVRecord> records = null;
            try {
                records = CSVFormat.EXCEL.parse(in);
                for (CSVRecord record : records) {
                    String prof = record.get("Primary Instructor");
                    System.out.println(prof);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
