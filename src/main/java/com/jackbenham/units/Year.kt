package com.jackbenham.units

import com.jackbenham.KeyedCreator
import com.jackbenham.ProperMath
import com.jackbenham.relations.MonthInYear
import com.jackbenham.relations.QuarterInYear
import com.jackbenham.units.interfaces.DateUnit
import com.jackbenham.units.interfaces.YearFixed

@Suppress("PropertyName")
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

    val JAN: Month by lazy { month(MonthInYear.JAN) }
    val FEB: Month by lazy { month(MonthInYear.FEB) }
    val MAR: Month by lazy { month(MonthInYear.MAR) }
    val APR: Month by lazy { month(MonthInYear.APR) }
    val MAY: Month by lazy { month(MonthInYear.MAY) }
    val JUN: Month by lazy { month(MonthInYear.JUN) }
    val JUL: Month by lazy { month(MonthInYear.JUL) }
    val AUG: Month by lazy { month(MonthInYear.AUG) }
    val SEP: Month by lazy { month(MonthInYear.SEP) }
    val OCT: Month by lazy { month(MonthInYear.OCT) }
    val NOV: Month by lazy { month(MonthInYear.NOV) }
    val DEC: Month by lazy { month(MonthInYear.DEC) }
    val Q1: Quarter by lazy { quarter(QuarterInYear.Q1) }
    val Q2: Quarter by lazy { quarter(QuarterInYear.Q2) }
    val Q3: Quarter by lazy { quarter(QuarterInYear.Q3) }
    val Q4: Quarter by lazy { quarter(QuarterInYear.Q4) }

    override fun getKey(): Int = index_

    override fun add(offset: Int): Year = fromKey(getKey() + offset)

    override fun toYear(): Year = this

    override fun getYY(): Int = ProperMath.mod(getKey(), 100)

    override fun getYYYY(): Int = getKey()

    override fun compareTo(other: Year): Int = getKey() - other.getKey()

    override fun toString(): String {
        return getYYYY().toString()
    }

    override fun equals(other: Any?): Boolean {
        if (other is Year)
            return this === other
        return false
    }

    override fun hashCode(): Int = getKey()
}
