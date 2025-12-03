package aoc2025.day02

import aoc2025.utils.FileHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayTwoTest {
    private lateinit var dayTwo: DayTwo

    @BeforeEach
    fun setUp() {
        dayTwo = DayTwo(FileHelper.testFileForDay(2))
    }

    @Test
    fun partA() {
        assertEquals(0, dayTwo.partA())
    }


    @Test
    fun puzzleA() {
        dayTwo = DayTwo(FileHelper.puzzleFileForDay(2))
        assertEquals(0, dayTwo.partA())
    }

    @Test
    fun partB() {
        assertEquals(0, dayTwo.partB())
    }

    @Test
    fun puzzleB() {
        dayTwo = DayTwo(FileHelper.puzzleFileForDay(2))
        assertEquals(0, dayTwo.partB())
    }
}