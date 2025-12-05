package aoc2025.day05

import java.io.File

class DayFive(filePath: String) {
    private val ranges: List<LongRange>
    private val valsToCheck: List<Long>

    init {
        var r = mutableListOf<LongRange>()
        var checks = mutableListOf<Long>()
        var stillOnRanges = true
        File(filePath).forEachLine {
            if (it.isEmpty()) {
                stillOnRanges = false
            } else if (stillOnRanges) {
                val split = it.split("-")
                r.add(split.first().toLong()..split.last().toLong())
            } else {
                checks.add(it.toLong())
            }
        }
        ranges = r
        valsToCheck = checks
    }

    fun partA(): Int {
        return valsToCheck.count { ranges.any { check -> it in check } }
    }

    fun partB(): Long {
        val rList = ranges.toMutableList()
        for (i in rList.indices.reversed()) {
            val it = rList[i]
            val f = rList.indexOfFirst { check -> it.first in check && it != check }
            if (f == -1) continue
            if (rList[f].last < it.last) { rList[f] = rList[f].first..it.last }
            rList.removeAt(i)
        }

        return rList
            .toSet()
            .sumOf { (1 + it.last - it.first) }
    }
}
