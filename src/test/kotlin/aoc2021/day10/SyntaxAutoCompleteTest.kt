package aoc2021.day10

import aoc2021.day10.SyntaxAutoComplete
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SyntaxAutoCompleteTest {
    @Test
    fun scoreAutocomplete() {
        val syntaxAutoComplete = SyntaxAutoComplete("src/test/kotlin/aoc2021/day10/input.txt")
        assertEquals(288957, syntaxAutoComplete.completeNonCorruptedLines())
    }
}