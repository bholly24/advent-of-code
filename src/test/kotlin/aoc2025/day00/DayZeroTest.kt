package aoc2025.day00

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class DayZeroTest {
    private lateinit var dayZero: DayZero;

    @BeforeEach
    fun setup() {
        dayZero = DayZero("src/test/kotlin/aoc2025/day00/input.txt")
    }

    @Test
    fun partA() {
        assertEquals(3, dayZero.partA())
    }

    @Test
    fun puzzleA() {
        dayZero = DayZero("src/test/kotlin/aoc2025/day00/puzzle.txt")
        assertEquals(1066, dayZero.partA())
    }

    @Test
    fun partB() {
        assertEquals(6, dayZero.partB())
    }

    @Test
    fun puzzleB() {
        dayZero = DayZero("src/test/kotlin/aoc2025/day00/puzzle.txt")
        assertEquals(6223, dayZero.partB())
    }

    fun assertState(position: Direction = Direction.Left, startPosition: Int = 50, endPosition: Int = 50, turns: Int = 0, times: Int = 0) {
        val r = DayZero.PartB.next(Instruction(position, turns), Result(startPosition, 0))
        assertEquals(times, r.timesToIncrement)
        assertEquals(endPosition, r.position)
    }

    @Test
    fun bLeft() {
        assertState(Direction.Left, 1, 99, 2, 1)
        assertState(Direction.Left, 1, 3, 1098, 11)
        assertState(Direction.Left, 50, 1, 49, 0)
        assertState(Direction.Left, 50, 0, 150, 2)
        assertState(Direction.Left, 50, 0, 1050, 11)
        assertState(Direction.Left, 50, 99, 151, 2)
    }

    @Test
    fun bRight() {
        assertState(Direction.Right, 0, 99, 99, 0)
        assertState(Direction.Right, 50, 99, 49, 0)
        assertState(Direction.Right, 50, 0, 50, 1)
        assertState(Direction.Right, 50, 0, 150, 2)
        assertState(Direction.Right, 50, 0, 1050, 11)
        assertState(Direction.Right, 50, 1, 151, 2)
    }
}