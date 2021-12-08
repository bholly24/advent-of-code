package lanternfish

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LanternfishTest {

    @Test
    fun runSimulation() {
        val lanternfish = Lanternfish(arrayOf(3,4,3,1,2))
        lanternfish.runSimulation(18)
        assertEquals(
            (lanternfish.lanternfish.map {it.age}),
            mutableListOf(6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8)
        )
    }

    @Test
    fun mapSimultationShort() {
        val lanternMap = LanternMap(arrayOf(3,4,3,1,2))
        assertEquals(26, lanternMap.runSimulationAndGetFishTotal(18))
    }

    @Test
    fun mapSimultationMedium() {
        val lanternMap = LanternMap(arrayOf(3,4,3,1,2))
        assertEquals(5934, lanternMap.runSimulationAndGetFishTotal(80))
    }

    @Test
    fun mapSimultationHard() {
        val lanternMap = LanternMap(arrayOf(3,4,3,1,2))
        assertEquals(26984457539, lanternMap.runSimulationAndGetFishTotal(256))
    }
}