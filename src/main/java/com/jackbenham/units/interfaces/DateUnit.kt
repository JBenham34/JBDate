package com.jackbenham.units.interfaces

import com.jackbenham.*

interface DateUnit<T : DateUnit<T>> : Keyed<Int>, Comparable<T>, Sequential<T> {
    override fun next(): T
    override fun prev(): T
}