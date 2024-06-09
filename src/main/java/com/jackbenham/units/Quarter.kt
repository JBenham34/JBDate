package com.jackbenham.units

import com.jackbenham.*
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
            require((key - quarterInYear.getKey()) % 4 == 0)
            val year = Year((key - quarterInYear.getKey()) / 4)
            return invoke(year, quarterInYear)
        }
    }

    private val iQuarter_: Int by lazy {
        year_.getKey() * 4 + quarterInYear_.getKey()
    }

    override fun getKey(): Int = iQuarter_

    override fun toQuarter(): Quarter = this

    override fun getQuarterInYear(): QuarterInYear  = quarterInYear_

    override fun getQ(): Int = quarterInYear_.getQ()

    override fun getQQ(): String = quarterInYear_.getQQ()

    override fun toYear(): Year = year_

    override fun getYY(): Int = year_.getYY()

    override fun getYYYY(): Int = year_.getYYYY()

    override fun add(offset: Int): Quarter = fromKey(getKey() + offset)

    override fun compareTo(other: Quarter): Int = getKey() - other.getKey()

    override fun toString(): String {
        return "${getQQ()} ${getYYYY()}"
    }

    override fun equals(other: Any?): Boolean {
        if (other is Quarter)
            return this === other
        return false
    }

    override fun hashCode(): Int = getKey()
}