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

    fun partA(): Long {
        val total = codes.sumOf { code -> code.sumOf { s -> if (containsDuplicateSequence(s)) s.toLong() else 0 } }
        println("Total: $total")
        return total
    }

    fun partB(): Long {
        val total = codes.sumOf { code -> code.filter(::containsSubsequence).sumOf { it.toLong() } }
        println("Total: $total")
        return total
    }

    fun containsDuplicateSequence(s: String): Boolean {
        return s.length % 2 == 0 && s.take(s.length / 2) == s.drop(s.length / 2)
    }

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