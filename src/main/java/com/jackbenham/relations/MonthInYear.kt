package com.jackbenham.relations

import com.jackbenham.*
import com.jackbenham.units.interfaces.MonthFloating
import com.jackbenham.units.interfaces.QuarterFloating

enum class MonthInYear(private val index_: Int, private val representation_: String) : Keyed<Int>, Sequential<MonthInYear>, MonthFloating, QuarterFloating, Comparable<MonthInYear> {
    JAN(0, "January"),
    FEB(1, "February"),
    MAR(2, "March"),
    APR(3, "April"),
    MAY(4, "May"),
    JUN(5, "June"),
    JUL(6, "July"),
    AUG(7, "August"),
    SEP(8, "September"),
    OCT(9, "October"),
    NOV(10, "November"),
    DEC(11, "December");

    companion object : KeyedCreator<Int, MonthInYear> {
        override fun fromKey(key: Int): MonthInYear? {
            return when (key) {
                0 -> JAN
                1 -> FEB
                2 -> MAR
                3 -> APR
                4 -> MAY
                5 -> JUN
                6 -> JUL
                7 -> AUG
                8 -> SEP
                9 -> OCT
                10 -> NOV
                11 -> DEC
                else -> null
            }
        }
    }

    override fun getMM(): Int = index_ + 1

    override fun getMMM(): String = getMMMM().slice(IntRange(0, 2))

    override fun getMMMM(): String = representation_

    override fun getQ(): Int = index_ / 3 + 1

    override fun getQQ(): String = "Q" + getQ()

    override fun getKey(): Int = index_

    override fun next(): MonthInYear = add(1)

    override fun prev(): MonthInYear = add(-1)

    override fun add(offset: Int): MonthInYear = fromKey(ProperMath.mod(getKey() + offset, 12))!!

    override fun getMonthInQuarter(): MonthInQuarter = MonthInQuarter.fromKey(getKey() % 3)!!

    override fun getQuarterInYear(): QuarterInYear = QuarterInYear.fromKey(getKey() / 3)!!

    override fun getMonthInYear(): MonthInYear = this
}