package aoc2025.day07

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(7))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay(7))
    }

    @Test
    fun partA() {
        logAndAssertEquals(21, exampleSolver::partA)
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(1533, puzzleSolver::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(40L, exampleSolver::partB)
    }

    @Test
    fun puzzleB() {
        logAndAssertEquals(10733529153890, puzzleSolver::partB)
    }
}

