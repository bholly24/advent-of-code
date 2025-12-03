package aoc2021.day07

import kotlin.math.ceil

class ExponentialCrabCommander(crabPositions: List<Int>) : CrabCommander(crabPositions) {
    override val startPosition = ceil(sortedCrabs.average()).toInt()

    override fun evaluateFuelCost(square: Int): Int {
        var totalFuel = 0
        sortedCrabs.forEach {
            val error = (if (it > square) it - square else square - it)
            if (error > 0) totalFuel += (1..error).sum()
        }
        return totalFuel
    }
}
