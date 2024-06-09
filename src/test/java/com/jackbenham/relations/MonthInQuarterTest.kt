package com.jackbenham.relations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MonthInQuarterTest {
    @Test
    fun testFromKey() {
        assertNull(MonthInQuarter.fromKey(-1))
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.fromKey(0))
        assertSame(MonthInQuarter.MIDDLE, MonthInQuarter.fromKey(1))
        assertSame(MonthInQuarter.LAST, MonthInQuarter.fromKey(2))
        assertNull(MonthInQuarter.fromKey(3))
    }

    @Test
    fun testNext() {
        assertSame(MonthInQuarter.MIDDLE, MonthInQuarter.FIRST.next())
        assertSame(MonthInQuarter.LAST, MonthInQuarter.MIDDLE.next())
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.LAST.next())
    }

    @Test
    fun testPrev() {
        assertSame(MonthInQuarter.LAST, MonthInQuarter.FIRST.prev())
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.MIDDLE.prev())
        assertSame(MonthInQuarter.MIDDLE, MonthInQuarter.LAST.prev())
    }

    @Test
    fun testAdd() {
        assertSame(MonthInQuarter.MIDDLE, MonthInQuarter.FIRST.add(1))
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.FIRST.add(12))
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.FIRST.add(24))
        assertSame(MonthInQuarter.LAST, MonthInQuarter.MIDDLE.add(13))
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.MIDDLE.add(11))
        assertSame(MonthInQuarter.MIDDLE, MonthInQuarter.LAST.add(-1))
        assertSame(MonthInQuarter.LAST, MonthInQuarter.LAST.add(-3))
        assertSame(MonthInQuarter.LAST, MonthInQuarter.LAST.add(-12))
    }

    @Test
    fun testGetMonthInYear() {
        assertSame(MonthInYear.JAN, MonthInQuarter.FIRST.getMonthInYear(QuarterInYear.Q1))
        assertSame(MonthInYear.FEB, MonthInQuarter.MIDDLE.getMonthInYear(QuarterInYear.Q1))
        assertSame(MonthInYear.MAR, MonthInQuarter.LAST.getMonthInYear(QuarterInYear.Q1))
        assertSame(MonthInYear.APR, MonthInQuarter.FIRST.getMonthInYear(QuarterInYear.Q2))
        assertSame(MonthInYear.MAY, MonthInQuarter.MIDDLE.getMonthInYear(QuarterInYear.Q2))
        assertSame(MonthInYear.JUN, MonthInQuarter.LAST.getMonthInYear(QuarterInYear.Q2))
        assertSame(MonthInYear.JUL, MonthInQuarter.FIRST.getMonthInYear(QuarterInYear.Q3))
        assertSame(MonthInYear.AUG, MonthInQuarter.MIDDLE.getMonthInYear(QuarterInYear.Q3))
        assertSame(MonthInYear.SEP, MonthInQuarter.LAST.getMonthInYear(QuarterInYear.Q3))
        assertSame(MonthInYear.OCT, MonthInQuarter.FIRST.getMonthInYear(QuarterInYear.Q4))
        assertSame(MonthInYear.NOV, MonthInQuarter.MIDDLE.getMonthInYear(QuarterInYear.Q4))
        assertSame(MonthInYear.DEC, MonthInQuarter.LAST.getMonthInYear(QuarterInYear.Q4))
    }
}