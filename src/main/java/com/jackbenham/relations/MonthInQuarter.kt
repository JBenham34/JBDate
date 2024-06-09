package com.jackbenham.relations

import com.jackbenham.*

enum class MonthInQuarter(private val index_: Int) : Keyed<Int>, Sequential<MonthInQuarter>, Comparable<MonthInQuarter> {
    FIRST(0),
    MIDDLE(1),
    LAST(2);

    companion object : KeyedCreator<Int, MonthInQuarter> {
        override fun fromKey(key: Int): MonthInQuarter? {
            return when(key) {
                0 -> FIRST
                1 -> MIDDLE
                2 -> LAST
                else -> null
            }
        }
    }

    override val key_: Int
        get() = index_

    override fun add(offset: Int): MonthInQuarter = fromKey(ProperMath.mod(key_ + offset, 3))!!

    fun getMonthInYear(quarterInYear: QuarterInYear): MonthInYear = quarterInYear.getMonthInYear(this)

}