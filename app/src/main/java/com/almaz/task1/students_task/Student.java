package com.almaz.task1.students_task;

/*
  Задание подразумевает создание класса(ов) для выполнения задачи.

  Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
  курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
  Упорядочите студентов по курсу, причем студенты одного курса располагались
  в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
  Определите самого старшего студента и самого младшего студентов.
  Для каждой группы найдите лучшего с точки зрения успеваемости студента.
 */
public class Student {
    private String firstName;
    private String lastName;
    private String middleName;
    private int birthYear;
    private int course;
    private int groupNumber;
    private int historyGrade;
    private int englishGrade;
    private int mathGrade;
    private int biologyGrade;
    private int chemistryGrade;

    public Student(String lastName,
                   String firstName,
                   String middleName,
                   int birthYear,
                   int course,
                   int groupNumber,
                   int historyGrade,
                   int englishGrade,
                   int mathGrade,
                   int biologyGrade,
                   int chemistryGrade) {
        setLastName(lastName);
        setFirstName(firstName);
        setMiddleName(middleName);
        setBirthYear(birthYear);
        setCourse(course);
        setGroupNumber(groupNumber);
        setHistoryGrade(historyGrade);
        setEnglishGrade(englishGrade);
        setMathGrade(mathGrade);
        setBiologyGrade(biologyGrade);
        setChemistryGrade(chemistryGrade);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getHistoryGrade() {
        return historyGrade;
    }

    public void setHistoryGrade(int historyGrade) {
        this.historyGrade = historyGrade;
    }

    public int getEnglishGrade() {
        return englishGrade;
    }

    public void setEnglishGrade(int englishGrade) {
        this.englishGrade = englishGrade;
    }

    public int getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(int mathGrade) {
        this.mathGrade = mathGrade;
    }

    public int getBiologyGrade() {
        return biologyGrade;
    }

    public void setBiologyGrade(int biologyGrade) {
        this.biologyGrade = biologyGrade;
    }

    public int getChemistryGrade() {
        return chemistryGrade;
    }

    public void setChemistryGrade(int chemistryGrade) {
        this.chemistryGrade = chemistryGrade;
    }
}
