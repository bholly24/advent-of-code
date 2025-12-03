package aoc2021.day10

class SyntaxParser(filePath: String) : SyntaxAnalyzer(filePath) {

    fun scoreErrors(): Int {
        var score = 0
        symbolLines.forEach {
            score += determineLineError(it)
        }

        println("Parsing score: $score")
        return score
    }
}