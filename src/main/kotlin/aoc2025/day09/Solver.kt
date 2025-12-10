package aoc2025.day09

import utils.Coord
import java.io.File
import kotlin.math.abs

class Solver(filePath: String) {
    val coords = File(filePath)
        .readLines()
        .map { Coord(it.split(",").first().toInt(), it.split(",").last().toInt()) }
    val possibleGreenX = coords.map { it.x }.toSortedSet()
    val minPossibleGreenX: Int = possibleGreenX.min()
    val maxPossibleGreenX: Int = possibleGreenX.max()
    val possibleGreenY = coords.map { it.y }.toSortedSet()
    val minPossibleGreenY: Int = possibleGreenY.min()
    val maxPossibleGreenY: Int = possibleGreenY.max()
    private var possibleAreas = mutableListOf<Pair<Pair<Coord, Coord>,Long>>()

    fun partA(): Long {
        var maxArea = 0L
        for (coord in coords) {
            for (other in coords) {
                possibleAreas.add(Pair(Pair(coord, other), getArea(coord, other)))
            }
            val max = coords.maxOf { (getArea(coord, it)) }
            if (max > maxArea) {
                maxArea = max
            }
        }
        return maxArea
    }

    // Too high 4604141472
    fun partB(): Long {
        val badCoords = mutableListOf<Coord>()
        partA()
        val stuckAtIndex = 96048
        val sor = possibleAreas.sortedByDescending { it.second }
        var checked = 0
        val f = sor.drop(96048).first().second
        val firstAttempt = sor.drop(96045).take(12)
            .first {
                println("Value is ${it.second}")
                val per = getCoordPerimeter(it.first.first, it.first.second)
                println(checked++)
                if (per.any { pc -> badCoords.contains(pc) }) {
                    false
                } else {
                    per.all { c ->
                        val s = coordIsGreen(c)
                        if (!s) badCoords.add(c)
                        s
                    }
                }
            }

        return firstAttempt.second
    }

    fun coordIsGreen(coord: Coord): Boolean {
        if (coordIsInGreenLine(coord)) return true
        val enclosedBelow = (coord.y downTo minPossibleGreenY).shuffled().any { coordIsInGreenLine(Coord(coord.x, it)) }
        println("${coord.y} $minPossibleGreenY")
        val enclosedAbove = (coord.y..maxPossibleGreenY).shuffled().any { coordIsInGreenLine(Coord(coord.x, it)) }
        val enclosedRight = (coord.x downTo minPossibleGreenX).shuffled().any { coordIsInGreenLine(Coord(it, coord.y)) }
        val enclosedLeft = (coord.x..maxPossibleGreenX).shuffled().any { coordIsInGreenLine(Coord(it, coord.y)) }
        return enclosedBelow && enclosedAbove && enclosedRight && enclosedLeft
    }

    fun getArea(coord: Coord, comp: Coord) = (abs(comp.y - coord.y).toLong() + 1) * (abs(comp.x - coord.x).toLong() + 1)

    fun coordIsInGreenLine(coord: Coord): Boolean {
        for (ci in coords.indices) {
            val c = coords[ci]
            if (c.y == coord.y && c.x == coord.x) return true
            if (c.x == coord.x) {
                if (c.y > coord.y && coords.getOrElse(ci + 1, { coords[0] }).y < coord.y) {
                    return true
                } else if (c.y < coord.y && coords.getOrElse(ci + 1, { coords[0] }).y > coord.y) {
                    return true
                }
            }

            if (c.y == coord.y) {
                if (c.x > coord.x && coords.getOrElse(ci + 1, { coords[0] }).x < coord.x) {
                    return true
                } else if (c.x < coord.x && coords.getOrElse(ci + 1, { coords[0] }).x > coord.x) {
                    return true
                }
            }
        }
        return false
    }
}

fun getCoordPerimeter(cOne: Coord, cTwo: Coord): List<Coord> {
    var bigX: Int
    var smallX: Int
    if (cOne.x > cTwo.x) {
        bigX = cOne.x
        smallX = cTwo.x
    } else {
        bigX = cTwo.x
        smallX = cOne.x
    }
    var bigY: Int
    var smallY: Int

    if (cOne.y > cTwo.y) {
        bigY = cOne.y
        smallY = cTwo.y
    } else {
        bigY = cTwo.y
        smallY = cOne.y
    }
    val list = (smallX .. bigX).map { Coord(it, bigY) } +
            (smallY .. bigY).map { Coord(bigX, it) } +
            (smallX .. bigX).map { Coord(it, smallY) } +
            (smallY .. bigY).map { Coord(smallX, it) }
    return list.shuffled()
}
