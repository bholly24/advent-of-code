package aoc2025.day07

import utils.Coord
import utils.Grid
import utils.MutableGrid
import java.io.File

class Solver(filePath: String) {
    private val beamGrid = Grid(File(filePath).readLines().map(String::toList))
    private var beamSplits = 0

    fun partA(): Int {
        val mutableGrid = beamGrid.toMutableGrid()
        val startIndex = beamGrid.items.first().indexOfFirst { it == 'S' }
        mutateGridFromStart(mutableGrid, Coord(startIndex, 0))
        mutableGrid.print()
        return beamSplits
    }

    private fun mutateGridFromStart(grid: MutableGrid<Char>, c: Coord): Unit {
        val targetCoord = Coord(c.x, c.y + 1)
        if (!grid.coordExists(targetCoord)) return
        val gridVal = grid.get(targetCoord)
        when (gridVal) {
            '.' -> {
                grid.set(targetCoord, '|')
                mutateGridFromStart(grid, targetCoord)
            }
            '|' -> grid.set(targetCoord, '|')
            '^' -> {
                beamSplits++
                listOf(Coord(c.x - 1, targetCoord.y), Coord(c.x + 1, targetCoord.y))
                    .filter { grid.isInBounds(it) }
                    .forEach {
                        grid.set(it, '|')
                        mutateGridFromStart(grid, it)
                    }
            }
        }
    }

    fun partB(): Long {
        val memoMap = mutableMapOf<Coord, Long>()
        val startIndex = beamGrid.items.first().indexOfFirst { it == 'S' }
        return calculateMemoizedTimelines(Coord(startIndex, 0), memoMap)
    }

    private fun calculateMemoizedTimelines(startCoord: Coord, memoMap: MutableMap<Coord, Long>): Long {
        val targetCoord = Coord(startCoord.x, startCoord.y + 1)
        if (!beamGrid.coordExists(targetCoord)) return 1
        if (memoMap.contains(targetCoord)) return memoMap.getValue(targetCoord)

        val gridVal = beamGrid.get(targetCoord)
        return when (gridVal) {
            '.' -> calculateMemoizedTimelines(targetCoord, memoMap)
            '^' -> {
                val value = listOf(Coord(startCoord.x - 1, targetCoord.y), Coord(startCoord.x + 1, targetCoord.y))
                    .filter { beamGrid.isInBounds(it) }
                    .sumOf { calculateMemoizedTimelines(it, memoMap) }
                memoMap[targetCoord] = value
                value
            }
            else -> throw Exception("This should not be possible in an unmutated problem input")
        }
    }
}
