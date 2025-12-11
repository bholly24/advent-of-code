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
    private var possibleAreas = mutableListOf<Pair<Pair<Coord, Coord>, Long>>()

    fun partA(): Long {
        var maxArea = 0L
        for (coord in coords) {
            for (other in coords) {
                // Add in for part B :)
                possibleAreas.add(Pair(Pair(coord, other), getArea(coord, other)))
            }
            val max = coords.maxOf { (getArea(coord, it)) }
            if (max > maxArea) {
                maxArea = max
            }
        }
        return maxArea
    }

    /*
    Optimization ToDo
    This is a wild, unoptimized mess that got the right answer.
     It got stuck at an index calculating the area for 45 minutes, which I assumed was the right answer.
     Eventually it completed successfully in just over 3 hours.

     The only way it completed under a few days was by shuffling coord lists and memoizing bad coords.
     Even then it's incredibly poorly performing.
     */
    fun partB(): Long {
        val badCoords = mutableListOf<Coord>()
        partA()
        var checked = 0
        val sor = possibleAreas.sortedByDescending { it.second }
        val firstAttempt = sor.first {
            checked++
            if (checked % 10 == 0) println("Checked $checked")
            val perimeter = getCoordPerimeter(it.first.first, it.first.second)
            if (perimeter.any { pc -> badCoords.contains(pc) }) {
                false
            } else {
                perimeter.all { c ->
                    val isGreen = coordIsGreen(c)
                    if (!isGreen) badCoords.add(c)
                    isGreen
                }
            }
        }

        return firstAttempt.second
    }

    // Traverse all neighbors and verify if the coordinate is enclosed by "Green" lines on all sides
    fun coordIsGreen(coord: Coord): Boolean {
        if (coordIsInGreenLine(coord)) return true
        val enclosedBelow = (coord.y downTo minPossibleGreenY).shuffled().any { coordIsInGreenLine(Coord(coord.x, it)) }
        val enclosedAbove = (coord.y..maxPossibleGreenY).shuffled().any { coordIsInGreenLine(Coord(coord.x, it)) }
        val enclosedRight = (coord.x downTo minPossibleGreenX).shuffled().any { coordIsInGreenLine(Coord(it, coord.y)) }
        val enclosedLeft = (coord.x..maxPossibleGreenX).shuffled().any { coordIsInGreenLine(Coord(it, coord.y)) }
        return enclosedBelow && enclosedAbove && enclosedRight && enclosedLeft
    }

    fun getArea(coord: Coord, comp: Coord) = (abs(comp.y - coord.y).toLong() + 1) * (abs(comp.x - coord.x).toLong() + 1)

    private fun getCoordWithWrapping(index: Int) = coords.getOrElse(index + 1, { coords[0] })
    fun coordIsInGreenLine(targetCord: Coord): Boolean {
        for (ci in coords.indices) {
            val c = coords[ci]
            // If this coord is exactly equal to a red coord, it is green
            if (c.y == targetCord.y && c.x == targetCord.x) return true

            /* Otherwise, if the coord shares the x or y axis with another coord,
            verify this coord is between that coord and its neighbor */
            if (c.x == targetCord.x) {
                if (c.y > targetCord.y && getCoordWithWrapping(ci + 1).y < targetCord.y) {
                    return true
                } else if (c.y < targetCord.y && getCoordWithWrapping(ci + 1).y > targetCord.y) {
                    return true
                }
            }

            if (c.y == targetCord.y) {
                if (c.x > targetCord.x && coords.getOrElse(ci + 1, { coords[0] }).x < targetCord.x) {
                    return true
                } else if (c.x < targetCord.x && coords.getOrElse(ci + 1, { coords[0] }).x > targetCord.x) {
                    return true
                }
            }
        }
        return false
    }
}

fun getCoordPerimeter(cOne: Coord, cTwo: Coord): List<Coord> {
    val x = listOf(cOne, cTwo).sortedByDescending { it.x }
    val bigX = x.first().x
    val smallX = x.last().x
    val y = listOf(cOne, cTwo).sortedByDescending { it.y }
    val bigY = y.first().y
    val smallY = y.last().y
    val list = (smallX..bigX).map { Coord(it, bigY) } +
            (smallY..bigY).map { Coord(bigX, it) } +
            (smallX..bigX).map { Coord(it, smallY) } +
            (smallY..bigY).map { Coord(smallX, it) }

    // shuffle the list to improve likelihood of finding an invalid coord compared to x0 to xn iteration
    return list.shuffled()
}
