package com.jackbenham.units

import com.jackbenham.KeyedCreator
import com.jackbenham.ProperMath
import com.jackbenham.relations.MonthInYear
import com.jackbenham.relations.QuarterInYear
import com.jackbenham.units.interfaces.DateUnit
import com.jackbenham.units.interfaces.YearFixed
import com.jackbenham.units.interfaces.YearFloating

class Year private constructor(private val index_: Int) : DateUnit<Year>, YearFixed {
    companion object : KeyedCreator<Int, Year> {
        private val instanceCache_: MutableMap<Int, Year> = HashMap()
        operator fun invoke(year: Int): Year {
            return instanceCache_.getOrPut(year) { Year(year) }
        }
        override fun fromKey(key: Int) : Year {
            return invoke(key)
        }
    }

    private fun month(monthInYear: MonthInYear): Month = Month(this, monthInYear)
    private fun quarter(quarterInYear: QuarterInYear): Quarter = Quarter(this, quarterInYear)

    val jan: Month by lazy { month(MonthInYear.JAN) }
    val feb: Month by lazy { month(MonthInYear.FEB) }
    val mar: Month by lazy { month(MonthInYear.MAR) }
    val apr: Month by lazy { month(MonthInYear.APR) }
    val may: Month by lazy { month(MonthInYear.MAY) }
    val jun: Month by lazy { month(MonthInYear.JUN) }
    val jul: Month by lazy { month(MonthInYear.JUL) }
    val aug: Month by lazy { month(MonthInYear.AUG) }
    val sep: Month by lazy { month(MonthInYear.SEP) }
    val oct: Month by lazy { month(MonthInYear.OCT) }
    val nov: Month by lazy { month(MonthInYear.NOV) }
    val dec: Month by lazy { month(MonthInYear.DEC) }
    val q1: Quarter by lazy { quarter(QuarterInYear.Q1) }
    val q2: Quarter by lazy { quarter(QuarterInYear.Q2) }
    val q3: Quarter by lazy { quarter(QuarterInYear.Q3) }
    val q4: Quarter by lazy { quarter(QuarterInYear.Q4) }

    override fun next(): Year = fromKey(getKey() + 1)

    override fun prev(): Year = fromKey(getKey() - 1)

    override fun getKey(): Int = index_

    override fun add(offset: Int): Year = fromKey(getKey() + offset)

    override fun toYear(): Year = this

    override fun getYY(): Int = ProperMath.mod(getKey(), 100)

    override fun getYYYY(): Int = getKey()

    override fun compareTo(other: Year): Int = getKey() - other.getKey()

    override fun toString(): String {
        return getYYYY().toString()
    }
}
