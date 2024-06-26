package com.jackbenham.ranges

import com.jackbenham.units.interfaces.DateUnit
import java.util.*
import java.util.stream.Stream

abstract class AbstractDateRange<T : DateUnit<T>>(protected val start_: T, protected val end_: T) : DateRange<T> {
    init { require(start_ <= end_) }

    private val range_: List<T> by lazy {
        RangeListBuilder(start_, end_).buildResult_
    }

    private val length_ : Int by lazy {
        end_.key_ - start_.key_
    }

    override fun getStart(): T = start_

    override fun getEnd(): T = end_

    override fun inRange(other: T): Int? {
        val offset = other.compareTo(start_)
        if (offset < 0 || offset > length_)
            return null
        return offset
    }

    override fun list(): List<T> = range_

    override fun stream(): Stream<T> = list().stream()

    override fun iterator(): Iterator<T> = list().iterator()

    override fun contains(element: T): Boolean = list().contains(element)

    override fun hashCode(): Int = Objects.hash(start_, end_)

    override fun length(): Int = length_

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other.hashCode() != hashCode()) return false
        if (other !is AbstractDateRange<*>) return false
        if (start_ != other.start_) return false
        return end_ == other.end_
    }

    override fun toString(): String = "${getStart()} - ${getEnd()}"


}