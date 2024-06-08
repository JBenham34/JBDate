package com.jackbenham.units

import com.jackbenham.relations.MonthInYear
import com.jackbenham.relations.QuarterInYear
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MonthTest {
    @Test
    fun creation() {
        val q1_2020 = Quarter(Year(2020), QuarterInYear.Q1)
        val q1_2020again = Quarter(Year(2020), QuarterInYear.Q1)
        assertSame(q1_2020, q1_2020again)

        val q2_2020 = Quarter(Year(2020), QuarterInYear.Q2)
        assertNotSame(q1_2020, q2_2020)
    }

    @Test
    fun fromYear() {
        assertSame(Year(2020).q1, Quarter(Year(2020), QuarterInYear.Q1))
        assertNotSame(Year(2020).q1, Year(2020).q2)
    }

    @Test
    fun getQ() {
        assertEquals(1, Year(2020).q1.getQ())
        assertEquals(2, Year(2021).q2.getQ())
        assertEquals(3, Year(2022).q3.getQ())
    }

    @Test
    fun getQQ() {
        assertEquals("Q1", Year(2020).q1.getQQ())
        assertEquals("Q2", Year(2021).q2.getQQ())
        assertEquals("Q3", Year(2022).q3.getQQ())
    }

    @Test
    fun getYYYY() {
        assertEquals(2020, Year(2020).q1.getYYYY())
        assertEquals(2021, Year(2021).q2.getYYYY())
        assertEquals(2022, Year(2022).q3.getYYYY())
    }

    @Test
    fun next() {
        assertEquals(Year(2020).q2, Year(2020).q1.next())
        assertEquals(Year(2020).q3, Year(2020).q2.next())
        assertEquals(Year(2021).q1, Year(2020).q3.next().next())

        assertEquals(Year(2021).jan, Year(2020).dec.next())
    }

    @Test
    fun prev() {
        assertEquals(Year(2020).q1, Year(2020).q2.prev())
        assertEquals(Year(2020).q2, Year(2020).q3.prev())
        assertEquals(Year(2020).q1, Year(2020).q3.prev().prev())

        assertEquals(Year(2020).q4, Year(2021).q1.prev())
    }

    @Test
    fun add() {
        assertEquals(Year(2020).q2, Year(2020).q1.add(1))
        assertEquals(Year(2020).q3, Year(2020).q1.add(2))
        assertEquals(Year(2023).q1, Year(2020).q1.add(12))

        assertEquals(Year(2019).q4, Year(2020).q1.add(-1))
        assertEquals(Year(2019).q3, Year(2020).q1.add(-2))
        assertEquals(Year(2017).q1, Year(2020).q1.add(-12))
    }

    @Test
    fun testComparisons() {
        assertTrue(Year(2020).q1 < Year(2020).q2)
        assertTrue(Year(2020).q1 <= Year(2020).q2)
        assertTrue(Year(2020).q3 > Year(2020).q2)
        assertTrue(Year(2020).q3 >= Year(2020).q2)
        assertTrue(Year(2020).q1 > Year(2019).q4)
    }
}