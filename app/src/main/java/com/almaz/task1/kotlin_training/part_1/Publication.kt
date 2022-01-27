package com.almaz.task1.kotlin_training.part_1

/* 1
    Необходимо создать интерфейс Publication, у которого должно быть свойства – price и
    wordCount, а также метод getType, возвращающий строку. Создать два класса, реализующих
    данный интерфейс – Book и Magazine. В методе getType для класса Book возвращаем строку
    в зависимости от количества слов. Если количество слов не превышает 1000, то вывести
    “Flash Fiction”, 7,500 –“Short Story”, всё, что выше – “Novel”.
    Для класса Magazine возвращаем строку “Magazine”.
*/

interface Publication {
    val price: Double
    val wordCount: Long

    fun getType(): String
}
