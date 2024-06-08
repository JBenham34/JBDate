package com.jackbenham.units

import com.jackbenham.relations.MonthInYear
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class QuarterTest {
    @Test
    fun creation() {
        val apr2020 = Month(Year(2020), MonthInYear.APR)
        val apr2020Again = Month(Year(2020), MonthInYear.APR)
        Assertions.assertSame(apr2020, apr2020Again)

        val may2020 = Month(Year(2020), MonthInYear.MAY)
        Assertions.assertNotSame(may2020, apr2020)
    }

    @Test
    fun fromYear() {
        val apr2020: Month = Year(2020).apr
        val apr2020Again: Month = Year(2020).apr
        Assertions.assertSame(apr2020, apr2020Again)

        val may2020: Month = Year(2020).may
        Assertions.assertNotSame(may2020, apr2020)
    }

    @Test
    fun getMM() {
        Assertions.assertEquals(4, Year(2020).apr.getMM())
        Assertions.assertEquals(7, Year(2021).jul.getMM())
        Assertions.assertEquals(9, Year(2022).sep.getMM())
    }

    @Test
    fun getYYYY() {
        Assertions.assertEquals(2020, Year(2020).apr.getYYYY())
        Assertions.assertEquals(2021, Year(2021).jul.getYYYY())
        Assertions.assertEquals(2022, Year(2022).sep.getYYYY())
    }

    @Test
    fun next() {
        Assertions.assertEquals(Year(2020).apr, Year(2020).mar.next())
        Assertions.assertEquals(Year(2020).may, Year(2020).apr.next())
        Assertions.assertEquals(Year(2020).may, Year(2020).mar.next().next())

        Assertions.assertEquals(Year(2021).jan, Year(2020).dec.next())
    }

    @Test
    fun prev() {
        Assertions.assertEquals(Year(2020).mar, Year(2020).apr.prev())
        Assertions.assertEquals(Year(2020).apr, Year(2020).may.prev())
        Assertions.assertEquals(Year(2020).mar, Year(2020).may.prev().prev())

        Assertions.assertEquals(Year(2020).dec, Year(2021).jan.prev())
    }

    @Test
    fun add() {
        Assertions.assertEquals(Year(2020).apr, Year(2020).mar.add(1))
        Assertions.assertEquals(Year(2020).may, Year(2020).mar.add(2))
        Assertions.assertEquals(Year(2021).mar, Year(2020).mar.add(12))

        Assertions.assertEquals(Year(2020).apr, Year(2020).may.add(-1))
        Assertions.assertEquals(Year(2020).mar, Year(2020).may.add(-2))
        Assertions.assertEquals(Year(2019).mar, Year(2020).mar.add(-12))
    }

    @Test
    fun testComparisons() {
        Assertions.assertTrue(Year(2020).mar < Year(2020).apr)
        Assertions.assertTrue(Year(2020).mar <= Year(2020).apr)
        Assertions.assertTrue(Year(2020).may > Year(2020).apr)
        Assertions.assertTrue(Year(2020).may >= Year(2020).apr)
        Assertions.assertTrue(Year(2020).jan > Year(2019).dec)
    }
}