package aoc2024.day10

import aoc2024.fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayTenTest {

    @Test
    fun partA() {
        val dayTen = DayTen(FileHelper.testFileForDay(10))
        assertEquals(55312, dayTen.partA())
    }
}