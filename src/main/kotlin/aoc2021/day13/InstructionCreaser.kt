package aoc2021.day13

import java.io.File
import java.io.InputStream

class InstructionCreaser(filePath: String) {
    val foldSteps = mutableListOf<String>()
    private var instructions = readInLines(filePath)
    private var yMax: Int = instructions.size
    private var xMax: Int = instructions[0].size

    private fun readInLines(filePath: String): List<List<Boolean>> {
        val inputStream: InputStream = File(filePath).inputStream()
        var blankDivider = false
        val xDigits = mutableListOf<Int>()
        val yDigits = mutableListOf<Int>()

        inputStream.bufferedReader().forEachLine { line ->
            if (line.isEmpty()) {
                blankDivider = true
            } else if (!blankDivider) {
                val digits = line.split(',').map { it.toInt() }
                xDigits.add(digits[0])
                yDigits.add(digits[1])
            } else if (blankDivider) {
                val split = line.split(" ")
                foldSteps.add(split[2])
            }
        }
        xMax = (xDigits.maxOrNull() ?: 0) + 1
        yMax = (yDigits.maxOrNull() ?: 0) + 1

        val lineList = mutableListOf<MutableList<Boolean>>()
        (0 until yMax).forEach { _ -> lineList.add(MutableList(xMax) { false })}

        (0 until xDigits.size).forEach { lineList[yDigits[it]][xDigits[it]] = true}
        return lineList
    }

    fun executeFoldSteps(): Int {
        foldSteps.forEach {
            val instruction = it.split('=')
            val value = instruction[1].toInt()
            if (instruction[0] == "x") foldVertically(value) else foldHorizontally(value)
        }

        printResult()

        // For testing
        var hashes = 0
        instructions.forEach {it.forEach {if (it) hashes += 1}}
        return hashes
    }

    fun foldVertically(yAxis: Int, print: Boolean = false): Int {
        val newList = mutableListOf<List<Boolean>>()
        val firstHalf = instructions.map{ it.slice(0 until yAxis)}
        val secondHalf = instructions.map{ it.slice(yAxis + 1 until xMax).reversed()}

        for (i in 0 until instructions.size) {
            if (i >= firstHalf.size) {
                newList.add(secondHalf[i])
            } else if (i >= secondHalf.size) {
                newList.add(firstHalf[i])
            } else {
                val combinedList = firstHalf[i].mapIndexed { index, it -> it || secondHalf[i].get(index) }
                newList.add(combinedList)
            }
        }

        xMax = yAxis
        instructions = newList

        var hashes = 0
        newList.forEach {it.forEach {if (it) hashes += 1}}
        if (print) println("Total hashes: $hashes")
        return hashes
    }

    fun foldHorizontally(xAxis: Int, print: Boolean = false): Int {
        val newList = mutableListOf<List<Boolean>>()
        val firstHalf = instructions.slice(0 until xAxis)
        val secondHalf = instructions.slice(xAxis + 1 until yMax).reversed()

        for (i in 0 until xAxis) {
            if (i >= firstHalf.size) {
                newList.add(secondHalf[i])
            } else if (i >= secondHalf.size) {
                newList.add(firstHalf[i])
            } else {
                val combinedList = firstHalf[i].mapIndexed { index, it -> it || secondHalf[i].get(index) }
                newList.add(combinedList)
            }
        }

        instructions = newList
        yMax = xAxis

        var hashes = 0
        if (print) println("Total hashes: $hashes")
        newList.forEach {it.forEach {if (it) hashes += 1}}
        return hashes
    }

    private fun printResult() {
        instructions.forEach {
            println(it.map { if (it) '#' else '.' }.joinToString(""))
        }
    }
}