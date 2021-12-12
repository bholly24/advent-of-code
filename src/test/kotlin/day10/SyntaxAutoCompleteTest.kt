package day10

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SyntaxAutoCompleteTest {
    @Test
    fun scoreAutocomplete() {
        val syntaxAutoComplete = SyntaxAutoComplete("src/test/kotlin/day10/input.txt")
        assertEquals(288957, syntaxAutoComplete.completeNonCorruptedLines())
    }
}