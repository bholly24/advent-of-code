package day12

import java.io.File
import java.io.InputStream


abstract class CaveMapper(filePath: String) {
    private val caves = mutableListOf<Cave>()
    private val startList = mutableListOf<Cave>()
    private val completedPaths = mutableListOf<List<String>>()

    init {
        readInLines(filePath)
    }

    abstract fun pathShouldBeTraversed(node: Cave, path: List<Cave>): Boolean

    fun getTotalPaths(): Int {
        startList.forEach { findPath(mutableListOf(it)) }
        val totalPaths = completedPaths.size
        println("Total possible paths: $totalPaths")
        return totalPaths
    }

    private fun readInLines(filePath: String) {
        val inputStream: InputStream = File(filePath).inputStream()

        inputStream.bufferedReader().forEachLine { line -> parseCave(line) }
    }

    private fun findPath(path: List<Cave>) {
        val lastNode = path.last()
        if (lastNode.connectsToEnd) {
            completedPaths.add(path.map { it.identifier })
        }

        for (node in lastNode.caveConnections) {
            if (pathShouldBeTraversed(node, path)) {
                findPath(path.plus(node))
            }
        }
    }

    private fun parseCave(caveLine: String) {
        val caveSplit = caveLine.split('-')
        if (caveLine.contains("start")) {
            startList.add(findOrCreateCave(caveSplit.filter { it != "start" }[0]))
        } else if (caveLine.contains("end")) {
            findOrCreateCave(caveSplit.filter { it != "end" }[0]).connectsToEnd = true
        } else {
            val from = caveSplit[0]
            val to = caveSplit[1]
            findOrCreateCave(from).caveConnections.add(findOrCreateCave(to))
            findOrCreateCave(to).caveConnections.add(findOrCreateCave(from))
        }
    }

    private fun findOrCreateCave(identifier: String): Cave {
        var cave = caves.find { it.identifier == identifier }
        if (cave == null) {
            cave = Cave(identifier)
            caves.add(cave)
        }
        return cave
    }
}
