package aoc2021.day13

import aoc2021.day13.InstructionCreaser
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InstructionCreaserTest {

    @Test
    fun foldHorizontally() {
        val instructionCreaser = InstructionCreaser("src/test/kotlin/aoc2021/day13/input.txt")
        assertEquals(17, instructionCreaser.foldHorizontally(7))
    }

    @Test
    fun foldVertically() {
        val instructionCreaser = InstructionCreaser("src/test/kotlin/aoc2021/day13/input.txt")
        assertEquals(17, instructionCreaser.foldVertically(5))
    }

    @Test
    fun foldHorizontallyAndVertically() {
        val instructionCreaser = InstructionCreaser("src/test/kotlin/aoc2021/day13/input.txt")
        instructionCreaser.foldHorizontally(7)
        assertEquals(16, instructionCreaser.foldVertically(5))
    }

    @Test
    fun followInstructions() {
        val instructionCreaser = InstructionCreaser("src/test/kotlin/aoc2021/day13/input.txt")
        assertEquals(16, instructionCreaser.executeFoldSteps())
    }
}