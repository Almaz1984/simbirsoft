package com.almaz.task1;

import com.almaz.task1.java_training.part_2.classes_block.DinamicArray;
import com.almaz.task1.java_training.part_2.classes_block.MyTime;
import com.almaz.task1.java_training.part_2.classes_block.entry_exams.Enrollee;
import com.almaz.task1.java_training.part_2.classes_block.entry_exams.Faculty;
import com.almaz.task1.java_training.part_2.classes_block.entry_exams.Teacher;
import com.almaz.task1.java_training.part_2.classes_block.internet_store.Customer;
import com.almaz.task1.java_training.part_2.classes_block.internet_store.Product;
import com.almaz.task1.java_training.part_2.classes_block.internet_store.Store;
import com.almaz.task1.java_training.part_2.classes_block.subscriber_task.Subscriber;
import com.almaz.task1.java_training.part_2.classes_block.subscriber_task.SubscribersList;
import com.almaz.task1.java_training.part_2.classes_block.triangle.Triangle;
import com.almaz.task1.java_training.part_2.students_task.Student;
import com.almaz.task1.java_training.part_2.students_task.StudentsList;
import com.almaz.task1.java_training.part_2.PracticeBlock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class JavaTrainPart2Tests {
    /*
    Практические задания. Задание 2.
     */
    @Test
    public void lambdaTest() {
        PracticeBlock.Lambda.main();
    }

    /*
    Практические задания. Задание 3.
     */
    @Test
    public void directionsTest() {
        PracticeBlock.Directions[] directionsArray = (new PracticeBlock.Directions[]
                {PracticeBlock.Directions.UP,
                PracticeBlock.Directions.UP,
                PracticeBlock.Directions.LEFT,
                PracticeBlock.Directions.DOWN,
                PracticeBlock.Directions.LEFT,
                PracticeBlock.Directions.DOWN,
                PracticeBlock.Directions.DOWN,
                PracticeBlock.Directions.RIGHT,
                PracticeBlock.Directions.RIGHT,
                PracticeBlock.Directions.DOWN,
                PracticeBlock.Directions.RIGHT});
        PracticeBlock.DirectionsTask.moveByDirectionList(directionsArray);
    }

    /*
    Коллекции. Задание 5.
     */
    @Test
    public void studentTest() {
        ArrayList<Student> studentsList = new ArrayList<>();
        StudentsList.fillStudentsList(studentsList);

        System.out.println("\nBefore sort:");
        for (Student student : studentsList) {
            System.out.println(student.getLastName() + " " + student.getCourse());
        }

        System.out.println("\nAfter sort:");
        StudentsList.sortStudentsList(studentsList);
        for (Student student : studentsList) {
            System.out.println(student.getLastName() + " " + student.getCourse());
        }

        System.out.println("\nAverage history grade by group: ");
        StudentsList.getAverageGradeByGroup(studentsList, Student::getHistoryGrade).
                forEach((groupNumber, averageGrade) -> System.out.println("Group number:" + groupNumber + " Average grade: " + averageGrade));

        System.out.println("\nAverage english grade by group: ");
        StudentsList.getAverageGradeByGroup(studentsList, Student::getEnglishGrade).
                forEach((groupNumber, averageGrade) -> System.out.println("Group number:" + groupNumber + " Average grade: " + averageGrade));

        System.out.println("\nAverage math grade by group: ");
        StudentsList.getAverageGradeByGroup(studentsList, Student::getMathGrade).
                forEach((groupNumber, averageGrade) -> System.out.println("Group number:" + groupNumber + " Average grade: " + averageGrade));

        System.out.println("\nAverage biology grade by group: ");
        StudentsList.getAverageGradeByGroup(studentsList, Student::getBiologyGrade).
                forEach((groupNumber, averageGrade) -> System.out.println("Group number:" + groupNumber + " Average grade: " + averageGrade));

        System.out.println("\nAverage chemistry grade by group: ");
        StudentsList.getAverageGradeByGroup(studentsList, Student::getChemistryGrade).
                forEach((groupNumber, averageGrade) -> System.out.println("Group number:" + groupNumber + " Average grade: " + averageGrade));

        System.out.println("\nOldest student:" + StudentsList.getOldestStudent(studentsList).getLastName());

        System.out.println("\nYoungest student:" + StudentsList.getYoungestStudent(studentsList).getLastName());

        StudentsList.getTopStudent(studentsList).
                forEach((groupNumber, student) ->
                        System.out.println("\nGroup number: " + groupNumber + " Top student:" + student.getLastName()));

    }

    /*
    Классы. Задание 2.
     */
    @Test
    public void dinamicArrayTest() {
        DinamicArray dinamicArray = new DinamicArray(30);
        dinamicArray.fillArrayWithRandomNumbers();
        System.out.println("\nBefore mix:");
        dinamicArray.printArray();
        dinamicArray.mixArray();
        System.out.println("\nAfter mix:");
        dinamicArray.printArray();
        System.out.println("\nItem count 5:" + dinamicArray.getSpecificItemCount(5));

    }

    /*
    Классы. Задание 3.
     */
    @Test
    public void triangleTest() {
        Triangle triangle = new Triangle(1.0, 1.0, -2.0, 4.0, -2.0, -2.0);
        System.out.println(triangle.toString());

    }

    /*
    Классы. Задание 4.
     */
    @Test
    public void timeTest() {
        MyTime timeValue = new MyTime(12, 30, 40);
        System.out.println("Add 123 Seconds:");
        timeValue.addSecond(123);
        System.out.println("Seconds:" + timeValue.getSecond());
        System.out.println("Minutes:" + timeValue.getMinute());
        System.out.println("Hours:" + timeValue.getHour());
        System.out.println("\nAdd 123 Minutes:");
        timeValue.addMinute(123);
        System.out.println("Seconds:" + timeValue.getSecond());
        System.out.println("Minutes:" + timeValue.getMinute());
        System.out.println("Hours:" + timeValue.getHour());
    }

    /*
    Классы. Задание 5.
     */
    @Test
    public void subscriberTest() {
        ArrayList<Subscriber> subscribersList = new ArrayList<>();
        SubscribersList.fillSubscribersList(subscribersList);

        System.out.println("Subscribers list with exceeded local talks limit:");
        ArrayList<Subscriber> subscribersListWithExceededInternationalTime =
                SubscribersList.getSubscribersExceededLocalTimeTalks(20, subscribersList);
        for (Subscriber subscriber : subscribersListWithExceededInternationalTime) {
            System.out.println(subscriber);
        }

        System.out.println("\nSubscribers list who used long distance talks:");
        ArrayList<Subscriber> subscribersListUsedLongDistanceTalks =
                SubscribersList.getSubscribersUsedLongDistanceTalks(subscribersList);
        for (Subscriber subscriber : subscribersListUsedLongDistanceTalks) {
            System.out.println(subscriber);
        }

        System.out.println("\nSubscribers sort alphabetically:");
        SubscribersList.sortSubscribersAlphabetically(subscribersList);
        for (Subscriber subscriber : subscribersList) {
            System.out.println(subscriber);
        }

    }

    /*
    Классы. Задание 6.
     */
    @Test
    public void entryExamsTest() {
        Enrollee student = new Enrollee("Ivan", "Petrov");
        Teacher teacher = new Teacher("Vladimir", "Anisimov");
        Faculty faculty = new Faculty("Computer Science and Robotics");
        ArrayList<String> subjectsList = new ArrayList<>(Arrays.asList("Math", "Physics", "Informatics"));
        faculty.setExamsSubjectsList(subjectsList);
        faculty.setPassingScore(5);
        student.registerForEnroll(faculty);
        teacher.checkEnrolleeExams(student);
        faculty.checkEnrolleeToEnter(student);
    }

    /*
    Классы. Задание 7.
     */
    @Test
    public void internetStoreTest() {
        Customer customerIvanov = new Customer(0, "Ivan", "Ivanov", 20000);
        Customer customerPetrov = new Customer(1, "Petr", "Petrov", 200000);
        Store amazonStore = new Store("Amazon");
        Store.Salesman salesman = new Store.Salesman("Vladimir", "Popov");

        salesman.addProductToStore(amazonStore, new Product("Iphone 13", 80000));
        salesman.addProductToStore(amazonStore, new Product("Airpods pro", 10000));
        salesman.addProductToStore(amazonStore, new Product("Asus notebook", 60000));
        salesman.addProductToStore(amazonStore, new Product("Samsung galaxy s21", 65000));
        salesman.addProductToStore(amazonStore, new Product("Honor magicbook pro ", 70000));

        System.out.println("");
        customerIvanov.buyProduct(amazonStore, new ArrayList<>(Arrays.asList("Iphone 13")));
        customerIvanov.buyProduct(amazonStore, new ArrayList<>(Arrays.asList("Airpods pro")));
        customerIvanov.buyProduct(amazonStore, new ArrayList<>(Arrays.asList("Airpods")));
        customerIvanov.payForOrder(amazonStore);
        salesman.checkOrders(amazonStore, customerIvanov);
        customerIvanov.buyProduct(amazonStore, new ArrayList<>(Arrays.asList("Airpods")));

        System.out.println("");
        customerPetrov.buyProduct(amazonStore, new ArrayList<>(Arrays.asList("Asus notebook")));
        customerPetrov.buyProduct(amazonStore, new ArrayList<>(Arrays.asList("Iphone 13", "Airpods pro")));
        customerPetrov.payForOrder(amazonStore);
        salesman.checkOrders(amazonStore, customerPetrov);

    }
}
