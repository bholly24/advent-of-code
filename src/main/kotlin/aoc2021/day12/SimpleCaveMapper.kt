package aoc2021.day12

class SimpleCaveMapper(filePath: String) : CaveMapper(filePath) {
    override fun pathShouldBeTraversed(node: Cave, path: List<Cave>): Boolean {
        val smallCaveIds = path.filter { !it.isBigCave }.map { it.identifier }
        return node.isBigCave || !smallCaveIds.contains(node.identifier)
    }
}