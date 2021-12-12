package day10

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SyntaxParserTest {

    @Test
    fun scoreErrors() {
        val syntaxParser = SyntaxParser("src/test/kotlin/day10/input.txt")
        assertEquals(26397, syntaxParser.scoreErrors())
    }
}