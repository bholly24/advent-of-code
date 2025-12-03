package aoc2025.day01

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class DayOneTest {

    lateinit var dayOne: DayOne

    @BeforeEach
    fun setup() {
        dayOne = DayOne("src/test/kotlin/aoc2025/day01/input.txt")
    }

    @Test
    fun testPartOne() {
        assertEquals(1227775554, dayOne.partA())
    }

    @Test
    fun partAPuzzle() {
        dayOne = DayOne("src/test/kotlin/aoc2025/day01/puzzle.txt")
        assertEquals(44487518055, dayOne.partA())
    }

    @Test
    fun testPartB() {
        assertEquals(4174379265, dayOne.partB())
    }

    @Test
    fun partBPuzzle() {
        dayOne = DayOne("src/test/kotlin/aoc2025/day01/puzzle.txt")
        assertEquals(53481866137, dayOne.partB())
    }

    @Test
    fun findsDuplicateSequence() {
        assertTrue(dayOne.containsDuplicateSequence("111111"))
        assertTrue(dayOne.containsDuplicateSequence("12341234"))
        assertFalse(dayOne.containsDuplicateSequence("1"))
        assertFalse(dayOne.containsDuplicateSequence("112"))
        assertFalse(dayOne.containsDuplicateSequence("111"))
    }

    @Test
    fun findsSubsequence() {
        assertTrue(dayOne.containsSubsequence("111111"))
        assertTrue(dayOne.containsSubsequence("12341234"))
        assertTrue(dayOne.containsSubsequence("123123123"))
        assertFalse(dayOne.containsSubsequence("1"))
        assertFalse(dayOne.containsSubsequence("112"))
        assertTrue(dayOne.containsSubsequence("111"))
    }
}