package com.jackbenham.relations

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MonthInYearTest {
    @Test
    fun next() {
        assertSame(MonthInYear.FEB, MonthInYear.JAN.next())
        assertSame(MonthInYear.MAR, MonthInYear.FEB.next())
        assertSame(MonthInYear.JAN, MonthInYear.DEC.next())
    }

    @Test
    fun prev() {
        assertSame(MonthInYear.JAN, MonthInYear.FEB.prev())
        assertSame(MonthInYear.FEB, MonthInYear.MAR.prev())
        assertSame(MonthInYear.DEC, MonthInYear.JAN.prev())
    }

    @Test
    fun add() {
        assertSame(MonthInYear.FEB, MonthInYear.JAN.add(1))
        assertSame(MonthInYear.FEB, MonthInYear.FEB.add(12))
        assertSame(MonthInYear.FEB, MonthInYear.FEB.add(24))
        assertSame(MonthInYear.MAR, MonthInYear.FEB.add(13))
        assertSame(MonthInYear.JAN, MonthInYear.FEB.add(11))
        assertSame(MonthInYear.JAN, MonthInYear.FEB.add(-1))
        assertSame(MonthInYear.NOV, MonthInYear.FEB.add(-3))
        assertSame(MonthInYear.FEB, MonthInYear.FEB.add(-12))
    }

    @Test
    fun getMonthInQuarter() {
        assertEquals(MonthInQuarter.FIRST, MonthInYear.JAN.getMonthInQuarter())
        assertEquals(MonthInQuarter.MIDDLE, MonthInYear.FEB.getMonthInQuarter())
        assertEquals(MonthInQuarter.LAST, MonthInYear.MAR.getMonthInQuarter())
        assertEquals(MonthInQuarter.FIRST, MonthInYear.APR.getMonthInQuarter())
        assertEquals(MonthInQuarter.MIDDLE, MonthInYear.MAY.getMonthInQuarter())
        assertEquals(MonthInQuarter.LAST, MonthInYear.JUN.getMonthInQuarter())
        assertEquals(MonthInQuarter.FIRST, MonthInYear.JUL.getMonthInQuarter())
        assertEquals(MonthInQuarter.MIDDLE, MonthInYear.AUG.getMonthInQuarter())
        assertEquals(MonthInQuarter.LAST, MonthInYear.SEP.getMonthInQuarter())
        assertEquals(MonthInQuarter.FIRST, MonthInYear.OCT.getMonthInQuarter())
        assertEquals(MonthInQuarter.MIDDLE, MonthInYear.NOV.getMonthInQuarter())
        assertEquals(MonthInQuarter.LAST, MonthInYear.DEC.getMonthInQuarter())
    }

    @Test
    fun getQuarterInYear() {
        assertEquals(QuarterInYear.Q1, MonthInYear.JAN.getQuarterInYear())
        assertEquals(QuarterInYear.Q1, MonthInYear.FEB.getQuarterInYear())
        assertEquals(QuarterInYear.Q1, MonthInYear.MAR.getQuarterInYear())
        assertEquals(QuarterInYear.Q2, MonthInYear.APR.getQuarterInYear())
        assertEquals(QuarterInYear.Q2, MonthInYear.MAY.getQuarterInYear())
        assertEquals(QuarterInYear.Q2, MonthInYear.JUN.getQuarterInYear())
        assertEquals(QuarterInYear.Q3, MonthInYear.JUL.getQuarterInYear())
        assertEquals(QuarterInYear.Q3, MonthInYear.AUG.getQuarterInYear())
        assertEquals(QuarterInYear.Q3, MonthInYear.SEP.getQuarterInYear())
        assertEquals(QuarterInYear.Q4, MonthInYear.OCT.getQuarterInYear())
        assertEquals(QuarterInYear.Q4, MonthInYear.NOV.getQuarterInYear())
        assertEquals(QuarterInYear.Q4, MonthInYear.DEC.getQuarterInYear())
    }
}