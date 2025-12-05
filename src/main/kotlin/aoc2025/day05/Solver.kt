package aoc2025.day05

import java.io.File

class Solver(filePath: String) {
    private val ranges: List<LongRange>
    private val valsToCheck: List<Long>

    init {
        val lines = File(filePath).readLines()
        val emptyLineIndex = lines.indexOfFirst { it.isEmpty() }
        ranges = lines.take(emptyLineIndex).map {
            val (start, end) = it.split("-").map(String::toLong)
            start..end
        }
        valsToCheck = lines.drop(emptyLineIndex + 1).map(String::toLong)
    }

    fun partA(): Int {
        return valsToCheck.count { ranges.any { check -> it in check } }
    }

    fun partB(): Long {
        val mutableRangeList = ranges.toMutableList()
        for (i in mutableRangeList.lastIndex downTo 0) {
            val range = mutableRangeList[i]
            val indexOverlappingRange = mutableRangeList.indexOfFirst { check -> range.first in check && range != check }
            if (indexOverlappingRange == -1) continue
            val target = mutableRangeList[indexOverlappingRange]
            if (target.last < range.last) {
                mutableRangeList[indexOverlappingRange] = target.first..range.last
            }
            mutableRangeList.removeAt(i)
        }

        return mutableRangeList.toSet().sumOf { (1 + it.last - it.first) }
    }
}

