package day6

class LanternMap(lanternFishInput: Array<Int>) {
    private val lanternCount = hashMapOf<Int, Long>(0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0, 5 to 0, 6 to 0, 7 to 0, 8 to 0)

    init {
        lanternFishInput.forEach {
            val count = lanternCount.getOrDefault(it, 0)
            lanternCount[it] = count + 1
        }
    }

    fun runSimulationAndGetFishTotal(days: Int): Long {
        for (day in 0 until days) {
            simulateLanternFishDay()
        }

        val fish = lanternCount.map { it.value }.sum()
        println("Total fish: $fish")

        return fish
    }

    private fun simulateLanternFishDay() {
        val fishToAdd = lanternCount.getOrDefault(0, 0)
        for (day in 1 until 9) {
            val nextDay = day - 1
            lanternCount[nextDay] = lanternCount.getOrDefault(day, 0)
        }
        lanternCount[8] = fishToAdd
        lanternCount[6] = lanternCount.getOrDefault(6, 0) + fishToAdd
    }
}
