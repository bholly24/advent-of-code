package aoc2025.day08

import java.io.File
import kotlin.math.pow
import kotlin.math.sqrt

class Solver(filePath: String) {
    private val coords = File(filePath).readLines()
        .map {
            ThreeDCoord(
                it.split(",")[0].toInt(),
                it.split(",")[1].toInt(),
                it.split(",")[2].toInt()
            )
        }
    private val sortedCoordGroups = getSortedCoordinateGroups()

    fun partA(totalConnections: Int): Int {
        val coordGroups = sortedCoordGroups
            .take(totalConnections)
            .fold(listOf<Set<ThreeDCoord>>()) { acc, coords ->
                getNextIteration(coords, acc)
            }

        return coordGroups
            .map { it.size }
            .sortedByDescending { it }
            .take(3)
            .reduce { acc, i -> acc * i }
    }

    fun partB(): Long {
        var coordGroups = listOf<Set<ThreeDCoord>>()
        for (coord in sortedCoordGroups) {
            coordGroups = getNextIteration(coord, coordGroups)
            if (coordGroups.size == 1 && coordGroups.first().size == coords.size) {
                return coord.a.x.toLong() * coord.b.x.toLong()
            }
        }
        throw Error("No solution was reached")
    }

    private fun getSortedCoordinateGroups(): List<Coords> {
        val comparisonList = coords.toMutableList().toList()
        val distances = mutableListOf<Pair<Double, Coords>>()
        for (initialCoord in coords) {
            for (compareCoord in comparisonList) {
                if (initialCoord != compareCoord) {
                    distances.add(Pair(initialCoord.getDistance(compareCoord), Coords(compareCoord, initialCoord)))
                }
            }
        }
        val sortedCoords = distances.sortedBy { it.first }.toSet().toList().chunked(2).map { it.first() }
        val coordsToAdd: List<Coords> = sortedCoords.map { it.second }
        return coordsToAdd
    }

    fun getNextIteration(coord: Coords, existingGroups: List<Set<ThreeDCoord>>): List<Set<ThreeDCoord>> {
        var coordGroups = existingGroups.toMutableList()
        val matchingCoordGroups = coordGroups.filter { it.contains(coord.a) || it.contains(coord.b) }
        val setOfCoordsToAdd = mutableSetOf(coord.a, coord.b)
        if (matchingCoordGroups.isEmpty()) {
            coordGroups.add(setOfCoordsToAdd)
        } else {
            val combinedMatchingCoords = (matchingCoordGroups.flatten() + setOfCoordsToAdd).toMutableSet()
            coordGroups = coordGroups.filter { !(it.contains(coord.a) || it.contains(coord.b)) }.toMutableList()
            coordGroups.add(combinedMatchingCoords)
        }
        return coordGroups
    }
}

    data class ThreeDCoord(val x: Int, val y: Int, val z: Int) {
        fun getDistance(o: ThreeDCoord): Double {
            val x = (o.x - x).toDouble().pow(2.0)
            val y = (o.y - y).toDouble().pow(2.0)
            val z = (o.z - z).toDouble().pow(2.0)
            return sqrt(x + y + z)
        }
    }

    data class Coords(val a: ThreeDCoord, val b: ThreeDCoord)
