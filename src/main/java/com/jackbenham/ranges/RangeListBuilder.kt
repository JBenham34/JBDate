package com.jackbenham.ranges

import com.jackbenham.Builder
import com.jackbenham.units.interfaces.DateUnit
import java.util.*

class RangeListBuilder<T : DateUnit<T>>(private val start_: T, private val end_: T) : Builder<List<T>> {
    override val buildResult_: List<T> by lazy { build() }

    private fun build(): List<T> {
        val range: MutableList<T> = ArrayList(end_.getKey() - start_.getKey())
        var current = start_
        while (current != end_) {
            range.add(current)
            current = current.next()
        }
        range.add(end_)
        return Collections.unmodifiableList(range)
    }
}