package com.jackbenham.ranges

import com.jackbenham.relations.MonthInQuarter
import com.jackbenham.relations.MonthInYear
import com.jackbenham.units.Year
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MonthRangeTest {
    @Test
    fun testConversionToYearly() {
        val everyMonthOf2020s = MonthRange(Year(2020).JAN, Year(2029).DEC)!!
        val everyYearOf2020s = everyMonthOf2020s.toYearRange()!!

        assertEquals(Year(2020), everyYearOf2020s.getStart())
        assertEquals(Year(2029), everyYearOf2020s.getEnd())
    }

    @Test
    fun testConversionToYearlyCanProduceNulls() {
        val secondHalfOf2020 = MonthRange(Year(2020).JUL, Year(2020).NOV)!!
        assertNull(secondHalfOf2020.toYearRange(MonthInYear.JAN, MonthInYear.NOV))
        assertNull(secondHalfOf2020.toYearRange(MonthInYear.JUN, MonthInYear.NOV))
        assertNotNull(secondHalfOf2020.toYearRange(MonthInYear.JUL, MonthInYear.NOV))
        assertNull(secondHalfOf2020.toYearRange(MonthInYear.JUL, MonthInYear.DEC))
    }

    @Test
    fun testConversionToQuarterly() {
        val everyMonthOf2020s = MonthRange(Year(2020).JAN, Year(2029).DEC)!!
        val everyQuarterOf2020s = everyMonthOf2020s.toQuarterRange()!!

        assertEquals(Year(2020).Q1, everyQuarterOf2020s.getStart())
        assertEquals(Year(2029).Q4, everyQuarterOf2020s.getEnd())
    }

    @Test
    fun testConversionToQuarterlyCanProduceNulls() {
        val secondHalfOf2020 = MonthRange(Year(2020).FEB, Year(2020).MAY)!!
        assertNull(secondHalfOf2020.toQuarterRange(MonthInQuarter.FIRST, MonthInQuarter.LAST))
        assertNotNull(secondHalfOf2020.toQuarterRange(MonthInQuarter.LAST, MonthInQuarter.LAST))
        assertNotNull(secondHalfOf2020.toQuarterRange(MonthInQuarter.FIRST, MonthInQuarter.FIRST))
        assertNotNull(secondHalfOf2020.toQuarterRange(MonthInQuarter.MIDDLE, MonthInQuarter.MIDDLE))
    }
}