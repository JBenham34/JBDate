package com.jackbenham.ranges

import com.jackbenham.relations.MonthInQuarter
import com.jackbenham.relations.QuarterInYear
import com.jackbenham.units.Month
import com.jackbenham.units.Quarter
import com.jackbenham.units.Year

class QuarterRange private constructor(start: Quarter, end: Quarter) : AbstractDateRange<Quarter>(start, end) {
    companion object {
        operator fun invoke(start: Quarter, end: Quarter): QuarterRange? {
            if (start <= end)
                return QuarterRange(start, end)
            return null
        }
    }

    fun toMonthRange(): MonthRange = toMonthRange(null, null)!!

    fun toMonthRange(startMonth: MonthInQuarter? = null, endMonth: MonthInQuarter? = null): MonthRange? = MonthRange(
            Month(start_, startMonth ?: MonthInQuarter.FIRST),
            Month(end_, endMonth ?: MonthInQuarter.LAST)
    )

    fun toYearRange(startingFromFirst: QuarterInYear = QuarterInYear.Q4, endingAtLast: QuarterInYear = QuarterInYear.Q1): YearRange? {
        val startYear: Year = if (start_.getQuarterInYear() <= startingFromFirst) start_.toYear()
        else start_.toYear().next()

        val endYear: Year = if (end_.getQuarterInYear() >= endingAtLast) end_.toYear()
        else end_.toYear().prev()

        return YearRange(startYear, endYear)
    }

}
