package aoc2025.day02

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DayTwoTest {
    private lateinit var dayTwo: DayTwo

    @BeforeEach
    fun setUp() {
        dayTwo = DayTwo(FileHelper.testFileForDay(2))
    }

    @Test
    fun partA() {
        logAndAssertEquals(357L, dayTwo::partA)
    }


    @Test
    fun puzzleA() {
        dayTwo = DayTwo(FileHelper.puzzleFileForDay(2))
        logAndAssertEquals(17144L, dayTwo::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(3121910778619L, dayTwo::partB)
    }

    @Test
    fun puzzleB() {
        dayTwo = DayTwo(FileHelper.puzzleFileForDay(2))
        logAndAssertEquals(170371185255900L, dayTwo::partB)
    }
}