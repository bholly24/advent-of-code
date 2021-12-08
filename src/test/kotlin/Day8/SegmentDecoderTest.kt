package Day8

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SegmentDecoderTest {

    @Test
    fun countDecipherableSegments() {
        val decoder = SegmentDecoder("src/test/kotlin/Day8/test-input.txt")
        val result = decoder.countDecipherableSegments()
        assertEquals(26, result)
    }

    @Test
    fun decodeAndSumSegments() {
        val decoder = SegmentDecoder("src/test/kotlin/Day8/test-input.txt")
        val result = decoder.decodeAndAddSegments()
        assertEquals(61229, result)
    }
}
