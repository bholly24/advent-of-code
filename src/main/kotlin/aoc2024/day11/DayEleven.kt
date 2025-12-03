package aoc2024.day11

import utils.Coord
import utils.Grid
import java.io.File

class DayEleven(path: String) {
    private val gardenGrid: Grid<Char> = Grid(File(path).readLines().map { it.toCharArray().toList() })
    private var plotCoords = mutableSetOf<Coord>()

    fun partA(): Int {
        val gardenPlotList = mutableListOf<GardenPlot>()
        var plotChar: Char? = null
        gardenGrid.items.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                plotCoords = mutableSetOf()
                if (gardenPlotList.any { it.items.any { i -> i.x == x && i.y == y } }) {
                    // continue
                } else {
                    plotChar = c
                    val newPoint = Coord(x, y)
                    plotCoords.add(newPoint)

                    val filtered = gardenGrid.getNeighbors(newPoint)
                        .filter { gardenGrid.get(it) == plotChar }

                    if (filtered.isNotEmpty()) {
                        getPlots(filtered, plotChar!!)
                    }
                    gardenPlotList.add(GardenPlot(plotCoords.toList(), getPlotPerimeter(plotCoords.toList())))
                    plotChar = null
                }
            }
        }
        val totalPrice = gardenPlotList.sumOf { it.getPriceByPerimeter() }
        println("Total garden plot price is $totalPrice")
        return totalPrice
    }

    fun partB(): Int {
        val gardenPlotList = mutableListOf<GardenPlot>()
        var plotChar: Char? = null
        gardenGrid.items.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                plotCoords = mutableSetOf()
                if (gardenPlotList.any { it.items.any { i -> i.x == x && i.y == y } }) {
                    // continue
                } else {
                    plotChar = c
                    val newPoint = Coord(x, y)
                    plotCoords.add(newPoint)

                    val filtered = gardenGrid.getNeighbors(newPoint)
                        .filter { gardenGrid.get(it) == plotChar }

                    if (filtered.isNotEmpty()) {
                        getPlots(filtered, plotChar!!)
                    }
                    gardenPlotList.add(GardenPlot(plotCoords.toList(), getPlotPerimeter(plotCoords.toList())))
                    plotChar = null
                }
            }
        }
        val totalPrice = gardenPlotList.sumOf {
            println(getPlotSides(it.items))
            it.getPriceBySides(getPlotSides(it.items))
        }
        println("Total garden plot price is $totalPrice")
        return totalPrice
    }

    private fun getPlots(neighborList: List<Coord>, plotChar: Char) {
        if (neighborList.isNotEmpty()) {
            neighborList.forEach {
                if (!plotCoords.any { c -> c.x == it.x && c.y == it.y }) {
                    plotCoords.add(it)
                    val newNeighbors = gardenGrid.getNeighbors(it)
                        .filter { nn -> gardenGrid.get(nn) == plotChar && !plotCoords.any { c -> c.x == nn.x && c.y == nn.y } }
                    if (newNeighbors.isNotEmpty()) {
                        getPlots(newNeighbors, plotChar)
                    }
                }
            }
        }
    }

    private fun getPlotPerimeter(items: List<Coord>): Int =
        items.sumOf {
            val n = gardenGrid.getNeighbors(it)
            val emptyPerimeter = 4 - n.size
            val gridNeighbors: Int =
                gardenGrid.getNeighbors(it).filter { n -> gardenGrid.get(n) == gardenGrid.get(it) }.size
            emptyPerimeter + n.size - gridNeighbors
        }

    private fun getPlotSides(gridList: List<Coord>): Int {
        var sides = 0
        val xSides = mutableListOf<Coord>()
        val ySides = mutableListOf<Coord>()

        for (c in gridList) {
            val f = gardenGrid.getNeighbors(c).filter { n -> gardenGrid.get(n) != gardenGrid.get(c) }
            val relevantChecks = f + gardenGrid.getOutOfBoundsNeighbors(c)

            relevantChecks.forEach { nC ->
//                if (nC.x != c.x && !xSides.any { x -> getCoordNeighbors(x).any { i -> i.x != nC.x && i != nC.y } }) {
                    if (nC.x != c.x && !xSides.any { c -> c.x == nC.x}) {
                        xSides.add(nC)
                        sides ++
                    } else if (nC.y != c.y && !ySides.any { c -> c.y == nC.y }) {
                        ySides.add(nC)
                        sides ++
                    }
                }
        }
        return sides
    }

    private fun getCoordNeighbors(point: Coord): List<Coord> {
        return listOf(
            Coord(point.x + 1, point.y),
            Coord(point.x - 1, point.y),
            Coord(point.x, point.y + 1),
            Coord(point.x, point.y - 1)
        )
    }
}

class GardenPlot(val items: List<Coord>, private val perimeter: Int) {
    fun getPriceByPerimeter() = items.size * perimeter
    fun getPriceBySides(sides: Int) = items.size * sides
}