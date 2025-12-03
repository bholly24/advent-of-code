package aoc2024.day14

import aoc2024.fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayFourteenTest {

    @Test
    fun partA() {
        val dayFourteen = DayFourteen(FileHelper.testFileForDay(14))
        assertEquals(2028, dayFourteen.partA())
    }
}