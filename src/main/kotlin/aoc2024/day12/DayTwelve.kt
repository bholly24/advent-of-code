package aoc2024.day12

import utils.Coord
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

class DayTwelve(path: String) {
    private val machines: List<Machine> = File(path).readLines().chunked(4).map { l ->
        val xYSplit = l.map { s -> s.split(",") }
        val a = Coord(xYSplit[0][0].filter { it.isDigit() }.toInt(), xYSplit[0][1].filter { it.isDigit() }.toInt())
        val b = Coord(xYSplit[1][0].filter { it.isDigit() }.toInt(), xYSplit[1][1].filter { it.isDigit() }.toInt())
        val p = Coord(xYSplit[2][0].filter { it.isDigit() }.toInt(), xYSplit[2][1].filter { it.isDigit() }.toInt())
        Machine(a, b, p)
    }

    private val longMachines: List<LongMachine> = machines
        .map {
            LongMachine(
                it.buttonA,
                it.buttonB,
                10000000000000 + it.prize.x,
                10000000000000 + it.prize.y
            )
        }

    fun partA(): Int {
        val sumOfTokens = machines.sumOf { it.getLowestTokenCost() }
        println("Sum of tokens is $sumOfTokens")
        return sumOfTokens
    }

    fun partASolve(): Int {
        val solve = machines.sumOf { it.solveTokenCost() }
        println("Sum of tokens with solve is $solve")
        return solve
    }

    fun partB(): Long {
       val sumOfTokens = longMachines.sumOf { it.solveTokenCost() }
        println("Sum of long tokens is $sumOfTokens")
        return sumOfTokens
    }
}

data class Machine(val buttonA: Coord, val buttonB: Coord, val prize: Coord) {
    fun getLowestTokenCost(): Int {
        var solution = 10000
        for (a in 0 until 100) {
            for (b in 0 until 100) {
                val x = buttonA.x * a + buttonB.x * b
                val y = buttonA.y * a + buttonB.y * b

                if (x == prize.x && y == prize.y) {
                    val potentialSolution = a * 3 + b
                    if (potentialSolution < solution) {
                        solution = potentialSolution
                    }
                }
            }
        }

        return if (solution != 10000) {
            solution
        } else 0
    }

    fun solveTokenCost(): Int {
        val newAX = buttonA.x * buttonB.y
        val newPrizeX = prize.x * buttonB.y

        var newAY = buttonA.y * buttonB.x
        var newPrizeY = prize.y *  buttonB.x

        newPrizeY -= newPrizeX
        newAY -= newAX
        val a = newPrizeY.toFloat() / newAY.toFloat()
        if (hasRemainder(a)) return 0

        val b = (prize.x - (buttonA.x * a)) / buttonB.x
        if (hasRemainder(b)) return 0

        return a.toInt() * 3 + b.toInt()
    }

    private fun hasRemainder(num: Float): Boolean = !(num > 0 && num % 1.0 == 0.0)
}


data class LongMachine(val buttonA: Coord, val buttonB: Coord, val prizeX: Long, val prizeY: Long) {
    fun solveTokenCost(): Long {
        val newAX = Math.multiplyExact(buttonA.x.toLong(), buttonB.y.toLong())
        val newPrizeX = Math.multiplyExact(prizeX, buttonB.y.toLong())

        var newAY = Math.multiplyExact(buttonA.y.toLong(), buttonB.x.toLong())
        var newPrizeY = Math.multiplyExact(prizeY, buttonB.x.toLong())

        newPrizeY -= newPrizeX
        newAY -= newAX
        val bigY = newPrizeY.toBigDecimal()
        val bigAY = newAY.toBigDecimal()
        val a = bigY.divide(bigAY, 10, RoundingMode.HALF_UP)

        if (hasRemainder(a)) return 0

        val ba = buttonA.x.toBigDecimal().times(a)
        val bigX = prizeX.toBigDecimal().minus(ba)
        val bigBX = buttonB.x.toBigDecimal()

        val b = bigX.divide(bigBX, 10, RoundingMode.HALF_UP)
        if (hasRemainder(b)) return 0

        return (a.toLong() * 3 + b.toLong())
    }

    private fun hasRemainder(num: BigDecimal): Boolean = num.remainder(BigDecimal("1.0")) > BigDecimal("0.0")
}
