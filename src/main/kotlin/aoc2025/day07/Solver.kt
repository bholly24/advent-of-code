package aoc2025.day07

import utils.Coord
import utils.Grid
import utils.MutableGrid
import java.io.File

class Solver(filePath: String) {
    private var beamSplits = 0
    private var beamDiffTimelines = 0L
    private val beamGrid = Grid(File(filePath).readLines().map(String::toList))
    private val splitMaps = mutableMapOf<Pair<Int, Int>, Long>()

    fun partA(): Int {
        val mutableGrid = beamGrid.toMutableGrid()
        val startIndex = beamGrid.items.first().indexOfFirst { it == 'S' }
        mutateGridFromStart(mutableGrid, Coord(startIndex, 0))
        mutableGrid.print()
        return beamSplits
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
                beamSplits++
                listOf(Coord(c.x - 1, newY), Coord(c.x + 1, newY))
                    .filter { grid.isInBounds(it) }
                    .forEach {
                        grid.set(it, '|')
                        mutateGridFromStart(grid, it)
                    }
            }
        }
    }

    fun partB(): Long {
        val startIndex = beamGrid.items.first().indexOfFirst { it == 'S' }
        mutateGridTimelinesMemoized(Coord(startIndex, 0))
        return splitMaps.values.max()
    }

    private fun mutateGridTimelinesMemoized(startCoord: Coord) {
        val newY = startCoord.y + 1
        if (newY > beamGrid.yLastIndex()) {
            beamDiffTimelines++
            return
        }
        val targetCoord = Coord(startCoord.x, newY)
        if (splitMaps.contains(Pair(targetCoord.x, targetCoord.y))) {
            beamDiffTimelines += splitMaps.getValue(Pair(targetCoord.x, targetCoord.y))
            return
        }
        val gridVal = beamGrid.get(targetCoord)
        when (gridVal) {
            '.' -> mutateGridTimelinesMemoized(targetCoord)
            '^' -> {
                val startDiff = beamDiffTimelines
                listOf(Coord(startCoord.x - 1, newY), Coord(startCoord.x + 1, newY))
                    .filter { beamGrid.isInBounds(it) }
                    .forEach { mutateGridTimelinesMemoized(it) }
                splitMaps[Pair(targetCoord.x, targetCoord.y)] = beamDiffTimelines - startDiff
            }
        }
    }
}
