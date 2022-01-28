package com.almaz.task1.java_training.part_2.classes_block.entry_exams;

import java.util.ArrayList;
import java.util.Map;

    /*
      VI

      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */

public class Enrollee extends Person {
    private final ArrayList<String> examsSubjectsList;
    private Map<String, Integer> examsScore;

    public Enrollee(String firstName, String lastName) {
        super(firstName, lastName);
        this.examsSubjectsList = new ArrayList<>();
    }

    public void registerForEnroll(Faculty faculty) {
        examsSubjectsList.clear();
        examsSubjectsList.addAll(faculty.getExamsSubjectsList());
    }

    public ArrayList<String> getExamsSubjectsList() {
        return this.examsSubjectsList;
    }

    public Map<String, Integer> getExamsScores() {
        return this.examsScore;
    }

    public void setExamsScore(Map<String, Integer> examsScore) {
        this.examsScore = examsScore;
    }
}
