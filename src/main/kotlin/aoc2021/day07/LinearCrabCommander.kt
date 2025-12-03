package aoc2021.day07

import kotlin.math.ceil

class LinearCrabCommander(crabPositions: List<Int>) : CrabCommander(crabPositions) {
    override val startPosition = sortedCrabs[ceil((sortedCrabs.size / 2).toDouble()).toInt()]

    override fun evaluateFuelCost(square: Int): Int {
        var totalFuel = 0
        sortedCrabs.forEach { totalFuel += if (it > square) it - square else square - it }
        return totalFuel
    }
}
