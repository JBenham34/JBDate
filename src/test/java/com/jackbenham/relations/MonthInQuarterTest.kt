package com.jackbenham.relations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MonthInQuarterTest {
    @Test
    fun next() {
        assertSame(MonthInQuarter.MIDDLE, MonthInQuarter.FIRST.next())
        assertSame(MonthInQuarter.LAST, MonthInQuarter.MIDDLE.next())
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.LAST.next())
    }

    @Test
    fun prev() {
        assertSame(MonthInQuarter.LAST, MonthInQuarter.FIRST.prev())
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.MIDDLE.prev())
        assertSame(MonthInQuarter.MIDDLE, MonthInQuarter.LAST.prev())
    }

    @Test
    fun add() {
        assertSame(MonthInQuarter.MIDDLE, MonthInQuarter.FIRST.add(1))
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.FIRST.add(12))
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.FIRST.add(24))
        assertSame(MonthInQuarter.LAST, MonthInQuarter.MIDDLE.add(13))
        assertSame(MonthInQuarter.FIRST, MonthInQuarter.MIDDLE.add(11))
        assertSame(MonthInQuarter.MIDDLE, MonthInQuarter.LAST.add(-1))
        assertSame(MonthInQuarter.LAST, MonthInQuarter.LAST.add(-3))
        assertSame(MonthInQuarter.LAST, MonthInQuarter.LAST.add(-12))
    }
}