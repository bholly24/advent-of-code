package aoc2025.day09

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.Coord
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver
    private lateinit var testSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(9))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay(9))
        testSolver = Solver(FileHelper.getAdditionalTestFile(9, "test"))
    }

    @Test
    fun partA() {
        logAndAssertEquals(50, exampleSolver::partA)
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(4769758290, puzzleSolver::partA)
    }

    @Test
    fun partB() {
        logAndAssertEquals(24, exampleSolver::partB)
    }

    @Test
    fun puzzleB() {
        logAndAssertEquals(1588990708, puzzleSolver::partB)
    }

    fun testCoordIsInLine(x: Int, y: Int) = exampleSolver.coordIsInGreenLine(Coord(x, y))
    fun testCoordIsGreen(x: Int, y: Int) = exampleSolver.coordIsGreen(Coord(x, y))

    @Test
    fun coordNotInGreen() {
        assertFalse { testCoordIsInLine(0, 0) }
        assertFalse { testCoordIsInLine(6, 1) }
        assertFalse { testCoordIsInLine(6, 2) }
        assertFalse { testCoordIsInLine(12, 1) }
    }

    @Test
    fun allRedCoordsAreGreen() {
        assertTrue { testCoordIsInLine(9, 1) }
        assertTrue { testCoordIsInLine(11, 1) }
        assertTrue { testCoordIsInLine(2, 5) }
        assertTrue { testCoordIsInLine(9, 5) }
    }

    @Test
    fun coordInALineIsGreen() {
        assertTrue { testCoordIsInLine(7, 1) }
        assertTrue { testCoordIsInLine(6, 5) }
    }

    @Test
    fun enclosedCoordIsGreen() {
        assertTrue { testCoordIsGreen(8, 2)}
        assertFalse { testCoordIsGreen(6, 2)}
    }

    fun mytestLine(x: Int, y: Int) = testSolver.coordIsInGreenLine(Coord(x, y))
    fun mytest(x: Int, y: Int) = testSolver.coordIsGreen(Coord(x, y))

    @Test
    fun myTestFails() {
        assertFalse { mytestLine(4, 0) }
//        assertFalse { mytest(1, 1) }
    }
}

