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

    fun partA(totalConnections: Int): Int {
        val comparisonList = coords.toMutableList().toList()
        val distances = mutableListOf<Pair<Double, Coords>>()
        for (initialCoord in coords) {
            for (compareCoord in comparisonList) {
                if (initialCoord != compareCoord) {
                    distances.add(Pair(initialCoord.getDistance(compareCoord), Coords(compareCoord, initialCoord)))
                }
            }
        }
        if ((distances.size + coords.size) != (coords.size * coords.size)) throw Exception("Wow!!!")

        val sortedCoords = distances.sortedBy { it.first }.toSet().toList().chunked(2).map { it.first() }
        val coordsToAdd: List<Coords> = sortedCoords.map { it.second }
        val coordGroups = mutableListOf<MutableSet<ThreeDCoord>>()
        for (cg in coordsToAdd.take(totalConnections)) {
            val g = coordGroups.filter { it.contains(cg.a) || it.contains(cg.b) && !(it.contains(cg.a) && it.contains(cg.b)) }
            val a = mutableSetOf(cg.a, cg.b)
            if (g.isEmpty()) {
                coordGroups.add(a)
            } else {
                val s = g.flatten().toMutableSet()
                coordGroups.remove(g.first())
                coordGroups.remove(g.last())
                s.addAll(mutableListOf(cg.a, cg.b))
                coordGroups.add(s)
            }
        }
        return coordGroups.map { it.size }.sortedByDescending { it }.take(3).reduce { acc, i -> acc * i }
    }

    fun partB(): Int {
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
        var coordGroups = mutableListOf<MutableSet<ThreeDCoord>>()

        var added = 0
        for (coord in coordsToAdd) {
            val matchingCoordGroups = coordGroups.filter { it.contains(coord.a) || it.contains(coord.b) }
            val setOfCoordsToAdd = mutableSetOf(coord.a, coord.b)
            if (matchingCoordGroups.isEmpty()) {
                coordGroups.add(setOfCoordsToAdd)
            } else {
                val combinedMatchingCoords = (matchingCoordGroups.flatten() + setOfCoordsToAdd).toMutableSet()
                println("Before ${coordGroups.size}")
                // Remove
                coordGroups = coordGroups.filter { !it.contains(coord.a) && !it.contains(coord.b) }.toMutableList()
                coordGroups.add(combinedMatchingCoords)
                println("Now ${coordGroups.size}")
            }
            added++
            val i = sortedCoords.indexOfFirst { it.second.a.x == 117 && it.second.b.x == 216 }
            if (coordGroups.size == 1 && added > 5) {
                return coord.a.x * coord.b.x
            }
        }
        return coordGroups.size
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
