package aoc2025.day03

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(3))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay(3))
    }

    @Test
    fun partA() {
        logAndAssertEquals(357L, exampleSolver::partA)
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(17144L, puzzleSolver::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(3121910778619L, exampleSolver::partB)
    }

    @Test
    fun puzzleB() {
        logAndAssertEquals(170371185255900L, puzzleSolver::partB)
    }
}

