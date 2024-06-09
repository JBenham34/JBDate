package com.jackbenham.relations

import com.jackbenham.*
import com.jackbenham.units.interfaces.QuarterFloating

enum class QuarterInYear(private val index_ : Int) : Keyed<Int>, Sequential<QuarterInYear>, QuarterFloating, Comparable<QuarterInYear> {
    Q1(0),
    Q2(1),
    Q3(2),
    Q4(3);

    companion object : KeyedCreator<Int, QuarterInYear> {
        override fun fromKey(key: Int): QuarterInYear? {
            return when (key) {
                0 -> Q1
                1 -> Q2
                2 -> Q3
                3 -> Q4
                else -> null
            }
        }
    }

    override val key_: Int
        get() = index_

    override fun add(offset: Int): QuarterInYear = fromKey(ProperMath.mod(key_ + offset, 4))!!

    override fun getQ(): Int = key_ + 1

    override fun getQQ(): String = "Q" + getQ()

    override fun getQuarterInYear(): QuarterInYear = this

    fun getMonthInYear(monthInQuarter: MonthInQuarter): MonthInYear = MonthInYear.fromKey(key_ * 3 + monthInQuarter.key_)!!
}