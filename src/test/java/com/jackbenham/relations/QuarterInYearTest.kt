package com.jackbenham.relations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class QuarterInYearTest {
    @Test
    fun testFromKey() {
        assertNull(QuarterInYear.fromKey(-1))
        assertSame(QuarterInYear.Q1, QuarterInYear.fromKey(0))
        assertSame(QuarterInYear.Q2, QuarterInYear.fromKey(1))
        assertSame(QuarterInYear.Q3, QuarterInYear.fromKey(2))
        assertSame(QuarterInYear.Q4, QuarterInYear.fromKey(3))
        assertNull(QuarterInYear.fromKey(4))
    }

    @Test
    fun testGetQ() {
        assertSame(1, QuarterInYear.Q1.getQ())
        assertSame(2, QuarterInYear.Q2.getQ())
        assertSame(3, QuarterInYear.Q3.getQ())
        assertSame(4, QuarterInYear.Q4.getQ())
    }

    @Test
    fun testGetQQ() {
        assertEquals("Q1", QuarterInYear.Q1.getQQ())
        assertEquals("Q2", QuarterInYear.Q2.getQQ())
        assertEquals("Q3", QuarterInYear.Q3.getQQ())
        assertEquals("Q4", QuarterInYear.Q4.getQQ())
    }

    @Test
    fun testNext() {
        assertSame(QuarterInYear.Q2, QuarterInYear.Q1.next())
        assertSame(QuarterInYear.Q3, QuarterInYear.Q2.next())
        assertSame(QuarterInYear.Q4, QuarterInYear.Q3.next())
        assertSame(QuarterInYear.Q1, QuarterInYear.Q4.next())
    }

    @Test
    fun testPrev() {
        assertSame(QuarterInYear.Q4, QuarterInYear.Q1.prev())
        assertSame(QuarterInYear.Q1, QuarterInYear.Q2.prev())
        assertSame(QuarterInYear.Q2, QuarterInYear.Q3.prev())
        assertSame(QuarterInYear.Q3, QuarterInYear.Q4.prev())
    }

    @Test
    fun testAdd() {
        assertSame(QuarterInYear.Q2, QuarterInYear.Q1.add(1))
        assertSame(QuarterInYear.Q1, QuarterInYear.Q1.add(12))
        assertSame(QuarterInYear.Q1, QuarterInYear.Q1.add(24))
        assertSame(QuarterInYear.Q3, QuarterInYear.Q2.add(13))
        assertSame(QuarterInYear.Q1, QuarterInYear.Q2.add(11))
        assertSame(QuarterInYear.Q1, QuarterInYear.Q2.add(-1))
        assertSame(QuarterInYear.Q4, QuarterInYear.Q3.add(-3))
        assertSame(QuarterInYear.Q4, QuarterInYear.Q4.add(-12))
    }

    @Test
    fun testGetMonthInYear() {
        assertSame(MonthInYear.JAN, QuarterInYear.Q1.getMonthInYear(MonthInQuarter.FIRST))
        assertSame(MonthInYear.FEB, QuarterInYear.Q1.getMonthInYear(MonthInQuarter.MIDDLE))
        assertSame(MonthInYear.MAR, QuarterInYear.Q1.getMonthInYear(MonthInQuarter.LAST))
        assertSame(MonthInYear.APR, QuarterInYear.Q2.getMonthInYear(MonthInQuarter.FIRST))
        assertSame(MonthInYear.MAY, QuarterInYear.Q2.getMonthInYear(MonthInQuarter.MIDDLE))
        assertSame(MonthInYear.JUN, QuarterInYear.Q2.getMonthInYear(MonthInQuarter.LAST))
        assertSame(MonthInYear.JUL, QuarterInYear.Q3.getMonthInYear(MonthInQuarter.FIRST))
        assertSame(MonthInYear.AUG, QuarterInYear.Q3.getMonthInYear(MonthInQuarter.MIDDLE))
        assertSame(MonthInYear.SEP, QuarterInYear.Q3.getMonthInYear(MonthInQuarter.LAST))
        assertSame(MonthInYear.OCT, QuarterInYear.Q4.getMonthInYear(MonthInQuarter.FIRST))
        assertSame(MonthInYear.NOV, QuarterInYear.Q4.getMonthInYear(MonthInQuarter.MIDDLE))
        assertSame(MonthInYear.DEC, QuarterInYear.Q4.getMonthInYear(MonthInQuarter.LAST))
    }

    @Test
    fun testGetQuarterInYear() {
        assertSame(QuarterInYear.Q1, QuarterInYear.Q1.getQuarterInYear())
        assertSame(QuarterInYear.Q2, QuarterInYear.Q2.getQuarterInYear())
        assertSame(QuarterInYear.Q3, QuarterInYear.Q3.getQuarterInYear())
        assertSame(QuarterInYear.Q4, QuarterInYear.Q4.getQuarterInYear())
    }
}