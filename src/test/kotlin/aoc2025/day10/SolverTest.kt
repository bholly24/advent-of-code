package aoc2025.day10

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(10))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay(10))
    }

    @Test
    fun partA() {
        logAndAssertEquals(7, exampleSolver::partA)
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(535, puzzleSolver::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(1, exampleSolver::partB)
    }

    @Test
    fun puzzleB() {
        logAndAssertEquals(1, puzzleSolver::partB)
    }
}

