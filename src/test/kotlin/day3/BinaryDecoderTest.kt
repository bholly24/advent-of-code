package day3

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BinaryDecoderTest {
    private val decoder: BinaryDecoder = BinaryDecoder()
    private val simpleTestingList = listOf("10001", "10011", "10001", "00000")
    private val exampleTestingList = listOf(
        "00100", "11110", "10110", "10111", "10101", "01111", "00111",
        "11100", "10000", "11001", "00010", "01010"
    )

    @Test
    fun getGammaRate() {
        assertEquals(17, decoder.getGammaRate(simpleTestingList))
    }

    @Test
    fun getEpsilonRate() {
        assertEquals(14, decoder.getEpsilonRate(simpleTestingList))
    }

    @Test
    fun getPowerConsumption() {
        assertEquals(238, decoder.getPowerConsumption(simpleTestingList))
    }

    @Test
    fun getOxygenRate() {
        assertEquals(17, decoder.getOxygenRating(simpleTestingList))
        assertEquals(23, decoder.getOxygenRating(exampleTestingList))
    }

    @Test
    fun getCO2Rate() {
        assertEquals(0, decoder.getCO2Rating(simpleTestingList))
        assertEquals(10, decoder.getCO2Rating(exampleTestingList))
    }

    @Test
    fun getLifeSupportRating() {
        assertEquals(0, decoder.getLifeSupportRating(simpleTestingList))
        assertEquals(230, decoder.getLifeSupportRating(exampleTestingList))
    }
}