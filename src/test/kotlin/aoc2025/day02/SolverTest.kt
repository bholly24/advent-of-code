package aoc2025.day02

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(2))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay(2))
    }

    @Test
    fun partA() {
        logAndAssertEquals(1227775554, exampleSolver::partA)
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(44487518055, puzzleSolver::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(4174379265, exampleSolver::partB)
    }

    @Test
    fun puzzleB() {
        logAndAssertEquals(53481866137, puzzleSolver::partB)
    }

    @Test
    fun findsDuplicateSequence() {
        assertTrue(exampleSolver.containsDuplicateSequence("111111"))
        assertTrue(exampleSolver.containsDuplicateSequence("12341234"))
        assertFalse(exampleSolver.containsDuplicateSequence("1"))
        assertFalse(exampleSolver.containsDuplicateSequence("112"))
        assertFalse(exampleSolver.containsDuplicateSequence("111"))
    }

    @Test
    fun findsSubsequence() {
        assertTrue(exampleSolver.containsSubsequence("111111"))
        assertTrue(exampleSolver.containsSubsequence("12341234"))
        assertTrue(exampleSolver.containsSubsequence("123123123"))
        assertFalse(exampleSolver.containsSubsequence("1"))
        assertFalse(exampleSolver.containsSubsequence("112"))
        assertTrue(exampleSolver.containsSubsequence("111"))
    }
}

