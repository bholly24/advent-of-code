package aoc2024.day12

import aoc2024.fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayTwelveTest {

    @Test
    fun partA() {
        val dayTwelve = DayTwelve(FileHelper.testFileForDay(12))
        assertEquals(480, dayTwelve.partA())
    }

    @Test
    fun partASolve() {
        val dayTwelve = DayTwelve(FileHelper.testFileForDay(12))
        assertEquals(480, dayTwelve.partASolve())
    }

    @Test
    fun partB() {
        val dayTwelve = DayTwelve(FileHelper.testFileForDay(12))
        assertEquals(480, dayTwelve.partB())
    }
}