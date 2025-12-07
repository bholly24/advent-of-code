package aoc2025.day07

import utils.Coord
import utils.Grid
import utils.MutableGrid
import java.io.File

class Solver(filePath: String) {
    private val beamGrid = Grid(File(filePath).readLines().map(String::toList))

    private object PartA {
        var beamSplits = 0
    }

    fun partA(): Int {
        val mutableGrid = beamGrid.toMutableGrid()
        val startIndex = beamGrid.items.first().indexOfFirst { it == 'S' }
        mutateGridFromStart(mutableGrid, Coord(startIndex, 0))
        mutableGrid.print()
        return PartA.beamSplits
    }

    private fun mutateGridFromStart(grid: MutableGrid<Char>, c: Coord): Unit {
        val newY = c.y + 1
        if (newY > grid.yLastIndex()) return
        val targetCoord = Coord(c.x, newY)
        val gridVal = grid.get(targetCoord)
        when (gridVal) {
            '.' -> {
                grid.set(targetCoord, '|')
                mutateGridFromStart(grid, targetCoord)
            }
            '|' -> grid.set(targetCoord, '|')
            '^' -> {
                PartA.beamSplits++
                listOf(Coord(c.x - 1, newY), Coord(c.x + 1, newY))
                    .filter { grid.isInBounds(it) }
                    .forEach {
                        grid.set(it, '|')
                        mutateGridFromStart(grid, it)
                    }
            }
        }
    }

    private object PartB {
        var beamDiffTimelines = 0L
        val splitMaps = mutableMapOf<Pair<Int, Int>, Long>()
    }

    fun partB(): Long {
        val startIndex = beamGrid.items.first().indexOfFirst { it == 'S' }
        calculateMemoizedTimelines(Coord(startIndex, 0))
        return PartB.splitMaps.values.max()
    }

    private fun calculateMemoizedTimelines(startCoord: Coord) {
        val newY = startCoord.y + 1
        if (newY > beamGrid.yLastIndex()) {
            PartB.beamDiffTimelines++
            return
        }
        val targetCoord = Coord(startCoord.x, newY)
        if (PartB.splitMaps.contains(Pair(targetCoord.x, targetCoord.y))) {
            PartB.beamDiffTimelines += PartB.splitMaps.getValue(Pair(targetCoord.x, targetCoord.y))
            return
        }
        val gridVal = beamGrid.get(targetCoord)
        when (gridVal) {
            '.' -> calculateMemoizedTimelines(targetCoord)
            '^' -> {
                val startDiff = PartB.beamDiffTimelines
                listOf(Coord(startCoord.x - 1, newY), Coord(startCoord.x + 1, newY))
                    .filter { beamGrid.isInBounds(it) }
                    .forEach { calculateMemoizedTimelines(it) }
                PartB.splitMaps[Pair(targetCoord.x, targetCoord.y)] = PartB.beamDiffTimelines - startDiff
            }
        }
    }
}
