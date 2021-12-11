package day9

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LavaWalkerTest {

    @Test
    fun findLowestPoints() {
        val lavaWalker = LavaWalker("src/test/kotlin/day9/input.txt")

        assertEquals(15, lavaWalker.findTotalRisk())
    }

    @Test
    fun findBasinSizes() {
        val lavaWalker = LavaWalker("src/test/kotlin/day9/input.txt")
        assertEquals(1134, lavaWalker.findBasinSizes())
    }
}