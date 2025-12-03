package aoc2023.day06

interface CamelCard {
    val score: Score
    val wager: Int
    val tieBreakScore: List<Int>
}