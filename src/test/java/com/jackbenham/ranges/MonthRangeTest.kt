package com.jackbenham.ranges

import com.jackbenham.relations.MonthInQuarter
import com.jackbenham.relations.MonthInYear
import com.jackbenham.relations.QuarterInYear
import com.jackbenham.units.Year
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MonthRangeTest {
    @Test
    fun testConversionToYearly() {
        val everyMonthOf2020s = MonthRange(Year(2020).jan, Year(2029).dec)!!
        val everyYearOf2020s = everyMonthOf2020s.toYearRange()!!

        assertEquals(Year(2020), everyYearOf2020s.getStart())
        assertEquals(Year(2029), everyYearOf2020s.getEnd())
    }

    @Test
    fun testConversionToYearlyCanProduceNulls() {
        val secondHalfOf2020 = MonthRange(Year(2020).jul, Year(2020).dec)!!
        assertNull(secondHalfOf2020.toYearRange(MonthInYear.JAN, MonthInYear.DEC))
        assertNull(secondHalfOf2020.toYearRange(MonthInYear.JUN, MonthInYear.DEC))
        assertNotNull(secondHalfOf2020.toYearRange(MonthInYear.JUL, MonthInYear.DEC))
    }

    @Test
    fun testConversionToQuarterly() {
        val everyMonthOf2020s = MonthRange(Year(2020).jan, Year(2029).dec)!!
        val everyQuarterOf2020s = everyMonthOf2020s.toQuarterRange()!!

        assertEquals(Year(2020).q1, everyQuarterOf2020s.getStart())
        assertEquals(Year(2029).q4, everyQuarterOf2020s.getEnd())
    }

    @Test
    fun testConversionToQuarterlyCanProduceNulls() {
        val secondHalfOf2020 = MonthRange(Year(2020).feb, Year(2020).may)!!
        assertNull(secondHalfOf2020.toQuarterRange(MonthInQuarter.FIRST, MonthInQuarter.LAST))
        assertNotNull(secondHalfOf2020.toQuarterRange(MonthInQuarter.LAST, MonthInQuarter.LAST))
        assertNotNull(secondHalfOf2020.toQuarterRange(MonthInQuarter.FIRST, MonthInQuarter.FIRST))
        assertNotNull(secondHalfOf2020.toQuarterRange(MonthInQuarter.MIDDLE, MonthInQuarter.MIDDLE))
    }
}