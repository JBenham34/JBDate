package com.jackbenham.ranges

import com.jackbenham.relations.MonthInYear
import com.jackbenham.relations.QuarterInYear
import com.jackbenham.units.Month
import com.jackbenham.units.Quarter
import com.jackbenham.units.Year

class YearRange private constructor(private val start_: Year, private val end_: Year) : AbstractDateRange<Year>(start_, end_) {
    companion object {
        operator fun invoke(start: Year, end: Year): YearRange? {
            if (start <= end)
                return YearRange(start, end)
            return null
        }
    }

    fun toMonthRange(): MonthRange = toMonthRange(null, null)!!

    fun toMonthRange(startMonth: MonthInYear? = null, endMonth: MonthInYear? = null): MonthRange? = MonthRange(
            Month(start_, startMonth ?: MonthInYear.JAN),
            Month(end_, endMonth ?: MonthInYear.DEC)
    )

    fun toQuarterRange(): QuarterRange = toQuarterRange(null, null)!!

    fun toQuarterRange(startQuarter: QuarterInYear? = null, endQuarter: QuarterInYear? = null): QuarterRange? = QuarterRange(
            Quarter(start_, startQuarter ?: QuarterInYear.Q1),
            Quarter(end_, endQuarter ?: QuarterInYear.Q4)
    )
}