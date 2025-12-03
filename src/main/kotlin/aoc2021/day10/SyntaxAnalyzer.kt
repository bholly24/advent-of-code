package aoc2021.day10

import java.io.File
import java.io.InputStream

abstract class SyntaxAnalyzer(filePath: String) {
    protected val symbolLines = readInLines(filePath)
    private val stopSymbolMatcher = mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
    private val stopSymbolErrorScores = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)

    private fun readInLines(filePath: String): List<String> {
        val inputStream: InputStream = File(filePath).inputStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().forEachLine { lineList.add(it) }

        return lineList
    }

    protected fun determineLineError(line: String): Int {
        val symbolStack = ArrayDeque<Char>()
        line.toCharArray().forEach {
            if (isStartingSymbol(it)) {
                symbolStack.addLast(it)
            } else {
                val symbolThatShouldMatchStopSymbol = symbolStack.removeLast()
                if (symbolThatShouldMatchStopSymbol != stopSymbolMatcher.get(it)) {
                    return stopSymbolErrorScores.getOrDefault(it, 0)
                }
            }
        }
        return 0
    }

    protected fun isStartingSymbol(symbol: Char): Boolean {
        return listOf('(', '[', '{', '<').contains(symbol)
    }
}