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
        val splitMaps = mutableMapOf<Pair<Int, Int>, Long>()
    }

    fun partB(): Long {
        val startIndex = beamGrid.items.first().indexOfFirst { it == 'S' }
        return calculateMemoizedTimelines(Coord(startIndex, 0))
    }

    private fun calculateMemoizedTimelines(startCoord: Coord): Long {
        val newY = startCoord.y + 1
        if (newY > beamGrid.yLastIndex()) return 1
        val targetCoord = Coord(startCoord.x, newY)
        if (PartB.splitMaps.contains(Pair(targetCoord.x, targetCoord.y))) {
            return PartB.splitMaps.getValue(Pair(targetCoord.x, targetCoord.y))
        }
        val gridVal = beamGrid.get(targetCoord)
        return when (gridVal) {
            '.' -> calculateMemoizedTimelines(targetCoord)
            '^' -> {
                val value = listOf(Coord(startCoord.x - 1, newY), Coord(startCoord.x + 1, newY))
                    .filter { beamGrid.isInBounds(it) }
                    .sumOf { calculateMemoizedTimelines(it) }
                PartB.splitMaps[Pair(targetCoord.x, targetCoord.y)] = value
                value
            }
            else -> throw Exception("This should not be possible in an unmutated problem input")
        }
    }
}
