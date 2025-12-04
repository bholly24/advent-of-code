package aoc2025.day02

import aoc2025.day04.DayFour
import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DayFourTest {
    private lateinit var dayFour: DayFour

    @BeforeEach
    fun setUp() {
        dayFour = DayFour(FileHelper.testFileForDay(4))
    }

    @Test
    fun partA() {
        logAndAssertEquals(13, dayFour::partA)
    }

    @Test
    fun puzzleA() {
        dayFour = DayFour(FileHelper.puzzleFileForDay(4))
        logAndAssertEquals(1435, dayFour::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(43, dayFour::partB)
    }

    @Test
    fun puzzleB() {
        dayFour = DayFour(FileHelper.puzzleFileForDay(4))
        logAndAssertEquals(8623, dayFour::partB)
    }
}