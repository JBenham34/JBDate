package com.jackbenham.ranges

import com.jackbenham.relations.MonthInQuarter
import com.jackbenham.relations.MonthInYear
import com.jackbenham.units.Month
import com.jackbenham.units.Quarter
import com.jackbenham.units.Year

class MonthRange private constructor(private val start_: Month, private val end_: Month) : AbstractDateRange<Month>(start_, end_) {
    companion object {
        operator fun invoke(start: Month, end: Month): MonthRange? {
            if (start <= end)
                return MonthRange(start, end)
            return null
        }
    }

    fun toQuarterRange(startingFromFirst: MonthInQuarter = MonthInQuarter.LAST, endingAtLast: MonthInQuarter = MonthInQuarter.FIRST): QuarterRange? {
        val startQuarter: Quarter = if (start_.getMonthInQuarter() <= startingFromFirst) start_.toQuarter()
        else start_.toQuarter().next()

        val endQuarter: Quarter = if (end_.getMonthInQuarter() >= endingAtLast) end_.toQuarter()
        else end_.toQuarter().prev()

        return QuarterRange(startQuarter, endQuarter)
    }

    fun toYearRange(startCutoff: MonthInYear = MonthInYear.DEC, endCutoff: MonthInYear = MonthInYear.JAN): YearRange? {
        val startYear: Year = if (start_.getMonthInYear() <= startCutoff) start_.toYear()
        else start_.toYear().next()

        val endYear: Year = if (end_.getMonthInYear() >= endCutoff) end_.toYear()
        else end_.toYear().prev()

        return YearRange(startYear, endYear)
    }
}