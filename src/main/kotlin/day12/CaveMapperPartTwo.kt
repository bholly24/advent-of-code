package day12

import java.io.File
import java.io.InputStream

class CaveMapperPartTwo(filePath: String) : CaveMapper(filePath) {

    override fun pathShouldBeTraversed(node: Cave, path: List<Cave>): Boolean {
        val smallCaveIds = path.filter { !it.isBigCave }.map { it.identifier }
        return node.isBigCave || smallCaveIds.size == smallCaveIds.distinct().size || !smallCaveIds.contains(node.identifier)
    }
}