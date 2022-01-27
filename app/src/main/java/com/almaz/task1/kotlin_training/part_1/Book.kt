package com.almaz.task1.kotlin_training.part_1

const val FLASH_FICTION_WORDS_COUNT = 1000
const val SHORT_STORY_WORDS_COUNT = 7500
const val FLASH_FICTION = "Flash Fiction"
const val SHORT_STORY = "Short Story"
const val NOVEL = "Novel"

class Book(
    override var price: Long,
    override var wordCount: Long
) : Publication {

    override fun getType(): String {
        return when {
            wordCount <= FLASH_FICTION_WORDS_COUNT -> FLASH_FICTION
            wordCount <= SHORT_STORY_WORDS_COUNT -> SHORT_STORY
            wordCount > SHORT_STORY_WORDS_COUNT -> NOVEL
            else -> {
                ""
            }
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
