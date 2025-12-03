package aoc2021.day10

import aoc2021.day10.SyntaxParser
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SyntaxParserTest {

    @Test
    fun scoreErrors() {
        val syntaxParser = SyntaxParser("src/test/kotlin/aoc2021/day10/input.txt")
        assertEquals(26397, syntaxParser.scoreErrors())
    }
}