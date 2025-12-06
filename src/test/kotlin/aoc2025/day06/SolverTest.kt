package aoc2025.day06

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(6))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay(6))
    }

    @Test
    fun partA() {
        logAndAssertEquals(4277556, exampleSolver::partA)
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(5733696195703, puzzleSolver::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(3263827, exampleSolver::partB)
    }

    @Test
    fun puzzleB() {
        logAndAssertEquals(10951882745757, puzzleSolver::partB)
    }
}

