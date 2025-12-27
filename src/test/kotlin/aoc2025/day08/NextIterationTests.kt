package aoc2025.day08

import aoc2025.utils.FileHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class NextIterationTest {
    private lateinit var exampleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay(8))
    }

    private fun getCoordGroup(xA: Int, xB: Int): Coords {
        return Coords(ThreeDCoord(xA, xA, xA), ThreeDCoord(xB, xB, xB))
    }

    @Test
    fun nexIterationAddsNewGroups() {
        val testGroups = mutableListOf<Set<ThreeDCoord>>()
        val coordsToAdd = getCoordGroup(1, 2)
        val coordsToAddAgain = getCoordGroup(0, 9)
        val coordsToAddKetiga = getCoordGroup(6, 5)
        var res = exampleSolver.getNextIteration(coordsToAdd, testGroups)
        res = exampleSolver.getNextIteration(coordsToAddAgain, res)
        res = exampleSolver.getNextIteration(coordsToAddKetiga, res)
        assertEquals(3, res.size)
        assertTrue(res.map { it.size }.all { it == 2 })
        assertTrue(res.first().contains(coordsToAdd.a))
        assertTrue(res.first().contains(coordsToAdd.b))
    }

    @Test
    fun nextIterationTestEmpty() {
        val testGroups = mutableListOf<Set<ThreeDCoord>>()
        val coordsToAdd = getCoordGroup(1, 2)
        val res = exampleSolver.getNextIteration(coordsToAdd, testGroups)
        assertEquals(1, res.size)
        assertEquals(2, res.first().size)
        assertTrue(res.first().contains(coordsToAdd.a))
        assertTrue(res.first().contains(coordsToAdd.b))
    }

    @Test
    fun nextIterationMergesTwoWithALink() {
        val testGroups = mutableListOf<Set<ThreeDCoord>>()
        val coordsToAdd = getCoordGroup(1, 2)
        var res = exampleSolver.getNextIteration(coordsToAdd, testGroups)

        val diffCoords = getCoordGroup(0, 4)
        res = exampleSolver.getNextIteration(diffCoords, res)

        assertEquals(2, res.size)
        assertTrue(res.map { it.size }.all { it == 2 })

        val linkingCoords = getCoordGroup(1, 0)
        res = exampleSolver.getNextIteration(linkingCoords, res)

        assertEquals(1, res.size)
        assertEquals(4, res.first().size)
        assertTrue(res.first().contains(coordsToAdd.a))
        assertTrue(res.first().contains(coordsToAdd.b))
    }

    @Test
    fun nextIterationAddsMatches() {
        val testGroups = mutableListOf<Set<ThreeDCoord>>()
        val coordsToAdd = getCoordGroup(1, 2)
        var res = exampleSolver.getNextIteration(coordsToAdd, testGroups)

        val singleMatch = getCoordGroup(2, 6)
        res = exampleSolver.getNextIteration(singleMatch, res)

        assertEquals(1, res.size)
        assertEquals(3, res.first().size)
    }

    @Test
    fun nextIterationDoesNotAddDuplicativeCoords() {
        val testGroups = mutableListOf<Set<ThreeDCoord>>()
        val coordsToAdd = getCoordGroup(1, 2)
        var res = exampleSolver.getNextIteration(coordsToAdd, testGroups)
        res = exampleSolver.getNextIteration(coordsToAdd, res)

        val flippedCoordsToAdd = getCoordGroup(2, 1)
        res = exampleSolver.getNextIteration(flippedCoordsToAdd, res)
        assertEquals(1, res.size)
        assertEquals(2, res.first().size)
        assertTrue(res.first().contains(coordsToAdd.a))
        assertTrue(res.first().contains(coordsToAdd.b))
    }

    @Test
    fun nextIterationComplexAdditions() {
        val testGroups = mutableListOf<Set<ThreeDCoord>>()
        val coordsToAdd = getCoordGroup(1, 2)
        var res = exampleSolver.getNextIteration(coordsToAdd, testGroups)

        val coordsToAddS = getCoordGroup(11, 32)
        res = exampleSolver.getNextIteration(coordsToAddS, res)

        val coordsToAddD = getCoordGroup(51, 22)
        res = exampleSolver.getNextIteration(coordsToAddD, res)

        val diffCoords = getCoordGroup(0, 4)
        res = exampleSolver.getNextIteration(diffCoords, res)

        assertEquals(4, res.size)
        assertTrue(res.map { it.size }.all { it == 2 })

        val coordsToAddC = getCoordGroup(51, 99)
        res = exampleSolver.getNextIteration(coordsToAddC, res)

        val linkingCoords = getCoordGroup(1, 0)
        res = exampleSolver.getNextIteration(linkingCoords, res)

        assertEquals(3, res.size)
        assertEquals(4, res.maxOf { it.size })
        assertTrue(res.last().contains(coordsToAdd.a))
        assertTrue(res.last().contains(coordsToAdd.b))
    }
}

