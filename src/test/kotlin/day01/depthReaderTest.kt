import day01.DepthReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DepthReaderTest {

    @Test
    fun countDepthIncreases() {
        val reader = DepthReader()
        assertEquals(
            reader.countDepthIncreases(listOf(9, 10)),
            1
        )
    }

    @Test
    fun depthReadingsAreEqual() {
        val reader = DepthReader()
        assertEquals(
            reader.countDepthIncreases(listOf(9, 9)),
            0
        )
    }

    @Test
    fun depthReadingsDontIncrease() {
        val reader = DepthReader()
        assertEquals(
            reader.countDepthIncreases(listOf(9, 8)),
            0
        )
    }

    @Test
    fun depthReadingsInList() {
        val reader = DepthReader()
        assertEquals(
            reader.countDepthIncreases(listOf(9, 9, 10)),
            1
        )
    }

    @Test
    fun rollingDepthReadings() {
        val reader = DepthReader()
        assertEquals(
            reader.countRollingDepthIncreases(listOf(9, 9, 10, 13)),
            1
        )
    }
}