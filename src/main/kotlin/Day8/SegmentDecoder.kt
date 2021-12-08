package Day8

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
            lineList.add(it.split("|")[delimiterIndexToKeep].trim().split(" "))
        }

        return lineList
    }

    fun countDecipherableSegments(): Int {
        var decipherableSegments = 0
        outputLines.forEach { it.forEach { segment -> if (segmentIsDecipherable(segment)) decipherableSegments += 1 } }

        println("Total decipherable segments: $decipherableSegments")
        return decipherableSegments
    }

    fun decodeLine(inputLine: List<String>, outputLine: List<String>): Int {
        val lineMap = mutableMapOf<String, Int>()

        inputLine.forEach {
            when (it.length) {
                2 -> lineMap[it] = 1
                3 -> lineMap[it] = 7
                4 -> lineMap[it] = 4
                7 -> lineMap[it] = 8
            }
        }

        val digitMap = mutableMapOf<Char, SegmentPositions>()

        if (numbersOccur(lineMap, 1, 7)) {
            digitMap[fillInSegmentPositions(lineMap, 1, 7)] = SegmentPositions.TOP
        }

        var total = 0
        outputLine.forEach { it.forEach { segment -> total += lineMap.getOrDefault(it, 0) } }

        return total
    }

    fun decodeAndAddSegments(): Int {
        var total = 0
        for (index in inputLines.indices) {
            total += decodeLine(inputLines[index], outputLines[index])
        }
        return total
    }

    private fun segmentIsDecipherable(segment: String): Boolean {
        return arrayOf(2, 3, 4, 7).contains(segment.length)
    }

    private fun findCharDifference(segment: String, segmentTwo: String): Char {
        val biggerSegment = if (segment.length > segmentTwo.length) segment else segmentTwo
        val smallerSegment = if (segment.length < segmentTwo.length) segment else segmentTwo

        for (i in biggerSegment.indices) {
            if (!smallerSegment.contains(biggerSegment[i])) return biggerSegment[i]
        }
        return ' '
    }

    private fun fillInSegmentPositions(knownMap: Map<String, Int>, numberOne: Int, numberTwo: Int): Char {
        val knownNumbers = knownMap.entries.associate { (key, value) -> value to key }
        return findCharDifference(knownNumbers.getOrDefault(numberOne, ""), knownNumbers.getOrDefault(numberTwo, ""))
    }

    private fun numbersOccur(knownMap: Map<String, Int>, numberOne: Int, numberTwo: Int): Boolean {
        val knownNumbers = knownMap.values.toList()
        return knownNumbers.contains(numberOne) && knownNumbers.contains(numberTwo)
    }
}

enum class SegmentPositions {
    TOP, BOTTOM, TOP_RIGHT, TOP_LEFT, MIDDLE, BOTTOM_RIGHT, BOTTOM_LEFT,
}

abstract class SegmentNumber {
    abstract val segments: Array<SegmentPositions>
    val numberSegments: Int = segments.size
}

class One : SegmentNumber() {
    override val segments = arrayOf(
        SegmentPositions.BOTTOM_RIGHT,
        SegmentPositions.TOP_RIGHT
    )
}

class Two : SegmentNumber() {
    override val segments = arrayOf(
        SegmentPositions.TOP,
        SegmentPositions.TOP_RIGHT,
        SegmentPositions.MIDDLE,
        SegmentPositions.BOTTOM_LEFT,
        SegmentPositions.BOTTOM
    )
}

class Three : SegmentNumber() {
    override val segments = arrayOf(
        SegmentPositions.BOTTOM_RIGHT,
        SegmentPositions.TOP_RIGHT,
        SegmentPositions.MIDDLE,
        SegmentPositions.BOTTOM
    )
}

class Four : SegmentNumber() {
    override val segments = arrayOf(
        SegmentPositions.BOTTOM_RIGHT,
        SegmentPositions.TOP_RIGHT,
        SegmentPositions.TOP_LEFT,
        SegmentPositions.MIDDLE
    )
}

class Five : SegmentNumber() {
    override val segments = arrayOf(
        SegmentPositions.TOP,
        SegmentPositions.TOP_LEFT,
        SegmentPositions.MIDDLE,
        SegmentPositions.BOTTOM_RIGHT,
        SegmentPositions.BOTTOM
    )
}

class Six : SegmentNumber() {
    override val segments = arrayOf(
        SegmentPositions.TOP,
        SegmentPositions.TOP_RIGHT,
        SegmentPositions.MIDDLE,
        SegmentPositions.BOTTOM_RIGHT,
        SegmentPositions.BOTTOM,
        SegmentPositions.BOTTOM_LEFT
    )
}

class Seven : SegmentNumber() {
    override val segments = arrayOf(
        SegmentPositions.TOP,
        SegmentPositions.TOP_RIGHT,
        SegmentPositions.BOTTOM_RIGHT
    )
}

class Eight : SegmentNumber() {
    override val segments = arrayOf(
        SegmentPositions.TOP,
        SegmentPositions.TOP_LEFT,
        SegmentPositions.TOP_RIGHT,
        SegmentPositions.MIDDLE,
        SegmentPositions.BOTTOM_RIGHT,
        SegmentPositions.BOTTOM,
        SegmentPositions.BOTTOM_LEFT
    )
}


class Nine : SegmentNumber() {
    override val segments = arrayOf(
        SegmentPositions.TOP,
        SegmentPositions.TOP_LEFT,
        SegmentPositions.TOP_RIGHT,
        SegmentPositions.MIDDLE,
        SegmentPositions.BOTTOM_RIGHT
    )
}