package day07

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CrabCommanderTest {

    @Test
    fun alignCrabs() {
        val crabCommander = LinearCrabCommander(listOf(16,1,2,0,4,2,7,1,2,14))
        assertEquals(37, crabCommander.alignCrabs())
    }

    @Test
    fun alignExponentialCrabs() {
        val crabCommander = ExponentialCrabCommander(listOf(16,1,2,0,4,2,7,1,2,14))
        assertEquals(168, crabCommander.alignCrabs())
    }
}