package com.jackbenham.units

import com.jackbenham.relations.MonthInYear
import com.jackbenham.relations.QuarterInYear
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class QuarterTest {
    @Test
    fun testCreation() {
        val qOne2020 = Quarter(Year(2020), QuarterInYear.Q1)
        val qOne2020again = Quarter(Year(2020), QuarterInYear.Q1)
        assertSame(qOne2020, qOne2020again)

        val qTwo2020 = Quarter(Year(2020), QuarterInYear.Q2)
        assertNotSame(qOne2020, qTwo2020)
    }

    @Test
    fun testFromYear() {
        assertSame(Year(2020).Q1, Quarter(Year(2020), QuarterInYear.Q1))
        assertNotSame(Year(2020).Q1, Year(2020).Q2)
    }

    @Test
    fun testGetQs() {
        assertEquals(1, Year(2020).Q1.getQ())
        assertEquals("Q1", Year(2020).Q1.getQQ())
    }

    @Test
    fun testGetYs() {
        assertEquals(2020, Year(2020).Q1.getYYYY())
        assertEquals(2021, Year(2021).Q2.getYYYY())
        assertEquals(2022, Year(2022).Q3.getYYYY())
        assertEquals(20, Year(2020).Q1.getYY())
        assertEquals(21, Year(2021).Q2.getYY())
        assertEquals(22, Year(2022).Q3.getYY())
    }

    @Test
    fun testNext() {
        assertEquals(Year(2020).Q2, Year(2020).Q1.next())
        assertEquals(Year(2020).Q3, Year(2020).Q2.next())
        assertEquals(Year(2021).Q1, Year(2020).Q3.next().next())

        assertEquals(Year(2021).JAN, Year(2020).DEC.next())
    }

    @Test
    fun testPrev() {
        assertEquals(Year(2020).Q1, Year(2020).Q2.prev())
        assertEquals(Year(2020).Q2, Year(2020).Q3.prev())
        assertEquals(Year(2020).Q1, Year(2020).Q3.prev().prev())

        assertEquals(Year(2020).Q4, Year(2021).Q1.prev())
    }

    @Test
    fun testAdd() {
        assertEquals(Year(2020).Q2, Year(2020).Q1.add(1))
        assertEquals(Year(2020).Q3, Year(2020).Q1.add(2))
        assertEquals(Year(2023).Q1, Year(2020).Q1.add(12))

        assertEquals(Year(2019).Q4, Year(2020).Q1.add(-1))
        assertEquals(Year(2019).Q3, Year(2020).Q1.add(-2))
        assertEquals(Year(2017).Q1, Year(2020).Q1.add(-12))
    }

    @Test
    fun testGetQuarterInYear() {
        assertEquals(QuarterInYear.Q1, Year(2020).Q1.getQuarterInYear())
        assertEquals(QuarterInYear.Q1, Year(2021).Q1.getQuarterInYear())
        assertEquals(QuarterInYear.Q2, Year(2022).Q2.getQuarterInYear())
    }

    @Test
    fun testComparisons() {
        assertTrue(Year(2020).Q1 < Year(2020).Q2)
        assertTrue(Year(2020).Q1 <= Year(2020).Q2)
        assertTrue(Year(2020).Q3 > Year(2020).Q2)
        assertTrue(Year(2020).Q3 >= Year(2020).Q2)
        assertTrue(Year(2020).Q1 > Year(2019).Q4)
    }

    @Test
    fun testConversions() {
        assertSame(Year(2020).Q1, Year(2020).Q1.toQuarter())
        assertSame(Year(2020), Year(2020).Q1.toYear())
    }

    @Test
    fun testMonthPseudoEnums() {
        assertEquals(Year(2020).JAN, Year(2020).Q1.FIRST)
        assertEquals(Year(2020).FEB, Year(2020).Q1.MIDDLE)
        assertEquals(Year(2020).MAR, Year(2020).Q1.LAST)
    }

    @Test
    fun testToString() {
        assertEquals("Q2 2020", Year(2020).Q2.toString())
    }

    @Test
    fun testHashCode() {
        assertEquals(2020 * 4 + 2, Year(2020).Q3.hashCode())
    }

    @Test
    fun testEquals() {
        assertEquals(Year(2020).Q3, Year(2020).Q3)
        assertNotEquals(Year(2020).Q3, Year(2020).Q4)
        assertNotEquals(Year(2020).Q3, Year(2020).DEC)
    }
}