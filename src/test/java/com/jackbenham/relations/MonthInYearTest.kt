package com.jackbenham.relations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MonthInYearTest {
    @Test
    fun testFromKey() {
        assertNull(MonthInYear.fromKey(-1))
        assertSame(MonthInYear.JAN, MonthInYear.fromKey(0))
        assertSame(MonthInYear.FEB, MonthInYear.fromKey(1))
        assertSame(MonthInYear.MAR, MonthInYear.fromKey(2))
        assertSame(MonthInYear.APR, MonthInYear.fromKey(3))
        assertSame(MonthInYear.MAY, MonthInYear.fromKey(4))
        assertSame(MonthInYear.JUN, MonthInYear.fromKey(5))
        assertSame(MonthInYear.JUL, MonthInYear.fromKey(6))
        assertSame(MonthInYear.AUG, MonthInYear.fromKey(7))
        assertSame(MonthInYear.SEP, MonthInYear.fromKey(8))
        assertSame(MonthInYear.OCT, MonthInYear.fromKey(9))
        assertSame(MonthInYear.NOV, MonthInYear.fromKey(10))
        assertSame(MonthInYear.DEC, MonthInYear.fromKey(11))
        assertNull(MonthInYear.fromKey(12))
    }

    @Test
    fun testGetMs() {
        assertEquals(3, MonthInYear.MAR.getMM())
        assertEquals("Mar", MonthInYear.MAR.getMMM())
        assertEquals("March", MonthInYear.MAR.getMMMM())
    }

    @Test
    fun testGetQs() {
        assertEquals(1, MonthInYear.MAR.getQ())
        assertEquals("Q1", MonthInYear.MAR.getQQ())
    }

    @Test
    fun testNext() {
        assertSame(MonthInYear.FEB, MonthInYear.JAN.next())
        assertSame(MonthInYear.MAR, MonthInYear.FEB.next())
        assertSame(MonthInYear.JAN, MonthInYear.DEC.next())
    }

    @Test
    fun testPrev() {
        assertSame(MonthInYear.JAN, MonthInYear.FEB.prev())
        assertSame(MonthInYear.FEB, MonthInYear.MAR.prev())
        assertSame(MonthInYear.DEC, MonthInYear.JAN.prev())
    }

    @Test
    fun testAdd() {
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
    fun testGetMonthInQuarter() {
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
    fun testGetQuarterInYear() {
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



    @Test
    fun testGetMonthInYear() {
        for (month in MonthInYear.entries)
            assertSame(month, month.getMonthInYear())
    }
}