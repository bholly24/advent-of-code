package aoc2021.day12

import aoc2021.day12.SimpleCaveMapper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CaveMapperTest {

    @Test
    fun getBasicPaths() {
        val caveMapper = SimpleCaveMapper("src/test/kotlin/aoc2021/day12/basic-input.txt")
        assertEquals(10, caveMapper.getTotalPaths())
    }

    @Test
    fun getMiddlePaths() {
        val caveMapper = SimpleCaveMapper("src/test/kotlin/aoc2021/day12/middling-input.txt")
        assertEquals(19, caveMapper.getTotalPaths())
    }

    @Test
    fun getLargePaths() {
        val caveMapper = SimpleCaveMapper("src/test/kotlin/aoc2021/day12/large-input.txt")
        assertEquals(226, caveMapper.getTotalPaths())
    }
}