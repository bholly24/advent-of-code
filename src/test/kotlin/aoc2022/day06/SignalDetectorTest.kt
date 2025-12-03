package aoc2022.day06

import aoc2022.FileHelper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SignalDetectorTest {
    private val signalDetector = SignalDetector(FileHelper.testFileForDay(6))

    @Test
    fun detectUniqueSequenceOfFour() {
        assertEquals(7, signalDetector.detectUniqueSequenceOfFour())
    }

    @Test
    fun detectUniqueSequenceOfNineteen() {
        assertEquals(19, signalDetector.detectUniqueSequenceOfFourteen())
    }
}