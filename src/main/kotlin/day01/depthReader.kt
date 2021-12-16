package day01

import java.io.File
import java.io.InputStream
import java.util.*

class DepthReader {
    fun readInFile(filePath: String): List<Int> {
        val inputStream: InputStream = File(filePath).inputStream()
        val lineList = mutableListOf<Int>()

        inputStream.bufferedReader().forEachLine { lineList.add(it.toInt()) }
        return Collections.unmodifiableList(lineList)
    }

    fun countDepthIncreases(depthReadings: List<Int>): Int {
        var count = 0
        for (i in 0 until depthReadings.count() - 1) {
            if (depthReadings[i + 1] > depthReadings[i]) count += 1
        }
        return count
    }

    fun countRollingDepthIncreases(depthReadings: List<Int>): Int {
        var count = 0
        for (i in 0 until depthReadings.count() - 3) {
            if (depthReadings[i + 1] + depthReadings[i + 2] + depthReadings[i + 3] > depthReadings[i] + depthReadings[i + 1] + depthReadings[i + 2] ) count += 1
        }
        return count
    }
}