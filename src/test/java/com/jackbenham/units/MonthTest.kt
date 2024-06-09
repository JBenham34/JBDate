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
        val apr2020 = Year(2020).apr
        val apr2020Again = Year(2020).apr
        assertSame(apr2020, apr2020Again)

        val may2020 = Year(2020).may
        assertNotSame(may2020, apr2020)
    }

    @Test
    fun testFromQuarter() {
        val apr2020 = Month(Year(2020).q2, MonthInQuarter.FIRST)
        val apr2020Again = Month(Year(2020).q2, MonthInQuarter.FIRST)
        assertSame(apr2020, apr2020Again)

        val may2020 = Month(Year(2020).q2, MonthInQuarter.MIDDLE)
        assertNotSame(may2020, apr2020)
    }

    @Test
    fun testFromKey() {
        assertEquals(Year(2020).apr, Month.fromKey(2020 * 12 + 3))
    }

    @Test
    fun testNext() {
        assertEquals(Year(2020).apr, Year(2020).mar.next())
        assertEquals(Year(2020).may, Year(2020).apr.next())
        assertEquals(Year(2020).may, Year(2020).mar.next().next())

        assertEquals(Year(2021).jan, Year(2020).dec.next())
    }

    @Test
    fun testPrev() {
        assertEquals(Year(2020).mar, Year(2020).apr.prev())
        assertEquals(Year(2020).apr, Year(2020).may.prev())
        assertEquals(Year(2020).mar, Year(2020).may.prev().prev())

        assertEquals(Year(2020).dec, Year(2021).jan.prev())
    }

    @Test
    fun testAdd() {
        assertEquals(Year(2020).apr, Year(2020).mar.add(1))
        assertEquals(Year(2020).may, Year(2020).mar.add(2))
        assertEquals(Year(2021).mar, Year(2020).mar.add(12))

        assertEquals(Year(2020).apr, Year(2020).may.add(-1))
        assertEquals(Year(2020).mar, Year(2020).may.add(-2))
        assertEquals(Year(2019).mar, Year(2020).mar.add(-12))
    }

    @Test
    fun testComparisons() {
        assertTrue(Year(2020).mar < Year(2020).apr)
        assertTrue(Year(2020).mar <= Year(2020).apr)
        assertTrue(Year(2020).may > Year(2020).apr)
        assertTrue(Year(2020).may >= Year(2020).apr)
        assertTrue(Year(2020).jan > Year(2019).dec)
    }

    @Test
    fun testConversions() {
        assertSame(Year(2020).jan, Year(2020).jan.toMonth())
        assertSame(Year(2020).q1, Year(2020).jan.toQuarter())
        assertSame(Year(2020), Year(2020).jan.toYear())
    }

    @Test
    fun testRelationConversions() {
        assertSame(MonthInYear.JAN, Year(2020).jan.getMonthInYear())
        assertSame(QuarterInYear.Q1, Year(2020).jan.getQuarterInYear())
        assertSame(MonthInQuarter.FIRST, Year(2020).jan.getMonthInQuarter())
    }

    @Test
    fun testGetMs() {
        assertEquals(1, Year(2020).jan.getMM())
        assertEquals("Jan", Year(2020).jan.getMMM())
        assertEquals("January", Year(2020).jan.getMMMM())
    }

    @Test
    fun testGetQs() {
        assertEquals(1, Year(2020).jan.getQ())
        assertEquals("Q1", Year(2020).jan.getQQ())
    }

    @Test
    fun testGetYs() {
        assertEquals(20, Year(2020).jan.getYY())
        assertEquals(2020, Year(2020).jan.getYYYY())
    }

    @Test
    fun testEquality() {
        assertEquals(Year(2020).jan, Year(2020).jan)
        assertNotEquals(Year(2020).jan, Year(2020).feb)
        assertNotEquals(Year(2020).jan, Year(2020).q1)
    }

    @Test
    fun testToString() {
        assertEquals("Jan 2020", Year(2020).jan.toString())
    }

    @Test
    fun testHashCode() {
        assertEquals(Year(2020).jan.hashCode(), Year(2020).jan.hashCode())
        assertNotEquals(Year(2020).jan.hashCode(), Year(2020).dec.hashCode())
        assertNotEquals(Year(2020).jan.hashCode(), Year(2021).jan.hashCode())
    }
}