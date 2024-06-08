package com.jackbenham.units

import com.jackbenham.units.Year
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class YearTest {

    @Test
    fun creation() {
        val y2020 = Year(2020)
        val y2020Again = Year(2020)
        assertSame(y2020, y2020Again)

        val y2021 = Year(2021)
        assertNotSame(y2021, y2020)
    }

    @Test
    fun getYYYY() {
        assertEquals(2020, Year(2020).getYYYY())
        assertEquals(1444, Year(1444).getYYYY())
        assertEquals(1821, Year(1821).getYYYY())
        assertEquals(1948, Year(1948).getYYYY())
        assertEquals(-2150, Year(-2150).getYYYY())
    }

    @Test
    fun next() {
        assertSame(Year(2021), Year(2020).next())
        assertSame(Year(2022), Year(2021).next())
        assertSame(Year(2022), Year(2020).next().next())
    }

    @Test
    fun prev() {
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
}