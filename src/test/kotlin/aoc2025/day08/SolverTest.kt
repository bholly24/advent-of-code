package aoc2025.day08

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(8))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay(8))
    }

    @Test
    fun partA() {
        logAndAssertEquals(40, { exampleSolver.partA(10) })
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(26400, { puzzleSolver.partA(1000) })
    }

    @Test
    fun partB() {
        logAndAssertEquals(25272, exampleSolver::partB)
    }

    @Test
    fun puzzleB() {
        logAndAssertEquals(8199963486, puzzleSolver::partB)
    }
    //Too low: 923223020
}

