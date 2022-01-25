package com.almaz.task1;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        List<T> resultCollection = new ArrayList<T>();

        if (firstList == null || secondList == null) {
            throw new NullPointerException("One of the arguments is null!");
        }
        resultCollection.addAll(firstList);
        resultCollection.addAll(secondList);
        resultCollection.sort(Collections.reverseOrder());

        return resultCollection;
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(@NonNull List<T> inputList) {
        List<T> resultList = new ArrayList<T>();

        if (inputList == null) {
            throw new NullPointerException("Argument is null!");
        }

        for (int i = 0; i < inputList.size(); i++) {
            resultList.add(inputList.get(i));
            resultList.addAll(inputList.subList(0, i));
        }
        return resultList;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        if (firstList == null || secondList == null) {
            throw new NullPointerException("One of the arguments is null");
        }
        return firstList.containsAll(secondList) && secondList.containsAll(firstList);
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(@NonNull List<T> inputList, int n) {
        if (inputList == null) {
            throw new NullPointerException("Argument is null!");
        }

        if (n == 0 || inputList.size() == 0) {
            return inputList;
        }

        List<T> tempList = new ArrayList<>(inputList);
        int size = tempList.size();
        T temperaryValue;

        if (n > 0) {
            for (int i = 0; i < n; i++) {
                temperaryValue = tempList.get(size - 1);
                tempList.remove(size - 1);
                tempList.add(0, temperaryValue);
            }
        }
        if (n < 0) {
            for (int i = size-1; i >0; i--) {
                temperaryValue = tempList.get(0);
                tempList.remove(0);
                tempList.add(size-1, temperaryValue);
            }
        }

        return tempList;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(@NonNull List<String> inputList, @NonNull String a,
                                        @NonNull String b) {
        if (inputList == null || a == null || b == null) {
            throw new NullPointerException("One of the arguments is null!");
        }
        return inputList.stream()
                .map(listItem -> listItem.equals(a) ? listItem = b : listItem)
                .collect(Collectors.toList());
    }

}
