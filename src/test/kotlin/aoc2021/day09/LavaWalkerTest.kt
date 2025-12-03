package aoc2021.day09

import aoc2021.day09.LavaWalker
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LavaWalkerTest {

    @Test
    fun findLowestPoints() {
        val lavaWalker = LavaWalker("src/test/kotlin/aoc2021/day09/input.txt")

        assertEquals(15, lavaWalker.findTotalRisk())
    }

    @Test
    fun findBasinSizes() {
        val lavaWalker = LavaWalker("src/test/kotlin/aoc2021/day09/input.txt")
        assertEquals(1134, lavaWalker.findBasinSizes())
    }
}