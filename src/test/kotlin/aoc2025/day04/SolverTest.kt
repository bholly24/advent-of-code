package aoc2025.day04

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(4))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay(4))
    }

    @Test
    fun partA() {
        logAndAssertEquals(13, exampleSolver::partA)
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(1435, puzzleSolver::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(43, exampleSolver::partB)
    }

    @Test
    fun puzzleB() {
        logAndAssertEquals(8623, puzzleSolver::partB)
    }
}

