package aoc2025.day04

import utils.Coord
import utils.Grid
import utils.MutableGrid
import java.io.File

class Solver(filePath: String) {
    val tpGrid = Grid<Char>(File(filePath).readLines().map { it.toMutableList() }.toMutableList())
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

    fun partB(): Int {
        var totalRemoved = 0
        val mutableGrid = tpGrid.toMutableGrid()
        for (y in 0 until mutableGrid.yMax) {
            for (x in 0 until mutableGrid.xMax) {
                val co = Coord(x, y)
                if (mutableGrid.get(co) != '@') continue
                totalRemoved += getNeighborsAndRemove(mutableGrid, co)
            }
        }
        return totalRemoved
    }

    private fun getNeighborsAndRemove(mutableGrid: MutableGrid<Char>, co: Coord): Int {
        if (mutableGrid.get(co) != '@') return 0
        val neighbors = mutableGrid.getNeighborsWithDiagonal(co).filter { mutableGrid.get(it) == '@' }
        if (neighbors.size >= 4) { return 0 }
        mutableGrid.set(Coord(co.x, co.y), '.')
        return 1 + neighbors.sumOf { n -> getNeighborsAndRemove(mutableGrid, n) }
    }
}

