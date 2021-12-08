package crabs

import kotlin.properties.Delegates

abstract class CrabCommander(crabPositions: List<Int>) {
    private var leastFuelSpent by Delegates.notNull<Int>();
    protected val sortedCrabs = crabPositions.sorted()
    abstract val startPosition: Int

    protected abstract fun evaluateFuelCost(square: Int): Int

    fun alignCrabs(): Int {
        leastFuelSpent = evaluateFuelCost(startPosition)
        findLocalMinimumByDirection { it - 1 }
        findLocalMinimumByDirection { it + 1 }
        println("Total fuel: $leastFuelSpent")
        return leastFuelSpent
    }

    // Both error functions in this puzzle are continuous and should have only one local minimum
    // thus local = global for this limited set of error functions.
    private fun findLocalMinimumByDirection(squareIncrementer: (currentSquare: Int) -> Int) {
        var newSquare = squareIncrementer(startPosition)
        var newCost = evaluateFuelCost(newSquare)
        while(newCost < leastFuelSpent) {
            newSquare = squareIncrementer(newSquare)
            newCost = evaluateFuelCost(newSquare)
            if (newCost < leastFuelSpent) {
                leastFuelSpent = newCost
            }
        }
    }
}
