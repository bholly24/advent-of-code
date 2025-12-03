package aoc2024.day10

import java.io.File

class DayTen(path: String) {
    private val startingStones: List<Long> = File(path).readLines()[0].split(" ").map { it.toLong() }

    fun partA(): Long {
        return getMemoizedStones(listOf(10, 10, 5))
    }

    fun partB(): Long {
        return getMemoizedStones(listOf(25, 25, 25))
    }

    private fun getMemoizedStones(breaks: List<Int>): Long {
        var totalFromFirstBreak = 0L
        var total = 0L
        val firstMemoizationMap = mutableMapOf<Long, Long>()
        val secondMemoizationMap = mutableMapOf<Long, Long>()

        startingStones.forEach {
            val firstBreakStones = getStonesAfterIterations(it, breaks[0])
            for (firstBreakStone in firstBreakStones) {
                if (firstMemoizationMap.containsKey(firstBreakStone)) {
                    total += firstMemoizationMap.getValue(firstBreakStone)
                    continue
                }
                totalFromFirstBreak = 0L
                val secondBreakStones = getStonesAfterIterations(firstBreakStone, breaks[1])
                for (secondBreakStone in secondBreakStones) {
                    if (secondMemoizationMap.containsKey(secondBreakStone)) {
                        total += secondMemoizationMap.getValue(secondBreakStone)
                        totalFromFirstBreak += secondMemoizationMap.getValue(secondBreakStone)
                        continue
                    }
                    val finalBreakStones = getStonesAfterIterations(secondBreakStone, breaks[2])
                    if (secondMemoizationMap.containsKey(secondBreakStone)) throw Error("Reassignment is a bug")
                    secondMemoizationMap[secondBreakStone] = finalBreakStones.size.toLong()
                    total += finalBreakStones.size
                    totalFromFirstBreak += finalBreakStones.size
                }
                if (firstMemoizationMap[firstBreakStone] != null) throw Error("Reassignment is a bug")
                firstMemoizationMap[firstBreakStone] = totalFromFirstBreak
            }
        }

        println("Stone number for ${breaks.sum()} iterations is $total")
        return total
    }

    private fun getStonesAfterIterations(startingStone: Long, iterations: Int): List<Long> =
        (0 until iterations).fold(listOf(startingStone)) { stones, _ -> calculateStones(stones) }

    private fun calculateStones(stones: List<Long>): List<Long> {
        return stones.flatMap {
            when {
                it == 0L -> listOf(1)
                it.toString().length % 2 == 0 -> {
                    val split = it.toString()
                    listOf(
                        split.substring(0 until split.length / 2).toLong(),
                        split.substring(split.length / 2).toLong()
                    )
                }

                else -> listOf(it * 2024L)
            }
        }
    }
}