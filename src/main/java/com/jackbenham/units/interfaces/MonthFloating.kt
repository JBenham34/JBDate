package com.jackbenham.units.interfaces

import com.jackbenham.relations.MonthInQuarter
import com.jackbenham.relations.MonthInYear

interface MonthFloating {
    fun getMonthInYear() : MonthInYear
    fun getMonthInQuarter() : MonthInQuarter
    fun getMM(): Int
    fun getMMM(): String
    fun getMMMM(): String
}