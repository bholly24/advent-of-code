package aoc2025.day01

import apple.laf.JRSUIConstants
import java.io.File
import kotlin.math.abs

class Solver(filePath: String) {
    private val lines = File(filePath).readLines().map {
        val d = if (it.first() == 'R') Direction.Right else Direction.Left
        val n = it.slice(1 until it.length).toInt()
        Instruction(d, n)
    }

    fun partA(): Int {
        return lines
            .fold(Result(50, 0)) { acc, it -> PartA.next(it, acc) }
            .timesToIncrement
    }

    fun partB(): Int {
        return lines
            .fold(Result(50, 0)) { acc, it -> PartB.next(it, acc) }
            .timesToIncrement
    }

    object PartA : GetResult {
        override fun next(instruction: Instruction, result: Result): Result {
            val resultingPosition = if (instruction.direction == Direction.Left) {
                val pos = result.position - instruction.turns
                if (pos % 100 < 0) (pos + 100) % 100 else pos % 100
            } else {
                (result.position + instruction.turns) % 100
            }
            return Result(resultingPosition, result.timesToIncrement + (if (resultingPosition == 0) 1 else 0))
        }
    }

    object PartB : GetResult {
        override fun next(instruction: Instruction, result: Result): Result {
            return if (instruction.direction == Direction.Right) right(instruction.turns, result) else left(
                instruction.turns,
                result
            )
        }

        private fun left(turns: Int, startingPosition: Result): Result {
            val raw = startingPosition.position - turns
            val baseTimes = abs(raw.floorDiv(100))
            val wrappedPosition = ((raw % 100) + 100) % 100 // Wrap to make it positive
            val updatedTimes = startingPosition.timesToIncrement +
                    baseTimes -
                    (if (startingPosition.position == 0) 1 else 0) +
                    (if (wrappedPosition == 0) 1 else 0)
            return Result(wrappedPosition, updatedTimes)
        }

        private fun right(turns: Int, startingPosition: Result): Result {
            val pos = startingPosition.position + turns
            val times = startingPosition.timesToIncrement + abs(pos.floorDiv(100))
            return Result(pos % 100, times)
        }
    }
}

