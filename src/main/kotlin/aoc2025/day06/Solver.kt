package aoc2025.day06

import utils.Coord
import utils.Grid
import java.io.File

class Solver(filePath: String) {
    private val lines: List<String> = File(filePath).readLines()
    private val operators = lines.last().split(" ").filterNot(String::isEmpty).map { it.first() }

    fun partA(): Long = parsePartA().sumOf(Equation::solve)
    fun partB(): Long = parsePartB().sumOf(Equation::solve)

    private fun parsePartA(): List<Equation> {
        val values = lines.dropLast(1)
            .map { it.split(" ").filter { !it.isEmpty() }.map(String::toLong) }
        return operators.mapIndexed { index, op -> Equation(values.map { it[index] }, op) }
    }

    private fun parsePartB(): List<Equation> {
        val grid = Grid(lines.map { it.toList() })
        val opCoords = (0 until grid.items.last().size)
            .map { Pair(Coord(it, grid.yMax - 1), grid.get(it, grid.yMax - 1)) }
            .filter { it.second != ' ' }

        return opCoords.indices.fold(listOf()) { acc, i ->
            val nums = mutableListOf<Long>()
            val operatorIndex = opCoords[i].first.x
            val nextOpIndex = if (i + 1 <= opCoords.lastIndex) opCoords[i + 1].first.x else grid.xMax
            for (x in operatorIndex until nextOpIndex) {
                val numString = (0 until grid.yMax - 1).fold("") { string, y ->
                    try {
                        string + grid.get(x, y)
                    } catch (_: IndexOutOfBoundsException) {
                        // Catch exceptions due to irregular row lengths and return the accumulated string
                        string
                    }
                }
                if (numString.any { it != ' ' }) nums.add(numString.filter { it != ' ' }.toLong())
            }
            acc + Equation(nums, opCoords[i].second)
        }
    }
}

data class Equation(val numbers: List<Long>, val operator: Char) {
    fun solve(): Long = if (operator == '*') numbers.reduce { a, b -> a * b } else numbers.sum()
}
