package day11

import java.io.File
import java.io.InputStream

class OctopusTracker(filePath: String) {
    private var octopusMatrix = readInLines(filePath)
    private val xMax: Int = octopusMatrix.size
    private val yMax: Int = octopusMatrix[0].size

    private fun readInLines(filePath: String): MutableList<MutableList<Int>> {
        val inputStream: InputStream = File(filePath).inputStream()
        val lineList = mutableListOf<MutableList<Int>>()

        inputStream.bufferedReader().forEachLine { line ->
            lineList.add(line.map { it.toString().toInt() }.toMutableList())
        }

        return lineList
    }

    fun predictFlashes(steps: Int): Int {
        var totalFlashes = 0
        for (step in 0 until steps) {
            totalFlashes += determineFlashing()
        }

        println("Total flashes after $steps steps: $totalFlashes")
        return totalFlashes
    }

    fun findSynchronizedFlashStep(): Int {
        var steps = 0
        var stepFlashes = 0
        while (stepFlashes < 100) {
            steps += 1
            stepFlashes = determineFlashing()
        }

        println("First step when all octopuses flash: $steps")
        return steps
    }

    private fun determineFlashing(): Int {
        octopusMatrix = octopusMatrix.map { row -> row.map { it + 1 }.toMutableList() }.toMutableList()
        var noMoreFlashes = false
        var flashes = 0
        while (!noMoreFlashes) {
            var hasFlash = false
            for (x in 0 until xMax) {
                for (y in 0 until yMax) {
                    val octopusLevel = octopusMatrix[x][y]
                    if (octopusLevel > 9) {
                        octopusMatrix[x][y] = 0
                        flashes += 1
                        // Maximum number of flashes
                        if (flashes == 100) {
                            return flashes
                        }
                        hasFlash = true
                        val coords =  coordsToIncrement(x, y)
                        for (coord in coords) {
                            val octopusValue = octopusMatrix[coord[0]][coord[1]]
                            if (octopusValue > 0) {
                                octopusMatrix[coord[0]][coord[1]] = octopusValue + 1
                            }
                        }
                    }
                }
            }
            noMoreFlashes = !hasFlash
            }

            return flashes
    }

    private fun coordsToIncrement(x: Int, y: Int): List<Array<Int>> {
        val coords = mutableListOf(
            arrayOf(x - 1, y - 1),
            arrayOf(x - 1, y),
            arrayOf(x - 1, y + 1),
            arrayOf(x, y - 1),
            arrayOf(x, y + 1),
            arrayOf(x + 1, y - 1),
            arrayOf(x + 1, y),
            arrayOf(x + 1, y + 1),
        )

        return coords.filter { it[0] in 0 until xMax && it[1] in 0 until yMax }
    }
}