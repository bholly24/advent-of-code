package aoc2025.day04

import utils.Coord
import utils.MutableGrid
import java.io.File

class DayFour(filePath: String) {
    val tpGrid = MutableGrid<Char>(File(filePath).readLines().map { it.toMutableList() }.toMutableList())
    fun partA(): Int {
        var total = 0
        for (y in 0 until tpGrid.yMax) {
            for (x in 0 until tpGrid.xMax) {
                val co = Coord(x, y)
                if (tpGrid.get(co) != '@') continue
                val totalTp = tpGrid.getNeighborsWithDiagonal(co).count { tpGrid.get(it) == '@' }
                if (totalTp < 4) total++
            }
        }
        return total
    }

    fun partB() = bruteForceTpRemoval(tpGrid, 0)

    fun bruteForceTpRemoval(grid: MutableGrid<Char>, totalRemoved: Int): Int {
        for (y in 0 until grid.yMax) {
            for (x in 0 until grid.xMax) {
                val co = Coord(x, y)
                if (grid.get(co) != '@') continue
                val totalTp = grid.getNeighborsWithDiagonal(co).count { tpGrid.get(it) == '@' }
                if (totalTp < 4) {
                    val newGrid = grid.copy()
                    newGrid.set(Coord(x, y), '.')
                    return bruteForceTpRemoval(newGrid, totalRemoved + 1)
                }
            }
        }
        return totalRemoved
    }
}