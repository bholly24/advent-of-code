package aoc2021.day06

import java.io.File
import java.io.InputStream

fun readInLines(filePath: String): Array<Int> {
    val inputStream: InputStream = File(filePath).inputStream()
    var line = listOf<Int>()
    // This kind of sadly implies only a single line working or else it will be overwritten
    inputStream.bufferedReader().forEachLine { line = it.split(",").map { it.toInt() } }
    return line.toTypedArray()
}

class Lanternfish(lanternfishInput: Array<Int>) {
    val lanternfish: MutableList<Fish> = mutableListOf()
    val newFishAge = 8
    val rebornFishAge = 6

    init {
        lanternfishInput.forEach { lanternfish.add(Fish(it)) }
    }

    fun runSimulation(days: Int) {
        for (day in 0 until days) {
            simulateLanternfishDay(day)
        }
        val fish = lanternfish.size
        println("Total fish: $fish")
    }

    private fun simulateLanternfishDay(currentDay: Int) {
        var fishToAdd = 0
        lanternfish.forEach {
            it.age -= 1
            if (it.age == -1) {
                it.age = rebornFishAge
                fishToAdd += 1
            }
        }

        for (add in 0 until fishToAdd) {
            lanternfish.add(Fish(newFishAge))
        }
    }

    private fun printOutFish() {
        lanternfish.forEach { print("${it.age}, ") }
        print('\n')
    }
}

class Fish(startingAge: Int) {
    var age = startingAge
}