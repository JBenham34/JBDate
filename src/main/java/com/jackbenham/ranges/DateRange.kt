package com.jackbenham.ranges

import com.jackbenham.Container
import com.jackbenham.Listable
import com.jackbenham.Streamable
import com.jackbenham.units.interfaces.DateUnit

interface DateRange<T : DateUnit<T>> : Streamable<T>, Listable<T>, Iterable<T>, Container<T> {
    fun getStart(): T
    fun getEnd(): T
}