package day10

import kotlin.math.ceil

class SyntaxAutoComplete(filePath: String) : SyntaxAnalyzer(filePath) {
    private val autocompleteScores = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)

    fun completeNonCorruptedLines(): Long {
        val scoreList = mutableListOf<Long>()
        symbolLines.forEach {
            if (determineLineError(it) == 0) {
                scoreList.add(completeLine(it))
            }
        }
        val score = scoreList.sorted()[ceil((scoreList.size / 2).toDouble()).toInt()]
        println("Middle score: $score")
        return score
    }

    private fun completeLine(line: String): Long {
        val symbolStack = ArrayDeque<Char>()
        line.toCharArray().forEach {
            if (isStartingSymbol(it)) {
                symbolStack.addLast(it)
            } else {
                symbolStack.removeLast()
            }
        }
        var score = 0L
        symbolStack.reversed().forEach {
            score = score * 5 + autocompleteScores.getOrDefault(it, 0)
        }
        return score
    }
}