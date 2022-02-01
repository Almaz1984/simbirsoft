package com.almaz.task1.kotlin_training.part_2

import com.almaz.task1.kotlin_training.part2.Type
import java.time.LocalTime

/* 2
    Реализовать класс данных User с полями id, name, age и type. У класса User создать
    ленивое свойство startTime, в котором получаем текущее время.
*/

data class User(
    val id: Long,
    val name: String,
    val age: Int,
    val type: Type,
) {
    val startTime: LocalTime by lazy {
        LocalTime.now()
    }
}
