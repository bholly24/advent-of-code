package day12

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CaveMapperPartTwoTest {

    @Test
    fun getBasicPaths() {
        val caveMapperPartTwo = CaveMapperPartTwo("src/test/kotlin/day12/basic-input.txt")
        assertEquals(36, caveMapperPartTwo.getTotalPaths())
    }

    @Test
    fun getMiddlePaths() {
        val caveMapperPartTwo = CaveMapperPartTwo("src/test/kotlin/day12/middling-input.txt")
        assertEquals(103, caveMapperPartTwo.getTotalPaths())
    }

    @Test
    fun getLargePaths() {
        val caveMapperPartTwo = CaveMapperPartTwo("src/test/kotlin/day12/large-input.txt")
        assertEquals(3509, caveMapperPartTwo.getTotalPaths())
    }
}