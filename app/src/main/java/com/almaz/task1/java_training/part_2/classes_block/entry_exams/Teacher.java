package com.almaz.task1.java_training.part_2.classes_block.entry_exams;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Teacher extends Person {
    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
    }

    private final int MAX_SCORE = 10;

    public void checkEnrolleeExams(Enrollee enrollee) {
        if (enrollee.getExamsSubjectsList().size() == 0) {
            System.out.println("Enrollee not registered to entry exam!");
        } else {
            Random random = new Random();
            Map<String, Integer> checkedList = enrollee.getExamsSubjectsList().stream().
                    collect(Collectors.toMap(k -> k, v -> random.nextInt(MAX_SCORE)));
            enrollee.setExamsScore(checkedList);
        }
    }

}
