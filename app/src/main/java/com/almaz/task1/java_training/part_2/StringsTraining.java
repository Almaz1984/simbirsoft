package com.almaz.task1.java_training.part_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     * (нумерация символов идет с нуля)
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public String getOddCharacterString(String text) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if (i % 2 != 0) {
                stringBuilder.append(text.charAt(i));
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public int[] getArrayLastSymbol(String text) {
        ArrayList<Integer> listLastSymbol = new ArrayList<>();
        int[] arrayLastSymbol;

        if (text.length() <= 0) {
            arrayLastSymbol = new int[]{};
        } else {
            char letter;
            letter = text.charAt(text.length() - 1);
            for (int i = 0; i < text.length() - 1; i++) {
                if (letter == text.charAt(i)) {
                    listLastSymbol.add(i);
                }
            }
            arrayLastSymbol = listLastSymbol.stream().mapToInt(i -> i).toArray();
        }

        return arrayLastSymbol;
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public int getNumbersCount(String text) {
        int numersCount = 0;

        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                numersCount++;
            }
        }
        return numersCount;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цифры заменены словами
     */
    public String replaceAllNumbers(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, String> numerals = new HashMap<>();
        int digit;

        numerals.put(0, "zero");
        numerals.put(1, "one");
        numerals.put(2, "two");
        numerals.put(3, "three");
        numerals.put(4, "four");
        numerals.put(5, "five");
        numerals.put(6, "six");
        numerals.put(7, "seven");
        numerals.put(8, "eight");
        numerals.put(9, "nine");

        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                digit = Character.getNumericValue(text.charAt(i));
                stringBuilder.append(numerals.get(digit));
            } else stringBuilder.append(text.charAt(i));
        }

        return stringBuilder.toString();
    }

    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public String capitalReverse(String text) {
        StringBuilder stringBuilder = new StringBuilder(text);
        char letter;

        for (int i = 0; i < text.length(); i++) {
            letter = Character.isUpperCase(text.charAt(i)) ? Character.toLowerCase(text.charAt(i)) :
                    Character.toUpperCase(text.charAt(i));
            stringBuilder.replace(i, i + 1, Character.toString(letter));
        }

        return stringBuilder.toString();
    }

}
