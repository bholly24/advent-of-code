package aoc2024.day14

import aoc2024.UtilityTypes.Coord
import java.io.File

class DayFourteen(path: String) {
    private val lines: List<MutableList<Char>> =
        File(path).readLines().map { line -> line.toCharArray().toMutableList() }
    private var boxGrid: List<MutableList<Char>> = lines.filter { it.isNotEmpty() && it.first() == '#' }
    private val moves = lines.filter { it.isNotEmpty() && it.first() != '#' }.flatten().toList()

    fun partA(): Int {
        var robotIndex = Coord(0, 0)
        boxGrid.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c == '@') robotIndex = Coord(x, y)
            }
        }
        printGrid()

        for (m in moves) {
            val directionCoord = when (m) {
                '^' -> Coord(0, -1)
                'v' -> Coord(0, 1)
                '>' -> Coord(1, 0)
                '<' -> Coord(-1, 0)
                else -> Coord(0, 0)
            }
            var newIndex = robotIndex.add(directionCoord)
            println("$m: ${newIndex.x},${newIndex.y}")
            if (boxGrid[newIndex.y][newIndex.x] == '#') {
                printGrid()
                continue
            }
            val newBoxPositions = mutableListOf<Coord>()
            var next = newIndex.add(directionCoord)
            while (boxGrid[newIndex.y][newIndex.x] == 'O' && boxGrid[next.y][next.x] != '#') {
                newBoxPositions.add(next)
                newIndex = newIndex.add(directionCoord)
                next = newIndex.add(directionCoord)
            }
            if (boxGrid[newIndex.y][newIndex.x] == 'O' &&
                boxGrid[next.y][next.x] == '#') continue

            newBoxPositions.forEach {
                boxGrid[it.y][it.x] = 'O'
            }
            boxGrid[robotIndex.y][robotIndex.x] = '.'
            robotIndex = robotIndex.add(directionCoord)
            boxGrid[robotIndex.y][robotIndex.x] = '@'
            printGrid()
        }

        var total = 0
        for (y in boxGrid.indices) {
            for (x in boxGrid[0].indices) {
                if (boxGrid[y][x] == 'O') total += 100 * y + x
            }
        }

        println("Total for box distance score is $total")
        return total
    }

    private fun printGrid() {
//        for (y in boxGrid.indices) {
//            println()
//            for (x in boxGrid[0].indices) {
//                print(boxGrid[y][x])
//            }
//        }
//        println()
    }
}