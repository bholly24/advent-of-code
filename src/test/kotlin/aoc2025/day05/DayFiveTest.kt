package aoc2025.day05

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DayFiveTest {
    private lateinit var dayFive: DayFive

    @BeforeEach
    fun setUp() {
        dayFive = DayFive(FileHelper.testFileForDay(5))
    }

    @Test
    fun partA() {
        logAndAssertEquals(3, dayFive::partA)
    }

    @Test
    fun puzzleA() {
        dayFive = DayFive(FileHelper.puzzleFileForDay(5))
        logAndAssertEquals(520, dayFive::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(14, dayFive::partB)
    }

    @Test
    fun puzzleB() {
        dayFive = DayFive(FileHelper.puzzleFileForDay(5))
        logAndAssertEquals(347338785050515, dayFive::partB)
    }

    @Test
    fun partBTest() {
        dayFive = DayFive(FileHelper.getAdditionalTestFile(5, "test"))
        logAndAssertEquals(49714559749632, dayFive::partB)
    }
}