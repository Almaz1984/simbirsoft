package com.almaz.task1.classes_block;

import java.util.ArrayList;
import java.util.Random;

/*
      II

      Создать класс, содержащий динамический массив и количество элементов в нем.
      Добавить конструктор, который выделяет память под заданное количество элементов.
      Добавить методы, позволяющие заполнять массив случайными числами,
      переставлять в данном массиве элементы в случайном порядке, находить количество
      различных элементов в массиве, выводить массив на экран.
     */

public class DinamicArray {
    ArrayList<Integer> array;
    int size;

    public DinamicArray(int size) {
        this.size = size;
        array = new ArrayList<>(size);
    }

    public void fillArrayRandomNumbers() {
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array.add(i, random.nextInt(10));
        }
    }

    public void mixArrayRandom() {
        int tempValue;
        int randomIndex;
        int currentIndex;
        Random random = new Random();

        for (currentIndex = size - 1; currentIndex >= 1; currentIndex--) {
            randomIndex = random.nextInt(currentIndex + 1);
            tempValue = array.get(randomIndex);
            array.set(randomIndex, array.get(currentIndex));
            array.set(currentIndex, tempValue);
        }
    }

    public int getItemCount(int item) {
        int count = 0;

        for (int arrayValue : array) {
            if (arrayValue == item) {
                count++;
            }
        }
        return count;
    }

    public void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.println("Array[" + i + "]=" + array.get(i));
        }
    }

}
