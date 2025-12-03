package aoc2021.day12

class Cave(val identifier: String) {
    val isBigCave = identifier.toCharArray().all { it.isUpperCase() }
    var caveConnections = mutableListOf<Cave>()
    var connectsToEnd = false
}
