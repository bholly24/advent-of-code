package aoc2022.day02

interface Choice {
    val score: Int
    val beats: Choice
    val losesTo: Choice
}