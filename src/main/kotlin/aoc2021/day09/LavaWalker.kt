package aoc2021.day09

import java.io.File
import java.io.InputStream

class LavaWalker(filePath: String) {
    private val lava = readInLines(filePath)
    private val xMax: Int = lava.size
    private val yMax: Int = lava[0].size
    private val lowPoints = mutableListOf<LavaPoint>()

    private fun readInLines(filePath: String): List<List<Int>> {
        val inputStream: InputStream = File(filePath).inputStream()
        val lineList = mutableListOf<List<Int>>()

        inputStream.bufferedReader().forEachLine { line ->
            lineList.add(line.map { it.toString().toInt() })
        }

        return lineList
    }

    fun findTotalRisk(): Int {
        val lowPoints = findLowPoints()
        val totalRisk = lowPoints.sumOf { it.height } + lowPoints.size
        println("Low point risk: $totalRisk")

        return totalRisk
    }

    // Getting distinct() for the data class LavaPoint is a hacky way to avoid recursion potentially adding points twice
    fun findBasinSizes(): Int {
        val lowPoints = findLowPoints()
        val basinSizes = mutableListOf<List<LavaPoint>>()

        lowPoints.forEach {
            val basin = mutableListOf<LavaPoint>()
            basin.add(it)
            addToBasin(it, basin)
            basinSizes.add(basin.distinct())
        }
        val sortedBasins = basinSizes.distinct().map { it.size }.sorted().reversed()
        val basinMultiplication = sortedBasins[0] * sortedBasins[1] * sortedBasins[2]
        println("Basin size multiplication: $basinMultiplication")
        return basinMultiplication
    }

    private fun findLowPoints(): List<LavaPoint> {
        for (x in 0 until xMax) {
            for (y in 0 until yMax) {
                val currentValue = lava[x][y]
                val comparisons = coordsToTest(x, y).map { lava[it[0]][it[1]]}
                if (comparisons.all { lava[x][y] < it }) {
                    lowPoints.add(LavaPoint(currentValue, x, y))
                }
            }
        }

        return lowPoints
    }

    private fun addToBasin(point: LavaPoint, currentBasin: MutableList<LavaPoint>) {
        val currentValue = lava[point.x][point.y]
        val coordsToTest = coordsToTest(point.x, point.y)

        for (coord in coordsToTest) {
            val checkValue = lava[coord[0]][coord[1]]
            if (isLowestValueEligibleForBasin(currentValue, coord[0], coord[1])) {
                val lavaPoint = LavaPoint(checkValue, coord[0], coord[1])
                currentBasin.add(lavaPoint)
                addToBasin(lavaPoint, currentBasin)
            }
        }
    }

    private fun coordsToTest(x: Int, y: Int): List<Array<Int>> {
        val coords = mutableListOf(
            arrayOf(x - 1, y),
            arrayOf(x + 1, y),
            arrayOf(x, y - 1),
            arrayOf(x, y + 1)
        )

        return coords.filter { it[0] in 0 until xMax && it[1] in 0 until yMax }
    }

    private fun isLowestValueEligibleForBasin(valueToCheck: Int, x: Int, y: Int): Boolean {
        val currentValue = lava[x][y]

        if (valueToCheck >= currentValue || currentValue >= 9) {
            return false
        }

        coordsToTest(x, y).forEach { if (lava[it[0]][it[1]] < valueToCheck) return false }

        return true
    }
}

data class LavaPoint(val height: Int, val x: Int, val y: Int)
