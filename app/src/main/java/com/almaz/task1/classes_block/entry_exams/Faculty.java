package com.almaz.task1.classes_block.entry_exams;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Faculty {
    private String name;
    private double passingScore;
    private ArrayList<String> examsList;

    public Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(double passingScore) {
        this.passingScore = passingScore;
    }

    public ArrayList<String> getExamsSubjectsList() {
        return examsList;
    }

    public void setExamsSubjectsList(ArrayList<String> examsList) {
        this.examsList = examsList;
    }

    public double calcAverageScore(Map<String, Integer> exams) {
        return exams.entrySet().stream().collect(Collectors.averagingDouble(Map.Entry::getValue));
    }

    public void checkEnrolleeToEnter(Enrollee enrollee) {
        if (enrollee.getExamsScores() == null) {
            System.out.println("Exams not checked!");
        } else {
            if (calcAverageScore(enrollee.getExamsScores()) >= passingScore) {
                System.out.println("Enrollee " + enrollee + " have entered the university at the "
                        + this.name + ". Congratulations!");
            } else {
                System.out.println("Enrollee " + enrollee + " haven't entered the university. Sorry.");
            }
        }
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
