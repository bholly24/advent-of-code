package aoc2024.day13

import aoc2024.fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayThirteenTest {

    @Test
    fun partA() {
        val dayThirteen = DayThirteen(FileHelper.testFileForDay(13), 11, 7)
        assertEquals(12, dayThirteen.partA())
    }
}