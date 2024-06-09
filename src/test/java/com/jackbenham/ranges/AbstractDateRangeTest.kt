package com.jackbenham.ranges

import com.jackbenham.units.Month
import com.jackbenham.units.Year
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.stream.Stream

class AbstractDateRangeTest {
    @Test
    fun testCreation() {
        val range = MonthRange(Year(2020).JAN, Year(2020).DEC)
        val duplicateRange = MonthRange(Year(2020).JAN, Year(2020).DEC)
        assertEquals(range, duplicateRange)
        assertNotSame(range, duplicateRange)

        val differentRange = MonthRange(Year(2021).JUL, Year(2021).DEC)
        assertNotEquals(range, differentRange)
        assertNotSame(range, differentRange)

        assertNull(MonthRange(Year(2020).DEC, Year(2020).JAN))
    }

    @Test
    fun testGetStart() {
        val range = MonthRange(Year(2020).JAN, Year(2020).DEC)!!
        val differentRange = MonthRange(Year(2021).JUL, Year(2021).DEC)!!
        
        assertSame(Year(2020).JAN, range.getStart())
        assertSame(Year(2021).JUL, differentRange.getStart())
    }

    @Test
    fun testGetEnd() {
        val range = MonthRange(Year(2020).JAN, Year(2020).DEC)!!
        val differentRange = MonthRange(Year(2021).JUL, Year(2021).DEC)!!

        assertSame(Year(2020).DEC, range.getEnd())
        assertSame(Year(2021).DEC, differentRange.getEnd())
    }

    @Test
    fun testList() {
        val range = MonthRange(Year(2020).JAN, Year(2020).DEC)!!
        val differentRange = MonthRange(Year(2021).JUL, Year(2021).DEC)!!
        val list: List<Month> = range.list()
        val differentList: List<Month> = differentRange.list()

        assertEquals(12, list.size)
        assertEquals(6, differentList.size)

        assertSame(Year(2020).JAN, list[0])
        assertSame(Year(2020).FEB, list[1])
        assertSame(Year(2020).MAR, list[2])
        assertSame(Year(2020).APR, list[3])
        assertSame(Year(2020).MAY, list[4])
        assertSame(Year(2020).JUN, list[5])
        assertSame(Year(2020).JUL, list[6])
        assertSame(Year(2020).AUG, list[7])
        assertSame(Year(2020).SEP, list[8])
        assertSame(Year(2020).OCT, list[9])
        assertSame(Year(2020).NOV, list[10])
        assertSame(Year(2020).DEC, list[11])

        assertSame(Year(2021).JUL, differentList[0])
        assertSame(Year(2021).AUG, differentList[1])
        assertSame(Year(2021).SEP, differentList[2])
        assertSame(Year(2021).OCT, differentList[3])
        assertSame(Year(2021).NOV, differentList[4])
        assertSame(Year(2021).DEC, differentList[5])
    }

    @Test
    fun testStream() {
        val range = MonthRange(Year(2020).JAN, Year(2020).DEC)!!
        val differentRange = MonthRange(Year(2021).JUL, Year(2021).DEC)!!
        val stream: Stream<Month> = range.stream()
        assertEquals(range.list(), stream.toList())

        val differentStream: Stream<Month> = differentRange.stream()
        assertEquals(differentRange.list(), differentStream.toList())
    }

    @Test
    fun testIterator() {
        val range = MonthRange(Year(2020).JAN, Year(2020).DEC)!!
        val differentRange = MonthRange(Year(2021).JUL, Year(2021).DEC)!!

        for (month in range) assertTrue(range.contains(month))
        for (month in differentRange) assertTrue(differentRange.contains(month))
    }

    @Test
    fun testToString() {
        assertEquals("Jan 2020 - Dec 2020", MonthRange(Year(2020).JAN, Year(2020).DEC).toString())
        assertEquals("Q1 2020 - Q4 2020", QuarterRange(Year(2020).Q1, Year(2020).Q4).toString())
        assertEquals("2020 - 2020", YearRange(Year(2020), Year(2020)).toString())
    }
}