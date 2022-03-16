package com.almaz.task1.data.mapper

interface Mapper<SRC, DST> {
    fun transform(src: SRC): DST
}
