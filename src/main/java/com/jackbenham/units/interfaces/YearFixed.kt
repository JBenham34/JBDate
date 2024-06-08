package com.jackbenham.units.interfaces

import com.jackbenham.units.Year

interface YearFixed : YearFloating {
    fun toYear(): Year
}