package com.almaz.task1.kotlin_training.part_1

class Magazine(
    override val price: Long,
    override val wordCount: Long
) : Publication {

    override fun getType(): String {
        return MAGAZINE
    }
}
