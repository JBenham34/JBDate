package com.jackbenham.ranges

import com.jackbenham.units.Month
import com.jackbenham.units.Year
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.stream.Stream

class AbstractDateRangeTest {
    @Test
    fun testCreation() {
        val range = MonthRange(Year(2020).jan, Year(2020).dec)
        val duplicateRange = MonthRange(Year(2020).jan, Year(2020).dec)
        assertEquals(range, duplicateRange)
        assertNotSame(range, duplicateRange)

        val differentRange = MonthRange(Year(2021).jul, Year(2021).dec)
        assertNotEquals(range, differentRange)
        assertNotSame(range, differentRange)

        assertNull(MonthRange(Year(2020).dec, Year(2020).jan))
    }

    @Test
    fun testGetStart() {
        val range = MonthRange(Year(2020).jan, Year(2020).dec)!!
        val differentRange = MonthRange(Year(2021).jul, Year(2021).dec)!!
        
        assertSame(Year(2020).jan, range.getStart())
        assertSame(Year(2021).jul, differentRange.getStart())
    }

    @Test
    fun testGetEnd() {
        val range = MonthRange(Year(2020).jan, Year(2020).dec)!!
        val differentRange = MonthRange(Year(2021).jul, Year(2021).dec)!!

        assertSame(Year(2020).dec, range.getEnd())
        assertSame(Year(2021).dec, differentRange.getEnd())
    }

    @Test
    fun testList() {
        val range = MonthRange(Year(2020).jan, Year(2020).dec)!!
        val differentRange = MonthRange(Year(2021).jul, Year(2021).dec)!!
        val list: List<Month> = range.list()
        val differentList: List<Month> = differentRange.list()

        assertEquals(12, list.size)
        assertEquals(6, differentList.size)

        assertSame(Year(2020).jan, list[0])
        assertSame(Year(2020).feb, list[1])
        assertSame(Year(2020).mar, list[2])
        assertSame(Year(2020).apr, list[3])
        assertSame(Year(2020).may, list[4])
        assertSame(Year(2020).jun, list[5])
        assertSame(Year(2020).jul, list[6])
        assertSame(Year(2020).aug, list[7])
        assertSame(Year(2020).sep, list[8])
        assertSame(Year(2020).oct, list[9])
        assertSame(Year(2020).nov, list[10])
        assertSame(Year(2020).dec, list[11])

        assertSame(Year(2021).jul, differentList[0])
        assertSame(Year(2021).aug, differentList[1])
        assertSame(Year(2021).sep, differentList[2])
        assertSame(Year(2021).oct, differentList[3])
        assertSame(Year(2021).nov, differentList[4])
        assertSame(Year(2021).dec, differentList[5])
    }

    @Test
    fun testStream() {
        val range = MonthRange(Year(2020).jan, Year(2020).dec)!!
        val differentRange = MonthRange(Year(2021).jul, Year(2021).dec)!!
        val stream: Stream<Month> = range.stream()
        assertEquals(range.list(), stream.toList())

        val differentStream: Stream<Month> = differentRange.stream()
        assertEquals(differentRange.list(), differentStream.toList())
    }

    @Test
    fun testIterator() {
        val range = MonthRange(Year(2020).jan, Year(2020).dec)!!
        val differentRange = MonthRange(Year(2021).jul, Year(2021).dec)!!

        for (month in range) assertTrue(range.contains(month))
        for (month in differentRange) assertTrue(differentRange.contains(month))
    }
}