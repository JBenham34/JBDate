package com.jackbenham.units.interfaces

import com.jackbenham.units.Month

interface MonthFixed : MonthFloating {
    fun toMonth(): Month;
}