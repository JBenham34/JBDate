package com.jackbenham.units

import com.jackbenham.*
import com.jackbenham.relations.MonthInQuarter
import com.jackbenham.relations.MonthInYear
import com.jackbenham.relations.QuarterInYear
import com.jackbenham.units.interfaces.*
import java.util.*
import kotlin.collections.HashMap

class Month private constructor(private val year_: Year, private val monthInYear_: MonthInYear) :
        DateUnit<Month>, MonthFixed, YearFixed, QuarterFixed, Keyed<Int>, Comparable<Month>, Sequential<Month> {
    companion object : KeyedCreator<Int, Month> {
        private val instanceCache_: MutableMap<Year, MutableMap<MonthInYear, Month>> = HashMap()

        operator fun invoke(year: Year, monthInYear: MonthInYear): Month {
            val yearCache: MutableMap<MonthInYear, Month> = instanceCache_.getOrPut(year) { EnumMap(MonthInYear::class.java) }
            return yearCache.getOrPut(monthInYear) { Month(year, monthInYear) }
        }

        operator fun invoke(quarter: Quarter, monthInQuarter: MonthInQuarter): Month
            = invoke(quarter.toYear(), monthInQuarter.getMonthInYear(quarter.getQuarterInYear()))

        override fun fromKey(key: Int): Month {
            val monthInYear = MonthInYear.fromKey(ProperMath.mod(key, 12))!!
            require((key - monthInYear.key_) % 12 == 0)
            val year = Year((key - monthInYear.key_) / 12)
            return invoke(year, monthInYear)
        }
    }

    private val iMonth_: Int by lazy {
        year_.key_ * 12 + monthInYear_.key_
    }

    override val key_: Int
        get() = iMonth_

    override fun add(offset: Int): Month = fromKey(key_ + offset)

    override fun compareTo(other: Month): Int = key_ - other.key_

    override fun toMonth(): Month = this

    override fun toQuarter(): Quarter = Quarter.fromKey(key_ / 3)

    override fun toYear(): Year = year_

    override fun getMonthInYear(): MonthInYear = monthInYear_

    override fun getMonthInQuarter(): MonthInQuarter = monthInYear_.getMonthInQuarter()

    override fun getQuarterInYear(): QuarterInYear = toQuarter().getQuarterInYear()

    override fun getMM(): Int = monthInYear_.getMM()

    override fun getMMM(): String = monthInYear_.getMMM()

    override fun getMMMM(): String = monthInYear_.getMMMM()

    override fun getQ(): Int = toQuarter().getQ()

    override fun getQQ(): String = toQuarter().getQQ()

    override fun getYY(): Int = toYear().getYY()

    override fun getYYYY(): Int = toYear().getYYYY()

    override fun toString(): String = "${getMMM()} ${getYYYY()}"

    override fun equals(other: Any?): Boolean {
        if (other is Month)
            return this === other
        return false
    }

    override fun hashCode(): Int = key_
}