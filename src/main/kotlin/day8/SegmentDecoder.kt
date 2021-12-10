package day8

import java.io.File
import java.io.InputStream

class SegmentDecoder(private val filePath: String) {
    private var inputLines: List<List<String>>
    private var outputLines: List<List<String>>

    init {
        inputLines = readInLines(0)
        outputLines = readInLines(1)
    }

    fun readInLines(delimiterIndexToKeep: Int): List<List<String>> {
        val inputStream: InputStream = File(filePath).inputStream()
        val lineList = mutableListOf<List<String>>()

        inputStream.bufferedReader().forEachLine {
            lineList.add(
                it.split("|")[delimiterIndexToKeep].trim().split(" ")
                    .map { it.toCharArray().sorted().joinToString("") })
        }

        return lineList
    }

    fun countDecipherableSegments(): Int {
        var decipherableSegments = 0
        outputLines.forEach { it.forEach { segment -> if (segmentIsDecipherable(segment)) decipherableSegments += 1 } }

        println("Total decipherable segments: $decipherableSegments")
        return decipherableSegments
    }

    fun decodeAndAddSegments(): Int {
        var total = 0
        for (index in inputLines.indices) {
            total += decodeLine(inputLines[index], outputLines[index])
        }
        println("Total segment sum after decoding: $total")
        return total
    }

    private fun decodeLine(inputLine: List<String>, outputLine: List<String>): Int {
        val lineMap = mutableMapOf<String, Int>()

        inputLine.forEach {
            when (it.length) {
                2 -> lineMap[it.toCharArray().sorted().joinToString("")] = 1
                3 -> lineMap[it.toCharArray().sorted().joinToString("")] = 7
                4 -> lineMap[it.toCharArray().sorted().joinToString("")] = 4
                7 -> lineMap[it.toCharArray().sorted().joinToString("")] = 8
            }
        }

        // 6 is the only six digit number that is missing bottom right
        val oneCharArray = lineMap.entries.associate { (key, value) -> value to key }.getOrDefault(1, "").toCharArray()
        val six = inputLine.filter { it.length == 6 && (!it.contains(oneCharArray[0]) || !it.contains(oneCharArray[1])) }[0]
        lineMap[six] = 6

        // 3 is the only 5 digit number with bottom right and top right segments
        val three = inputLine.filter { it.length == 5 && it.contains(oneCharArray[0]) && it.contains(oneCharArray[1]) }[0]
        lineMap[three] = 3

        // middle segment is in nine and not zero. It is found in all 3 five digit numbers.
        // while bottom segment left is not found in two and that is 0s unique char (relative to 9))
        val zeroOrNine = inputLine.filter { it.length == 6 && !lineMap.keys.toList().contains(it) }

        // Get the unique char for each string. 9s unique char is the middle, while zero's is bottom left
        val zeroOrNineFirst = zeroOrNine[0].toCharArray().filter { !zeroOrNine[1].toCharArray().contains(it) }[0]
        val zeroOrNineSecond = zeroOrNine[1].toCharArray().filter { !zeroOrNine[0].toCharArray().contains(it) }[0]

        val fiveDigitNumbers = inputLine.filter { it.length == 5 }

        var containedInAllThree = true
        var i = 0
        while (i < 3 && containedInAllThree) {
            containedInAllThree = fiveDigitNumbers[i].contains(zeroOrNineFirst)
            i += 1
        }

        val bottomLeftSegment: Char

        if (containedInAllThree) {
            bottomLeftSegment = zeroOrNineSecond
            lineMap[zeroOrNine[0]] = 9
            lineMap[zeroOrNine[1]] = 0
        } else {
            bottomLeftSegment = zeroOrNineFirst
            lineMap[zeroOrNine[0].toCharArray().sorted().joinToString("")] = 0
            lineMap[zeroOrNine[1].toCharArray().sorted().joinToString("")] = 9
        }

        // Two is the only five digit number that contains the bottom left segment
        lineMap[fiveDigitNumbers.filter { it.contains(bottomLeftSegment) }[0]] = 2

        // Five is the only five digit number left
        val five = fiveDigitNumbers.filter { !lineMap.keys.toList().contains(it) }[0]
        lineMap[five] = 5

        var total = ""
        outputLine.forEach {
            total += lineMap.getOrDefault(it.toCharArray().sorted().joinToString(""), 0).toString()
        }

        return total.toInt()
    }

    private fun segmentIsDecipherable(segment: String): Boolean {
        return arrayOf(2, 3, 4, 7).contains(segment.length)
    }
}
