package com.almaz.task1.kotlin_training.part_1

import android.util.Log
import com.almaz.task1.TAG

object Part1 {
    fun run() {
        /* 2
            Создать два объекта класса Book и один объект Magazine. Вывести в лог для каждого
            объекта тип, количество строк и цену в евро в отформатированном виде. У класса Book
            переопределить метод equals и произвести сравнение сначала по ссылке, затем используя
            метод equals. Результаты сравнений вывести в лог.
         */

        val firstBook = Book(100.0, 900)
        val secondBook = Book(150.0, 1001)
        val magazine = Magazine(50.0, 300)

        Log.d(
            TAG,
            "Book type: ${firstBook.getType()}, word count: ${firstBook.wordCount}, price: ${firstBook.price} euro"
        )
        Log.d(
            TAG,
            "Book type: ${secondBook.getType()}, word count: ${secondBook.wordCount}, price: ${secondBook.price} euro"
        )
        Log.d(
            TAG,
            "Book type: ${magazine.getType()}, word count: ${magazine.wordCount}, price: ${magazine.price} euro"
        )

        Log.d(TAG, (firstBook === secondBook).toString())
        Log.d(TAG, (firstBook == secondBook).toString())

        /* 3
            Создать метод buy, который в качестве параметра принимает Publication (notnull - значения)
            и выводит в лог “The purchase is complete. The purchase amount was [цена издания]”.
            Создать две переменных класса Book, в которых могут находиться null значения.
            Присвоить одной null, а второй любое notnull значение и вызвать метод buy с каждой из переменных.
        */

        val bookNull: Book? = null
        val bookNonNull: Book? = Book(150.0, 1001)

        buy(bookNonNull ?: throw NullPointerException("Book is null"))
        buy(bookNull ?: throw NullPointerException("Book is null"))

        /* 4
            Создать переменную sum и присвоить ей лямбда-выражение, которое будет складывать
            два переданных ей числа и выводить результат в лог. Вызвать данное лямбда-выражение
            с произвольными параметрами.
         */

        val sum = { value1: Int, value2: Int ->
            Log.d(TAG, (value1 + value2).toString())
        }
        sum(3, 4)
    }

    fun buy(publication: Publication) {
        Log.d(TAG, "The purchase is complete. The purchase amount was ${publication.price}")
    }
}
