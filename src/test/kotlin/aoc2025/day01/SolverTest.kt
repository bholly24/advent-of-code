package aoc2025.day01

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(1))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay(1))
    }

    @Test
    fun partA() {
        logAndAssertEquals(3, exampleSolver::partA)
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(1066, puzzleSolver::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(6, exampleSolver::partB)
    }

    @Test
    fun puzzleB() {
        logAndAssertEquals(6223, puzzleSolver::partB)
    }

    fun assertState(position: Direction = Direction.Left, startPosition: Int = 50, endPosition: Int = 50, turns: Int = 0, times: Int = 0) {
        val r = Solver.PartB.next(Instruction(position, turns), Result(startPosition, 0))
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

