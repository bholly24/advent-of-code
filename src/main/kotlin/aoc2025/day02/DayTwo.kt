package aoc2025.day02

import java.io.File

class DayTwo(filePath: String) {
    val input = File(filePath).readLines()

    fun partA(): Int {
        val ints = input.map { it.map { i -> i.digitToInt() } }
        val total = ints.sumOf {
            var i = 0
            var j = 0
            for (a in it.indices) {
                if (a < it.size - 1 && it[a] > i) {
                    i = it[a]
                    j = 0
                } else if (it[a] > j) {
                    j = it[a]
                }
            }
            val r = i * 10 + j
            println(r)
            r
        }
        println("Result is $total")
        return total
    }

    fun partB(): Long {
        val ints = input.map { it.map { i -> i.digitToInt() } }
        val total = ints.sumOf {
            val rangeMap = (0 until 12).toList().map { it -> 0 }.toMutableList()
            for (numIndex in it.indices) {
                var newHigh = false
                for (currentRangeIndex in rangeMap.indices) {
                    val possibleNumbersLeft = it.size - numIndex
                    if (newHigh) {
                        rangeMap[currentRangeIndex] = 0
                    } else if ((possibleNumbersLeft >= 12 - currentRangeIndex) && it[numIndex] > rangeMap[currentRangeIndex]) {
                        rangeMap[currentRangeIndex] = it[numIndex]
                        newHigh = true
                    }
                }
            }
            rangeMap.joinToString("") { it.toString() }.toLong()
        }
        println("Result is $total")
        return total
    }
}