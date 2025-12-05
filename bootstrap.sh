#!/bin/bash

PADDED_NUM=$2
if [ "${#PADDED_NUM}" -eq 1 ]; then
    PADDED_NUM="0$PADDED_NUM"
fi

NUM_NO_Zero=$((10#$PADDED_NUM)) 2>/dev/null

filePath="src/main/kotlin/$1/day$PADDED_NUM"
testPath="src/test/kotlin/$1/day$PADDED_NUM"

mkdir "$filePath"

echo """package aoc2025.day$PADDED_NUM

import java.io.File

class Solver(filePath: String) {
    fun partA(): Int {
        return 0
    }

    fun partB(): Int {
        return 0
    }
}
""" > "$filePath/Solver.kt"

mkdir "$testPath"

echo """package aoc2025.day$PADDED_NUM

import aoc2025.utils.FileHelper
import aoc2025.utils.logAndAssertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTest {
    private lateinit var exampleSolver: Solver
    private lateinit var puzzleSolver: Solver

    @BeforeEach
    fun setUp() {
        exampleSolver = Solver(FileHelper.testFileForDay($NUM_NO_Zero))
        puzzleSolver = Solver(FileHelper.puzzleFileForDay($NUM_NO_Zero))
    }

    @Test
    fun partA() {
        logAndAssertEquals(1, exampleSolver::partA)
    }

    @Test
    fun puzzleA() {
        logAndAssertEquals(1, puzzleSolver::partA)
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
""" > "$testPath/SolverTest.kt"

touch "$testPath/input.txt"
touch "$testPath/puzzle.txt"
