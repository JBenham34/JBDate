package com.jackbenham.units

import com.jackbenham.relations.QuarterInYear
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class QuarterTest {
    @Test
    fun testCreation() {
        val qOne2020 = Quarter(Year(2020), QuarterInYear.Q1)
        val qOne2020again = Quarter(Year(2020), QuarterInYear.Q1)
        Assertions.assertSame(qOne2020, qOne2020again)

        val qTwo2020 = Quarter(Year(2020), QuarterInYear.Q2)
        Assertions.assertNotSame(qOne2020, qTwo2020)
    }

    @Test
    fun testFromYear() {
        Assertions.assertSame(Year(2020).Q1, Quarter(Year(2020), QuarterInYear.Q1))
        Assertions.assertNotSame(Year(2020).Q1, Year(2020).Q2)
    }

    @Test
    fun testGetQ() {
        Assertions.assertEquals(1, Year(2020).Q1.getQ())
        Assertions.assertEquals(2, Year(2021).Q2.getQ())
        Assertions.assertEquals(3, Year(2022).Q3.getQ())
    }

    @Test
    fun testGetQQ() {
        Assertions.assertEquals("Q1", Year(2020).Q1.getQQ())
        Assertions.assertEquals("Q2", Year(2021).Q2.getQQ())
        Assertions.assertEquals("Q3", Year(2022).Q3.getQQ())
    }

    @Test
    fun testGetYYYY() {
        Assertions.assertEquals(2020, Year(2020).Q1.getYYYY())
        Assertions.assertEquals(2021, Year(2021).Q2.getYYYY())
        Assertions.assertEquals(2022, Year(2022).Q3.getYYYY())
    }

    @Test
    fun testNext() {
        Assertions.assertEquals(Year(2020).Q2, Year(2020).Q1.next())
        Assertions.assertEquals(Year(2020).Q3, Year(2020).Q2.next())
        Assertions.assertEquals(Year(2021).Q1, Year(2020).Q3.next().next())

        Assertions.assertEquals(Year(2021).JAN, Year(2020).DEC.next())
    }

    @Test
    fun testPrev() {
        Assertions.assertEquals(Year(2020).Q1, Year(2020).Q2.prev())
        Assertions.assertEquals(Year(2020).Q2, Year(2020).Q3.prev())
        Assertions.assertEquals(Year(2020).Q1, Year(2020).Q3.prev().prev())

        Assertions.assertEquals(Year(2020).Q4, Year(2021).Q1.prev())
    }

    @Test
    fun testAdd() {
        Assertions.assertEquals(Year(2020).Q2, Year(2020).Q1.add(1))
        Assertions.assertEquals(Year(2020).Q3, Year(2020).Q1.add(2))
        Assertions.assertEquals(Year(2023).Q1, Year(2020).Q1.add(12))

        Assertions.assertEquals(Year(2019).Q4, Year(2020).Q1.add(-1))
        Assertions.assertEquals(Year(2019).Q3, Year(2020).Q1.add(-2))
        Assertions.assertEquals(Year(2017).Q1, Year(2020).Q1.add(-12))
    }

    @Test
    fun testComparisons() {
        Assertions.assertTrue(Year(2020).Q1 < Year(2020).Q2)
        Assertions.assertTrue(Year(2020).Q1 <= Year(2020).Q2)
        Assertions.assertTrue(Year(2020).Q3 > Year(2020).Q2)
        Assertions.assertTrue(Year(2020).Q3 >= Year(2020).Q2)
        Assertions.assertTrue(Year(2020).Q1 > Year(2019).Q4)
    }
}