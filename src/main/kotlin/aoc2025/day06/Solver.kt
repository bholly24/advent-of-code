package aoc2025.day06

import java.io.File

class Solver(filePath: String) {
    private val lines: List<String> = File(filePath).readLines()
    private val linesWithNumbers = lines.take(lines.lastIndex)
    private val operators = lines.last().split(" ").toList().filter { !it.isEmpty() }.map { it[0] }

    fun partA(): Long = parsePartA().sumOf(Equation::solve)
    fun partB(): Long = parsePartB(lines).sumOf(Equation::solve)

    private fun parsePartA(): List<Equation> {
        val values = linesWithNumbers
            .map { it.split(" ").filter { !it.isEmpty() }.map(String::toLong) }
        return operators.mapIndexed { index, op -> Equation(values.map { it[index] }, op) }
    }

    private fun parsePartB(lines: List<String>): List<Equation> {
        // Build indexed map of our equation numbers
        val nums = mutableMapOf<Int, String>()
        for (line in linesWithNumbers) {
            for (i in line.indices) {
                val char = line[i]
                if (char == ' ') continue
                nums[i] = if (nums.containsKey(i)) nums[i] + char else char.toString()
            }
        }

        // Determine length of each equation to be able to split them out of my 1D hashmap
        val paddedOperators = lines.last().padEnd(lines.maxOf { it.length })
        data class EquationCounterState(val eqLengths: List<Int> = listOf(), val numberChars: Int = 0)

        val equationLengths: List<Int> = paddedOperators
            .foldIndexed(EquationCounterState()) { index, acc, ch ->
                when {
                    index == 0 -> EquationCounterState() // Ignore first operator
                    index == paddedOperators.lastIndex -> EquationCounterState(acc.eqLengths + (acc.numberChars + 2)) // Account for last line
                    ch == '+' || ch == '*' -> EquationCounterState(acc.eqLengths + acc.numberChars) // Add equation length
                    else -> EquationCounterState(acc.eqLengths, acc.numberChars + 1) // Increment eq length on empty space
                }
            }.eqLengths

        // Map equation lengths to split hashmap and return equations
        val equations = nums.toSortedMap().values.toList()
        var totalIndex = 0
        return equationLengths.mapIndexed { index, l ->
            val slice = equations.slice(totalIndex until totalIndex + l)
            totalIndex += l
            val numbers = slice.map(String::toLong)
            Equation(numbers, operators[index])
        }
    }
}

data class Equation(val numbers: List<Long>, val operator: Char) {
    fun solve(): Long = if (operator == '*') numbers.reduce { a, b -> a * b } else numbers.sum()
}
