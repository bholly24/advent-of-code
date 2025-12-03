package aoc2024.day13

import utils.Coord
import java.io.File

class DayThirteen(path: String, private val l: Int, private val w: Int) {
    private val robotInstructions: List<RobotInstructions> = File(path).readLines().map { parseRobotInstructions(it) }

    fun partA(): Int {
        for (x in 0 until 100) {
            robotInstructions.forEach { updateRobotPosition(it) }
        }
        val p = getProductOfRobotsInQuadrants()
        println("Total product of robots in quadrants is $p")
        return p
    }

    fun partB(): Int {
        for (sec in 1 until 1000000) {
            robotInstructions.forEach { updateRobotPosition(it) }
            if (getRobotsWithNeighbors() > robotInstructions.size / 2) {
                for (y in 0 until l) {
                    println()
                    for (x in 0 until w) {
                        if (robotInstructions.any { it.position.equals(Coord(x, y)) }) print("*") else print(".")
                    }
                }
                return sec
            }
        }
        return -1
    }

    private fun updateRobotPosition(robotInstructions: RobotInstructions) {
        var x = robotInstructions.position.x + robotInstructions.velocity.x
        if (x >= w) x %= w
        if (x < 0) x += w
        var y = robotInstructions.position.y + robotInstructions.velocity.y
        if (y >= l) y %= l
        if (y < 0) y += l
        robotInstructions.position = Coord(x, y)
    }

    private fun getRobotsWithNeighbors(): Int {
       return robotInstructions.filter {
            it.position.getSurroundingCoords().any { robotInstructions.any { r -> r.position.equals(it) }}
        }.size
    }

    private fun getProductOfRobotsInQuadrants(): Int {
        return robotInstructions.filter { it.position.x in 0 until w/2 && it.position.y in 0 until l/2 }.size *
                robotInstructions.filter { it.position.x in 0 until w/2 && it.position.y in (l/2 + 1)..l }.size *
                robotInstructions.filter { it.position.x in (w/2 + 1)..w && it.position.y in (l/2 + 1)..l }.size *
                robotInstructions.filter { it.position.x in (w/2 + 1)..w && it.position.y in 0 until l/2 }.size
    }

    private fun parseRobotInstructions(instructionString: String): RobotInstructions {
        val spaceSplit = instructionString.split(" ")
            .map { it.split("=")[1].split(",").map { it.toInt() } }
        return RobotInstructions(Coord(spaceSplit[0][0], spaceSplit[0][1]), Coord(spaceSplit[1][0], spaceSplit[1][1]))
    }
}

data class RobotInstructions(var position: Coord, val velocity: Coord)