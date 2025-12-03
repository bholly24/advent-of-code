package aoc2025.day01

import java.io.File

class DayOne(filePath: String) {
    private val codes: List<List<String>> = File(filePath).readLines()[0]
        .split(",")
        .map {
            val codes = it.split("-")
            val first = codes.first().toLong()
            val last = codes.last().toLong()
            (first..last).toList().map {i -> i.toString()
        }
    }

    fun partA(): Long {
        val total = codes.sumOf { it.sumOf { s -> if (containsDuplicateSequence(s)) s.toLong() else 0 } }
        println("Total: $total")
        return total
    }

    fun partB(): Long {
        val total = codes.sumOf { it.filter { s -> containsSubsequence(s)}.sumOf { i -> i.toLong() }}
        println("Total: $total")
        return total
    }

    fun containsDuplicateSequence(s: String): Boolean {
        return s.length % 2 == 0 && s.slice(0 until s.length / 2) == s.slice((s.length / 2) until s.length)
    }

    fun containsSubsequence(s: String): Boolean {
        var seq = ""
        var remainingString = s
        for (i in s.indices) {
            if (seq.length > remainingString.length) return false
            if (couldBeChunked(seq, remainingString) && remainingString.chunked(seq.length).none { it != seq })
                return true
            seq += remainingString.first()
            remainingString = remainingString.slice(1 until remainingString.length)
        }
        return false
    }

    private fun couldBeChunked(seq: String, remainingString: String) =
        !(seq.isEmpty() || remainingString.length % seq.length != 0)
}