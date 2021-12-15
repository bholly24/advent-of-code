package day12

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CaveMapperTest {

    @Test
    fun getBasicPaths() {
        val caveMapper = SimpleCaveMapper("src/test/kotlin/day12/basic-input.txt")
        assertEquals(10, caveMapper.getTotalPaths())
    }

    @Test
    fun getMiddlePaths() {
        val caveMapper = SimpleCaveMapper("src/test/kotlin/day12/middling-input.txt")
        assertEquals(19, caveMapper.getTotalPaths())
    }

    @Test
    fun getLargePaths() {
        val caveMapper = SimpleCaveMapper("src/test/kotlin/day12/large-input.txt")
        assertEquals(226, caveMapper.getTotalPaths())
    }
}