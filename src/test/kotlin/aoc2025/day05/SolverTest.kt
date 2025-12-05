package aoc2025.day05

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(5))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay(5))
    }

    @Test
    fun partA() {
        logAndAssertEquals(3, exampleSolver::partA)
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(520, puzzleSolver::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(14, exampleSolver::partB)
    }

    @Test
    fun puzzleB() {
        logAndAssertEquals(347338785050515, puzzleSolver::partB)
    }

    @Test
    fun partBTest() {
        exampleSolver = Solver(FileHelper.getAdditionalTestFile(5, "test"))
        logAndAssertEquals(49714559749632, exampleSolver::partB)
    }
}

