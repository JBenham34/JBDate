package com.jackbenham.units

import com.jackbenham.relations.MonthInQuarter
import com.jackbenham.relations.MonthInYear
import com.jackbenham.relations.QuarterInYear
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MonthTest {
    @Test
    fun testCreation() {
        val apr2020 = Month(Year(2020), MonthInYear.APR)
        val apr2020Again = Month(Year(2020), MonthInYear.APR)
        assertSame(apr2020, apr2020Again)

        val may2020 = Month(Year(2020), MonthInYear.MAY)
        assertNotSame(may2020, apr2020)
    }

    @Test
    fun testFromYear() {
        val apr2020 = Year(2020).APR
        val apr2020Again = Year(2020).APR
        assertSame(apr2020, apr2020Again)

        val may2020 = Year(2020).MAY
        assertNotSame(may2020, apr2020)
    }

    @Test
    fun testFromQuarter() {
        val apr2020 = Month(Year(2020).Q2, MonthInQuarter.FIRST)
        val apr2020Again = Month(Year(2020).Q2, MonthInQuarter.FIRST)
        assertSame(apr2020, apr2020Again)

        val may2020 = Month(Year(2020).Q2, MonthInQuarter.MIDDLE)
        assertNotSame(may2020, apr2020)
    }

    @Test
    fun testFromKey() {
        assertEquals(Year(2020).APR, Month.fromKey(2020 * 12 + 3))
    }

    @Test
    fun testNext() {
        assertEquals(Year(2020).APR, Year(2020).MAR.next())
        assertEquals(Year(2020).MAY, Year(2020).APR.next())
        assertEquals(Year(2020).MAY, Year(2020).MAR.next().next())

        assertEquals(Year(2021).JAN, Year(2020).DEC.next())
    }

    @Test
    fun testPrev() {
        assertEquals(Year(2020).MAR, Year(2020).APR.prev())
        assertEquals(Year(2020).APR, Year(2020).MAY.prev())
        assertEquals(Year(2020).MAR, Year(2020).MAY.prev().prev())

        assertEquals(Year(2020).DEC, Year(2021).JAN.prev())
    }

    @Test
    fun testAdd() {
        assertEquals(Year(2020).APR, Year(2020).MAR.add(1))
        assertEquals(Year(2020).MAY, Year(2020).MAR.add(2))
        assertEquals(Year(2021).MAR, Year(2020).MAR.add(12))

        assertEquals(Year(2020).APR, Year(2020).MAY.add(-1))
        assertEquals(Year(2020).MAR, Year(2020).MAY.add(-2))
        assertEquals(Year(2019).MAR, Year(2020).MAR.add(-12))
    }

    @Test
    fun testComparisons() {
        assertTrue(Year(2020).MAR < Year(2020).APR)
        assertTrue(Year(2020).MAR <= Year(2020).APR)
        assertTrue(Year(2020).MAY > Year(2020).APR)
        assertTrue(Year(2020).MAY >= Year(2020).APR)
        assertTrue(Year(2020).JAN > Year(2019).DEC)
    }

    @Test
    fun testConversions() {
        assertSame(Year(2020).JAN, Year(2020).JAN.toMonth())
        assertSame(Year(2020).Q1, Year(2020).JAN.toQuarter())
        assertSame(Year(2020), Year(2020).JAN.toYear())
    }

    @Test
    fun testRelationConversions() {
        assertSame(MonthInYear.JAN, Year(2020).JAN.getMonthInYear())
        assertSame(QuarterInYear.Q1, Year(2020).JAN.getQuarterInYear())
        assertSame(MonthInQuarter.FIRST, Year(2020).JAN.getMonthInQuarter())
    }

    @Test
    fun testGetMs() {
        assertEquals(1, Year(2020).JAN.getMM())
        assertEquals("Jan", Year(2020).JAN.getMMM())
        assertEquals("January", Year(2020).JAN.getMMMM())
    }

    @Test
    fun testGetQs() {
        assertEquals(1, Year(2020).JAN.getQ())
        assertEquals("Q1", Year(2020).JAN.getQQ())
    }

    @Test
    fun testGetYs() {
        assertEquals(20, Year(2020).JAN.getYY())
        assertEquals(2020, Year(2020).JAN.getYYYY())
    }

    @Test
    fun testEquality() {
        assertEquals(Year(2020).JAN, Year(2020).JAN)
        assertNotEquals(Year(2020).JAN, Year(2020).FEB)
        assertNotEquals(Year(2020).JAN, Year(2020).Q1)
    }

    @Test
    fun testToString() {
        assertEquals("Jan 2020", Year(2020).JAN.toString())
    }

    @Test
    fun testHashCode() {
        assertEquals(Year(2020).JAN.hashCode(), Year(2020).JAN.hashCode())
        assertNotEquals(Year(2020).JAN.hashCode(), Year(2020).DEC.hashCode())
        assertNotEquals(Year(2020).JAN.hashCode(), Year(2021).JAN.hashCode())
    }
}