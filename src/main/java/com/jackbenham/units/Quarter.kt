package com.jackbenham.units

import com.jackbenham.*
import com.jackbenham.relations.MonthInQuarter
import com.jackbenham.relations.QuarterInYear
import com.jackbenham.units.interfaces.*
import java.util.*
import kotlin.collections.HashMap

class Quarter private constructor(private val year_: Year, private val quarterInYear_: QuarterInYear) :
        DateUnit<Quarter>, YearFixed, QuarterFixed, Keyed<Int>, Comparable<Quarter>, Sequential<Quarter> {
    companion object : KeyedCreator<Int, Quarter> {
        private val instanceCache_: MutableMap<Year, MutableMap<QuarterInYear, Quarter>> = HashMap()

        operator fun invoke(year: Year, quarterInYear: QuarterInYear): Quarter {
            val yearCache: MutableMap<QuarterInYear, Quarter> = instanceCache_.getOrPut(year) { EnumMap(QuarterInYear::class.java) }
            return yearCache.getOrPut(quarterInYear) { Quarter(year, quarterInYear) }
        }

        override fun fromKey(key: Int): Quarter {
            val quarterInYear = QuarterInYear.fromKey(ProperMath.mod(key, 4))!!
            require((key - quarterInYear.key_) % 4 == 0)
            val year = Year((key - quarterInYear.key_) / 4)
            return invoke(year, quarterInYear)
        }
    }

    private fun month(monthInQuarter: MonthInQuarter): Month = Month(this, monthInQuarter)

    val FIRST: Month by lazy { month(MonthInQuarter.FIRST) }
    val MIDDLE: Month by lazy { month(MonthInQuarter.MIDDLE) }
    val LAST: Month by lazy { month(MonthInQuarter.LAST) }

    private val iQuarter_: Int by lazy {
        year_.key_ * 4 + quarterInYear_.key_
    }

    override val key_: Int
        get() = iQuarter_

    override fun getQuarterInYear(): QuarterInYear  = quarterInYear_

    override fun toQuarter(): Quarter = this

    override fun toYear(): Year = year_

    override fun getQ(): Int = quarterInYear_.getQ()

    override fun getQQ(): String = quarterInYear_.getQQ()

    override fun getYY(): Int = year_.getYY()

    override fun getYYYY(): Int = year_.getYYYY()

    override fun add(offset: Int): Quarter = fromKey(key_ + offset)

    override fun compareTo(other: Quarter): Int = key_ - other.key_

    override fun toString(): String = "${getQQ()} ${getYYYY()}"

    override fun equals(other: Any?): Boolean {
        if (other is Quarter)
            return this === other
        return false
    }

    override fun hashCode(): Int = key_
}