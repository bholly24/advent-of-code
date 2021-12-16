package day03

import java.io.File
import java.io.InputStream
import java.util.*
import kotlin.math.pow

class BinaryDecoder {
    fun readInBinaryNumbers(filePath: String): List<String> {
        val inputStream: InputStream = File(filePath).inputStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().forEachLine { lineList.add(it) }
        return Collections.unmodifiableList(lineList)
    }

    fun getPowerConsumption(binaryReadings: List<String>): Int {
        return getEpsilonRate(binaryReadings) * getGammaRate(binaryReadings)
    }

    fun getLifeSupportRating(binaryReadings: List<String>): Int {
        return getOxygenRating(binaryReadings) * getCO2Rating(binaryReadings)
    }

    fun getGammaRate(binaryReadings: List<String>): Int {
        var binaryNumber = ""
        getGlobalModalBinNumber(binaryReadings).forEach { if (it > 0) binaryNumber += "1" else binaryNumber += "0" }
        return convertBinaryNumber(binaryNumber)
    }

    fun getEpsilonRate(binaryReadings: List<String>): Int {
        var binaryNumber = ""
        getGlobalModalBinNumber(binaryReadings).forEach { if (it > 0) binaryNumber += "0" else binaryNumber += "1" }
        return convertBinaryNumber(binaryNumber)
    }

    fun getOxygenRating(binaryReadings: List<String>): Int {
        return convertBinaryNumber(getRunningModalBinNumber(binaryReadings, true))
    }

    fun getCO2Rating(binaryReadings: List<String>): Int {
        return convertBinaryNumber(getRunningModalBinNumber(binaryReadings, false))
    }

    private fun getRunningModalBinNumber(binaryReadings: List<String>, keepGreater: Boolean): String {
        val characterLength = binaryReadings[0].toCharArray().size
        val countingArray = Array(characterLength) { 0 }
        var currentIndex = 0
        val runningList = binaryReadings.toMutableList()

        while (currentIndex < characterLength) {
            runningList.forEach {
                if (it.toCharArray()[currentIndex] == '0') {
                    countingArray[currentIndex] -= 1
                } else {
                    countingArray[currentIndex] += 1
                }
            }

            val numberToKeep = if (countingArray[currentIndex] >= 0) {
                if (keepGreater) '1' else '0'
            } else {
                if (keepGreater) '0' else '1'
            }

            runningList.removeIf { it.toCharArray()[currentIndex] != numberToKeep }

            if (runningList.size == 1) {
                return runningList[0]
            }
            currentIndex += 1
        }

        return runningList[0]
    }

    private fun getGlobalModalBinNumber(binaryReadings: List<String>): Array<Int> {
        val characterLength = binaryReadings[0].toCharArray().size
        val countingArray = Array(characterLength) { 0 }
        binaryReadings.forEach {
            it.toCharArray().forEachIndexed { index, char ->
                if (char == '0') {
                    countingArray[index] -= 1
                } else {
                    countingArray[index] += 1
                }
            }
        }
        return countingArray
    }

    private fun convertBinaryNumber(binaryNumber: String): Int {
        var baseTen: Int = 0
        binaryNumber.reversed().forEachIndexed { index, char ->
            if (char == '1') {
                baseTen += 2.toDouble().pow(index).toInt()
            }
        }
        return baseTen
    }
}