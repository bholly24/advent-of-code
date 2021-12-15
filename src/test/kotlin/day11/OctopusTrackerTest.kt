package day11

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OctopusTrackerTest {

    @Test
    fun determineFlashing() {
        val octopusTracker = OctopusTracker("src/test/kotlin/day11/input.txt")
        assertEquals(1656, octopusTracker.predictFlashes(100))
    }

    @Test
    fun determineFirstSynchronizedFlash() {
        val octopusTracker = OctopusTracker("src/test/kotlin/day11/input.txt")
        assertEquals(195, octopusTracker.findSynchronizedFlashStep())
    }

    @Test
    fun firstSynchronizedFlashShouldBeOneTestCase() {
        val octopusTracker = OctopusTracker("src/test/kotlin/day11/one-step.txt")
        assertEquals(1, octopusTracker.findSynchronizedFlashStep())
    }

    @Test
    fun firstSynchronizedFlashShouldBeNineTestCase() {
        val octopusTracker = OctopusTracker("src/test/kotlin/day11/nine-steps.txt")
        assertEquals(9, octopusTracker.findSynchronizedFlashStep())
    }

    @Test
    fun guilliamsTestInput() {
        val octopusTracker = OctopusTracker("src/test/kotlin/day11/guilliams-input-test-case.txt")
        assertEquals(199, octopusTracker.findSynchronizedFlashStep())
    }
}