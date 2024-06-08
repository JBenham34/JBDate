package com.jackbenham.relations

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class QuarterInYearTest {
    @Test
    fun next() {
        assertSame(QuarterInYear.Q2, QuarterInYear.Q1.next())
        assertSame(QuarterInYear.Q3, QuarterInYear.Q2.next())
        assertSame(QuarterInYear.Q4, QuarterInYear.Q3.next())
        assertSame(QuarterInYear.Q1, QuarterInYear.Q4.next())
    }

    @Test
    fun prev() {
        assertSame(QuarterInYear.Q4, QuarterInYear.Q1.prev())
        assertSame(QuarterInYear.Q1, QuarterInYear.Q2.prev())
        assertSame(QuarterInYear.Q2, QuarterInYear.Q3.prev())
        assertSame(QuarterInYear.Q3, QuarterInYear.Q4.prev())
    }

    @Test
    fun add() {
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
    fun getMonthInYear() {
            assertEquals(MonthInYear.JAN, QuarterInYear.Q1.getMonthInYear(MonthInQuarter.FIRST))
            assertEquals(MonthInYear.FEB, QuarterInYear.Q1.getMonthInYear(MonthInQuarter.MIDDLE))
            assertEquals(MonthInYear.MAR, QuarterInYear.Q1.getMonthInYear(MonthInQuarter.LAST))
            assertEquals(MonthInYear.APR, QuarterInYear.Q2.getMonthInYear(MonthInQuarter.FIRST))
            assertEquals(MonthInYear.MAY, QuarterInYear.Q2.getMonthInYear(MonthInQuarter.MIDDLE))
            assertEquals(MonthInYear.JUN, QuarterInYear.Q2.getMonthInYear(MonthInQuarter.LAST))
            assertEquals(MonthInYear.JUL, QuarterInYear.Q3.getMonthInYear(MonthInQuarter.FIRST))
            assertEquals(MonthInYear.AUG, QuarterInYear.Q3.getMonthInYear(MonthInQuarter.MIDDLE))
            assertEquals(MonthInYear.SEP, QuarterInYear.Q3.getMonthInYear(MonthInQuarter.LAST))
            assertEquals(MonthInYear.OCT, QuarterInYear.Q4.getMonthInYear(MonthInQuarter.FIRST))
            assertEquals(MonthInYear.NOV, QuarterInYear.Q4.getMonthInYear(MonthInQuarter.MIDDLE))
            assertEquals(MonthInYear.DEC, QuarterInYear.Q4.getMonthInYear(MonthInQuarter.LAST))
        }
}