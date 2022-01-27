package com.almaz.task1.kotlin_training.part_1

const val FLASH_FICTION_WORD_COUNT_UPPER_BOUND = 1000
const val SHORT_STORY_WORD_COUNT_LOWER_BOUND = 1001
const val SHORT_STORY_WORD_COUNT_UPPER_BOUND = 7500
const val FLASH_FICTION = "Flash Fiction"
const val SHORT_STORY = "Short Story"
const val NOVEL = "Novel"

class Book(
    override var price: Double,
    override var wordCount: Long
) : Publication {

    override fun getType(): String {
        return when (wordCount) {
            in Long.MIN_VALUE..-1 -> throw Exception("Words count is negative!")
            in 0..FLASH_FICTION_WORD_COUNT_UPPER_BOUND -> FLASH_FICTION
            in SHORT_STORY_WORD_COUNT_LOWER_BOUND..SHORT_STORY_WORD_COUNT_UPPER_BOUND -> SHORT_STORY
            else -> NOVEL
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return if (other is Book) {
            this.price == other.price &&
                this.wordCount == other.wordCount
        } else false
    }

    override fun hashCode(): Int {
        var result = price.hashCode()
        result = 31 * result + wordCount.hashCode()
        return result
    }
}
