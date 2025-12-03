package aoc2025.day02

import java.io.File

class DayTwo(filePath: String) {
    val input = File(filePath)
        .readLines()
        .map { it.map { i -> i.digitToInt() } }


    fun partA(): Long {
        return input.sumOf { getMaxLongOfLength(it, 2) }
    }

    fun partB(): Long {
        return input.sumOf { getMaxLongOfLength(it, 12) }
    }

    private fun getMaxLongOfLength(intList: List<Int>, length: Int): Long {
        val rangeMap = intList.foldIndexed(List(length) { 0 }) { index, acc, i ->
            val numberToChange = acc.mapIndexed { ind, it ->
                val possibleNumbersLeft = intList.size - index
                if (possibleNumbersLeft >= length - ind && i > it) 1 else 0
            }.indexOfFirst { it == 1 }
            if (numberToChange < 0) {
                acc
            } else {
                acc.take(numberToChange) + listOf(i) +  List(length - numberToChange - 1) { 0 }
            }
        }

        return rangeMap.joinToString("") { it.toString() }.toLong()
    }
}