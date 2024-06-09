package com.jackbenham.units

import com.jackbenham.relations.MonthInYear
import com.jackbenham.relations.QuarterInYear
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class YearTest {
    @Test
    fun testCreation() {
        val y2020 = Year(2020)
        val y2020Again = Year(2020)
        assertSame(y2020, y2020Again)

        val y2021 = Year(2021)
        assertNotSame(y2021, y2020)
    }

    @Test
    fun testFromKey() {
        val y2020 = Year.fromKey(2020)
        val y2020Again = Year.fromKey(2020)
        assertSame(y2020, y2020Again)

        val y2021 = Year.fromKey(2021)
        assertNotSame(y2021, y2020)
    }

    @Test
    fun testGetYs() {
        assertEquals(20, Year(2020).getYY())
        assertEquals(2020, Year(2020).getYYYY())
    }

    @Test
    fun testNext() {
        assertSame(Year(2021), Year(2020).next())
        assertSame(Year(2022), Year(2021).next())
        assertSame(Year(2022), Year(2020).next().next())
    }

    @Test
    fun testPrev() {
        assertSame(Year(2021), Year(2022).prev())
        assertSame(Year(2020), Year(2021).prev())
        assertSame(Year(2020), Year(2022).prev().prev())
    }

    @Test
    fun testComparisons() {
        assertTrue(Year(2019) < Year(2020))
        assertTrue(Year(2019) <= Year(2020))
        assertTrue(Year(2021) > Year(2020))
        assertTrue(Year(2021) >= Year(2020))
        assertTrue(Year(2020) <= Year(2020))
        assertTrue(Year(2020) >= Year(2020))
    }

    @Test
    fun testToYear() {
        assertSame(Year(2020), Year(2020).toYear())
    }

    @Test
    fun testEquality() {
        assertEquals(Year(2020), Year(2020))
        assertNotEquals(Year(2020), Year(2021))
        assertNotEquals(Year(2020), Year(2021).APR)
    }

    @Test
    fun testMonthPseudoEnums() {
        assertEquals(Month(Year(2020), MonthInYear.JAN), Year(2020).JAN)
        assertEquals(Month(Year(2020), MonthInYear.FEB), Year(2020).FEB)
        assertEquals(Month(Year(2020), MonthInYear.MAR), Year(2020).MAR)
        assertEquals(Month(Year(2020), MonthInYear.APR), Year(2020).APR)
        assertEquals(Month(Year(2020), MonthInYear.MAY), Year(2020).MAY)
        assertEquals(Month(Year(2020), MonthInYear.JUN), Year(2020).JUN)
        assertEquals(Month(Year(2020), MonthInYear.JUL), Year(2020).JUL)
        assertEquals(Month(Year(2020), MonthInYear.AUG), Year(2020).AUG)
        assertEquals(Month(Year(2020), MonthInYear.SEP), Year(2020).SEP)
        assertEquals(Month(Year(2020), MonthInYear.OCT), Year(2020).OCT)
        assertEquals(Month(Year(2020), MonthInYear.NOV), Year(2020).NOV)
        assertEquals(Month(Year(2020), MonthInYear.DEC), Year(2020).DEC)
    }

    @Test
    fun testQuarterPseudoEnums() {
        assertEquals(Quarter(Year(2020), QuarterInYear.Q1), Year(2020).Q1)
        assertEquals(Quarter(Year(2020), QuarterInYear.Q2), Year(2020).Q2)
        assertEquals(Quarter(Year(2020), QuarterInYear.Q3), Year(2020).Q3)
        assertEquals(Quarter(Year(2020), QuarterInYear.Q4), Year(2020).Q4)
    }

    @Test
    fun testString() {
        assertEquals("2020", Year(2020).toString())
    }

    @Test
    fun testHashCode() {
        assertEquals(2020, Year(2020).hashCode())
    }
}