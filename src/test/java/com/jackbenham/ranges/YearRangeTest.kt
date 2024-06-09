package com.jackbenham.ranges

import com.jackbenham.relations.MonthInYear
import com.jackbenham.relations.QuarterInYear
import com.jackbenham.units.Year
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class YearRangeTest {
    @Test
    fun testConversionToMonthly() {
        val yearOf2020 = YearRange(Year(2020), Year(2020))!!
        assertNotNull(yearOf2020.toMonthRange(MonthInYear.JAN, MonthInYear.DEC))

        val monthsOf2020 = yearOf2020.toMonthRange(MonthInYear.JAN, MonthInYear.DEC)!!
        assertEquals(Year(2020).JAN, monthsOf2020.getStart())
        assertEquals(Year(2020).DEC, monthsOf2020.getEnd())

        val monthsOfSecondQuarterOf2020 = yearOf2020.toMonthRange(MonthInYear.APR, MonthInYear.JUN)!!
        assertEquals(Year(2020).APR, monthsOfSecondQuarterOf2020.getStart())
        assertEquals(Year(2020).JUN, monthsOfSecondQuarterOf2020.getEnd())
    }

    @Test
    fun testConversionCanProduceNulls() {
        val yearOf2020 = YearRange(Year(2020), Year(2020))!!
        assertNull(yearOf2020.toMonthRange(MonthInYear.DEC, MonthInYear.JAN))
        assertNull(yearOf2020.toQuarterRange(QuarterInYear.Q4, QuarterInYear.Q1))
    }

    @Test
    fun testConversionsWhenNoArgsGiven() {
        val everyMonthOf2ndMillennium = YearRange(Year(1000), Year(1999))!!.toMonthRange()
        assertEquals(Year(1000).JAN, everyMonthOf2ndMillennium.getStart())
        assertEquals(Year(1999).DEC, everyMonthOf2ndMillennium.getEnd())


        val everyQuarterOf2ndMillennium = YearRange(Year(1000), Year(1999))!!.toQuarterRange()
        assertEquals(Year(1000).Q1, everyQuarterOf2ndMillennium.getStart())
        assertEquals(Year(1999).Q4, everyQuarterOf2ndMillennium.getEnd())
    }
}