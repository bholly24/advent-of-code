package aoc2024.day11

import aoc2024.fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayElevenTest {

    @Test
    fun partA() {
        val dayEleven = DayEleven(FileHelper.testFileForDay(11))
        assertEquals(1930, dayEleven.partA())
    }

    @Test
    fun partB() {
        val dayEleven = DayEleven(FileHelper.testFileForDay(11))
        assertEquals(1206, dayEleven.partB())
    }

    @Test
    fun partBEMap() {
        val dayEleven = DayEleven(FileHelper.getAdditionalTestFile(11, "input-e"))
        assertEquals(236, dayEleven.partB())
    }
}