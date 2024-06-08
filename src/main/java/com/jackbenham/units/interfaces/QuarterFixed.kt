package com.jackbenham.units.interfaces

import com.jackbenham.units.Quarter

interface QuarterFixed : QuarterFloating {
    fun toQuarter(): Quarter
}