package com.jackbenham.relations

import com.jackbenham.*
import com.jackbenham.units.Month
import com.jackbenham.units.Quarter

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

    override fun getKey(): Int = index_

    override fun next(): MonthInQuarter = add(1)

    override fun prev(): MonthInQuarter = add(-1)

    override fun add(offset: Int): MonthInQuarter = fromKey(ProperMath.mod(index_ + offset, 3))!!

    fun getMonthInYear(quarterInYear: QuarterInYear): MonthInYear = quarterInYear.getMonthInYear(this)

}