package com.almaz.task1.students_task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class StudentsList {
    public static void fillStudentsList(ArrayList<Student> studentsList) {
        studentsList.add(new Student("Ivanov", "Ivan", "Ivanovich", 1997, 2, 201, 4, 5, 4, 5, 4));
        studentsList.add(new Student("Petrov", "Petr", "Petrovich", 1996, 2, 202, 5, 4, 5, 4, 5));
        studentsList.add(new Student("Fedulov", "Nikita", "Nikolaevich", 1995, 3, 302, 3, 4, 5, 3, 3));
        studentsList.add(new Student("Avdeenko", "Igor", "Vladimirovich", 1996, 2, 201, 3, 4, 4, 3, 3));
        studentsList.add(new Student("Savelev", "Sergey", "Vyacheslavovich", 1996, 3, 302, 5, 5, 5, 5, 5));
    }

    public static void sortStudentsList(ArrayList<Student> studentsList) {
        studentsList.sort(Comparator.comparing(Student::getCourse).thenComparing(Student::getLastName));
    }

    public static Map<Integer, Double> getAverageGradeByGroup(ArrayList<Student> studentsList, ToIntFunction<Student> subject) {
        return studentsList.stream().collect(Collectors.groupingBy(Student::getGroupNumber, Collectors.averagingInt(subject)));
    }

    public static Student getOldestStudent(ArrayList<Student> studentsList) {
        return studentsList.stream().min(Comparator.comparing(Student::getBirthYear)).orElse(null);
    }

    public static Student getYoungestStudent(ArrayList<Student> studentsList) {
        return studentsList.stream().max(Comparator.comparing(Student::getBirthYear)).orElse(null);
    }

    public static Map<Integer, Student> getTopStudent(ArrayList<Student> studentsList) {
        int maxSumGrade;
        Student topStudent;

        Map<Integer, Student> topStudents = new HashMap<Integer, Student>() {
        };

        Map<Integer, List<Student>> groups = studentsList.stream().
                collect(Collectors.groupingBy(Student::getGroupNumber));

        for (Map.Entry<Integer, List<Student>> group : groups.entrySet()) {
            maxSumGrade = 0;
            topStudent = null;
            for (Student student : group.getValue()) {
                int sumGrade = student.getMathGrade() +
                        student.getEnglishGrade() +
                        student.getBiologyGrade() +
                        student.getHistoryGrade() +
                        student.getChemistryGrade();
                if (sumGrade > maxSumGrade) {
                    maxSumGrade = sumGrade;
                    topStudent = student;
                }
            }
            topStudents.put(group.getKey(), topStudent);
        }
        return topStudents;
    }


}
