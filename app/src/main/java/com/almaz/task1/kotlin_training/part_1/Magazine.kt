package com.almaz.task1.kotlin_training.part_1

const val MAGAZINE = "Magazine"

class Magazine(
    override val price: Double,
    override val wordCount: Long
) : Publication {

    override fun getType(): String {
        return MAGAZINE
    }
}
