package com.jackbenham.units.interfaces

import com.jackbenham.relations.QuarterInYear

interface QuarterFloating {
    fun getQuarterInYear(): QuarterInYear
    fun getQ(): Int
    fun getQQ(): String
}