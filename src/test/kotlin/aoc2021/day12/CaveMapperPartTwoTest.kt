package aoc2021.day12

import aoc2021.day12.CaveMapperPartTwo
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CaveMapperPartTwoTest {

    @Test
    fun getBasicPaths() {
        val caveMapperPartTwo = CaveMapperPartTwo("src/test/kotlin/aoc2021/day12/basic-input.txt")
        assertEquals(36, caveMapperPartTwo.getTotalPaths())
    }

    @Test
    fun getMiddlePaths() {
        val caveMapperPartTwo = CaveMapperPartTwo("src/test/kotlin/aoc2021/day12/middling-input.txt")
        assertEquals(103, caveMapperPartTwo.getTotalPaths())
    }

    @Test
    fun getLargePaths() {
        val caveMapperPartTwo = CaveMapperPartTwo("src/test/kotlin/aoc2021/day12/large-input.txt")
        assertEquals(3509, caveMapperPartTwo.getTotalPaths())
    }
}