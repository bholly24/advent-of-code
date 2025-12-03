package aoc2025.day00

data class Instruction(val direction: Direction, val turns: Int)
data class Result(val position: Int, val timesToIncrement: Int)
enum class Direction { Left, Right }

interface GetResult {
    fun next(instruction: Instruction, result: Result): Result
}