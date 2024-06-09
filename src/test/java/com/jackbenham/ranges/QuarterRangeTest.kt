package com.jackbenham.ranges

import com.jackbenham.relations.MonthInQuarter
import com.jackbenham.relations.QuarterInYear
import com.jackbenham.units.Year
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class QuarterRangeTest {
    @Test
    fun testNullConstructor() {
        assertNull(QuarterRange(Year(2020).Q4, Year(2020).Q1))
        assertNotNull(QuarterRange(Year(2020).Q1, Year(2020).Q4))
    }

    @Test
    fun testConversionToMonthly() {
        val quartersOf2020 = QuarterRange(Year(2020).Q1, Year(2020).Q4)!!
        assertNotNull(quartersOf2020.toMonthRange(MonthInQuarter.FIRST, MonthInQuarter.LAST))
        assertEquals(quartersOf2020.toMonthRange(MonthInQuarter.FIRST, MonthInQuarter.LAST), quartersOf2020.toMonthRange())

        val monthsOf2020 = quartersOf2020.toMonthRange(MonthInQuarter.FIRST, MonthInQuarter.LAST)!!
        assertEquals(Year(2020).JAN, monthsOf2020.getStart())
        assertEquals(Year(2020).DEC, monthsOf2020.getEnd())

        val marToDec2020 = quartersOf2020.toMonthRange(MonthInQuarter.LAST, MonthInQuarter.LAST)!!
        assertEquals(Year(2020).MAR, marToDec2020.getStart())
        assertEquals(Year(2020).DEC, marToDec2020.getEnd())
    }

    @Test
    fun testConversionToYearly() {
        val quartersOf2020 = QuarterRange(Year(2020).Q1, Year(2020).Q4)!!
        assertNotNull(quartersOf2020.toYearRange(QuarterInYear.Q1, QuarterInYear.Q4))

        val yearOf2020 = quartersOf2020.toYearRange(QuarterInYear.Q1, QuarterInYear.Q4)!!
        assertEquals(Year(2020), yearOf2020.getStart())
        assertEquals(Year(2020), yearOf2020.getEnd())
    }

    @Test
    fun testConversionToYearlyCanProduceNulls() {
        val firstTwoQuarterOf2020 = QuarterRange(Year(2020).Q2, Year(2020).Q3)!!
        assertNull(firstTwoQuarterOf2020.toYearRange(QuarterInYear.Q1, QuarterInYear.Q3))
        assertNull(firstTwoQuarterOf2020.toYearRange(QuarterInYear.Q2, QuarterInYear.Q4))
    }

    @Test
    fun testConversionToMonthlyWhenNoArgsGiven() {
        val everyMonthOf2ndMillennium = QuarterRange(Year(1000).Q1, Year(1999).Q4)!!.toMonthRange()
        assertEquals(Year(1000).JAN, everyMonthOf2ndMillennium.getStart())
        assertEquals(Year(1999).DEC, everyMonthOf2ndMillennium.getEnd())
    }

    @Test
    fun testConversionToYearlyWhenNoArgsGiven() {
        val everyYearOf2ndMillennium = QuarterRange(Year(1000).Q1, Year(1999).Q4)!!.toYearRange()!!
        assertEquals(Year(1000), everyYearOf2ndMillennium.getStart())
        assertEquals(Year(1999), everyYearOf2ndMillennium.getEnd())
    }
}