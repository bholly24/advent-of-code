package aoc2025.day01

import java.io.File

class DayOne(filePath: String) {
    private val codes: List<List<String>> = File(filePath)
        .readLines()
        .first()
        .split(",")
        .map { range ->
            val (start, end) = range.split("-").map(String::toLong)
            (start..end).map(Long::toString)
        }

    fun partA(): Long = codes.sumOf { c -> c.sumOf { s -> if (containsDuplicateSequence(s)) s.toLong() else 0 } }

    fun partB(): Long = codes.sumOf { c -> c.filter(::containsSubsequence).sumOf { it.toLong() } }

    fun containsDuplicateSequence(s: String): Boolean =
        s.length % 2 == 0 && s.take(s.length / 2) == s.drop(s.length / 2)

    fun containsSubsequence(s: String): Boolean {
        return (1..s.length / 2)
            .toList()
            .any {
                val seq = s.take(it)
                val remaining = s.drop(it)
                remaining.chunked(seq.length).all { chunk -> chunk == seq }
            }
    }
}